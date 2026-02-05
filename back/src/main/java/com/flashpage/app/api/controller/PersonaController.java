package com.flashpage.app.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.flashpage.app.api.dto.PersonaDTO;
import com.flashpage.app.api.mapper.PersonaMapper;
import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.repository.PersonaRepository;
import com.flashpage.app.domain.service.PersonaService;
import com.flashpage.app.exception.BusinessException;
import com.flashpage.app.security.authz.AuthorizationService;
import com.flashpage.app.security.user.AppUserDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(
            PersonaService personaService,
            PersonaRepository personaRepository,
            AuthorizationService authorizationService
    ) {
        this.personaService = personaService;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    @PostMapping
    public ResponseEntity<PersonaDTO.Response> create(
            @AuthenticationPrincipal AppUserDetails principal,
            @Valid @RequestBody PersonaDTO.Create req
    ) {
        Persona actor = principal.getPersona();

        // Si creás un empleado (rol != CLIENTE), chequeamos jerarquía: actor debe ser superior
        // (si es CLIENTE, normalmente lo puede crear cualquiera interno; si querés, también controlalo)
        if (req.rol() != null && req.rol() != Persona.Rol.CLIENTE) {
            if (actor.getRol().level() <= req.rol().level()) {
                throw new BusinessException("No tenés permiso para crear una persona con ese rol");
            }
        }

        Persona nueva = PersonaMapper.toEntity(req);

        // Setear jefe si viene
        if (req.jefeId() != null) {
            Persona jefe = personaService.readById(req.jefeId());

            // Regla pro: el jefe debe ser superior al nuevo
            if (jefe.getRol().level() <= nueva.getRol().level()) {
                throw new BusinessException("El jefe debe tener un rol superior al subordinado");
            }
            nueva.setJefe(jefe);
        }

        Persona creada = personaService.create(nueva);

        return ResponseEntity
                .created(URI.create("/personas/" + creada.getId()))
                .body(PersonaMapper.toResponse(creada));
    }

    // ------------------------ READ ------------------------ //
    @GetMapping
    public ResponseEntity<List<PersonaDTO.Response>> readAll() {
        List<PersonaDTO.Response> list = personaService.readAll()
                .stream()
                .map(PersonaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    // ------------------------ READ ------------------------ //
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO.Response> readById(@PathVariable Long id) {
        Persona p = personaService.readById(id);
        return ResponseEntity.ok(PersonaMapper.toResponse(p));
    }

    // ----------------------- UPDATE ----------------------- //
    @PatchMapping("/{id}")
    public ResponseEntity<PersonaDTO.Response> update(
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable Long id,
            @RequestBody PersonaDTO.Update req
    ) {
        Persona actor = principal.getPersona();
        Persona target = personaService.readById(id);

        // Regla: solo si sos superior al target (por rol)
        if (actor.getRol().level() <= target.getRol().level()) {
            throw new BusinessException("No tenés permiso para modificar esta persona");
        }

        // Si intentan cambiar rol, también validamos (no podés “promover” a alguien a tu mismo nivel o superior)
        if (req.rol() != null && actor.getRol().level() <= req.rol().level()) {
            throw new BusinessException("No podés asignar un rol igual o superior al tuyo");
        }

        PersonaMapper.applyUpdate(req, target);

        Persona updated = personaService.update(id, target);

        return ResponseEntity.ok(PersonaMapper.toResponse(updated));
    }

    // ----------------------- DELETE ----------------------- //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable Long id
    ) {
        Persona actor = principal.getPersona();
        Persona target = personaService.readById(id);

        // Regla: solo si sos superior al target
        if (actor.getRol().level() <= target.getRol().level()) {
            throw new BusinessException("No tenés permiso para eliminar esta persona");
        }

        personaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // (OPCIONAL y recomendado) Soft delete
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<PersonaDTO.Response> desactivar(
            @AuthenticationPrincipal AppUserDetails principal,
            @PathVariable Long id
    ) {
        Persona actor = principal.getPersona();
        Persona target = personaService.readById(id);

        if (actor.getRol().level() <= target.getRol().level()) {
            throw new BusinessException("No tenés permiso para desactivar esta persona");
        }

        target.setActivo(false);
        Persona updated = personaService.update(id, target);

        return ResponseEntity.ok(PersonaMapper.toResponse(updated));
    }
}
