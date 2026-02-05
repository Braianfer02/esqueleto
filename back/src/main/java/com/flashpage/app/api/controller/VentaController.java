package com.flashpage.app.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.flashpage.app.api.dto.VentaDTO;
import com.flashpage.app.api.mapper.VentaMapper;
import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Venta;
import com.flashpage.app.domain.model.Venta.EstadoVenta;
import com.flashpage.app.domain.service.PersonaService;
import com.flashpage.app.domain.service.VentaService;
import com.flashpage.app.exception.BusinessException;
import com.flashpage.app.security.authz.AuthorizationService;
import com.flashpage.app.security.user.AppUserDetails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final PersonaService personaService;
    private final AuthorizationService authorizationService;

    public VentaController(VentaService ventaService, PersonaService personaService, AuthorizationService authorizationService) {
        this.ventaService = ventaService;
        this.personaService = personaService;
        this.authorizationService = authorizationService;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    @PostMapping
    public ResponseEntity<VentaDTO.Response> create(
            @AuthenticationPrincipal AppUserDetails principal,
            @Valid @RequestBody VentaDTO.Create req
    ) {
        Persona actor = principal.getPersona();

        // Resolver asesor real (existe y trae rol/jefe)
        Persona asesor = personaService.readById(req.asesorId());

        // Authz: EMPLEADO solo para sí; SUPERVISOR+ para cualquiera (según tu AuthorizationService)
        if (!authorizationService.puedeCrearVenta(actor, asesor)) {
            // ideal: ForbiddenException (403). Si no la tenés, BusinessException (400) zafa por ahora.
            throw new BusinessException("No tenés permiso para crear ventas para ese asesor");
        }

        // Mapear venta desde DTO
        Venta venta = VentaMapper.toEntity(req);

        // Pisar con entidades reales (importante para validar rol y jerarquía)
        venta.setAsesor(asesor);

        if (req.clienteId() != null) {
            Persona cliente = personaService.readById(req.clienteId());
            venta.setCliente(cliente);
        }

        Venta creada = ventaService.create(venta);

        return ResponseEntity.ok(VentaMapper.toResponse(creada));
    }

    // ------------------------ READ ------------------------ //
    @GetMapping
    public ResponseEntity<List<VentaDTO.Response>> readAll() {
        List<VentaDTO.Response> list = ventaService.readAll()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        return ResponseEntity.ok(list);
    }

    // ------------------------ READ ------------------------ //
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> readById(@PathVariable Long id) {
        Venta v = ventaService.readById(id);
        return ResponseEntity.ok(VentaMapper.toResponse(v));
    }

    // ----------------------- UPDATE ----------------------- //
    @PatchMapping("/{id}")
    public ResponseEntity<VentaDTO.Response> update(
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable Long id,
            @RequestBody VentaDTO.Update req
    ) {
        Persona actor = principal.getPersona();
        Venta existente = ventaService.readById(id);

        if (!authorizationService.puedeActualizarVenta(actor, existente)) {
            throw new BusinessException("No tenés permiso para actualizar esta venta");
        }

        // Update parcial con mapper
        VentaMapper.applyUpdate(req, existente);

        // Si viene clienteId, resolvemos entidad real
        if (req.clienteId() != null) {
            Persona cliente = personaService.readById(req.clienteId());
            existente.setCliente(cliente);
        }

        Venta updated = ventaService.update(id, existente);

        return ResponseEntity.ok(VentaMapper.toResponse(updated));
    }

    // ----------------------- DELETE ----------------------- //
    public record CancelRequest(
            @Size(max = 1000) String observaciones
    ) {}

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<VentaDTO.Response> cancelar(
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable Long id,
            @RequestBody(required = false) CancelRequest req
    ) {
        Persona actor = principal.getPersona();
        Venta venta = ventaService.readById(id);

        if (!authorizationService.puedeCancelarVenta(actor, venta)) {
            throw new BusinessException("No tenés permiso para cancelar esta venta");
        }

        venta.setEstado(EstadoVenta.CANCELADO);

        if (req != null && req.observaciones() != null && !req.observaciones().isBlank()) {
            String obs = venta.getObservaciones() == null ? "" : venta.getObservaciones();
            if (!obs.isBlank()) obs += "\n";
            obs += "CANCELADA: " + req.observaciones();
            venta.setObservaciones(obs);
        }

        Venta updated = ventaService.update(id, venta);

        return ResponseEntity.ok(VentaMapper.toResponse(updated));
    }
}
