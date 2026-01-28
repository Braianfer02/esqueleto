package com.flashpage.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flashpage.app.dto.VentaRequestDTO;
import com.flashpage.app.dto.VentaResponseDTO;
import com.flashpage.app.service.IVentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    // -------- CREATE --------
    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(
            @Valid @RequestBody VentaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ventaService.crearVenta(dto));
    }

    // -------- READ ONE --------
    @GetMapping("/{id}")
    public VentaResponseDTO obtenerVenta(@PathVariable Long id) {
        return ventaService.readOneVenta(id);
    }

    // -------- READ ALL --------
    @GetMapping
    public List<VentaResponseDTO> obtenerTodas() {
        return ventaService.readAllVenta();
    }

    // -------- READ BY ASESOR --------
    @GetMapping("/asesor/{id}")
    public List<VentaResponseDTO> obtenerPorAsesor(@PathVariable Long id) {
        return ventaService.readAsesor(id);
    }

    // -------- READ BY CLIENTE --------
    @GetMapping("/cliente/{id}")
    public List<VentaResponseDTO> obtenerPorCliente(@PathVariable Long id) {
        return ventaService.readCliente(id);
    }

    // -------- UPDATE --------
    @PutMapping("/{id}")
    public VentaResponseDTO actualizarVenta(
            @PathVariable Long id,
            @Valid @RequestBody VentaRequestDTO dto) {

        return ventaService.updateVenta(id, dto);
    }

    // -------- DELETE --------
    @DeleteMapping("/{id}")
    public VentaResponseDTO eliminarVenta(@PathVariable Long id) {
        return ventaService.deleteVenta(id);
    }
}
