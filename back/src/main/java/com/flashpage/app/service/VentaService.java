package com.flashpage.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.dto.VentaMapper;
import com.flashpage.app.dto.VentaRequestDTO;
import com.flashpage.app.dto.VentaResponseDTO;
import com.flashpage.app.exception.ResourceNotFoundException;
import com.flashpage.app.model.Persona;
import com.flashpage.app.model.Venta;
import com.flashpage.app.repository.PersonaRepository;
import com.flashpage.app.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final PersonaRepository personaRepository;

    public VentaService(VentaRepository ventaRepository,
                        PersonaRepository personaRepository) {
        this.ventaRepository = ventaRepository;
        this.personaRepository = personaRepository;
    }

    // -------- CREATE --------
    @Override
    public VentaResponseDTO crearVenta(VentaRequestDTO dto) {

        Persona asesor = personaRepository.findById(dto.getAsesorId())
                .orElseThrow(() -> new ResourceNotFoundException("Asesor no encontrado"));

        Persona cliente = personaRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Venta venta = VentaMapper.toEntity(dto, asesor, cliente);

        // ❌ NO seteamos createdAt ni fechaVenta
        // JPA se encarga solo (@PrePersist)

        return VentaMapper.toResponse(ventaRepository.save(venta));
    }
    // -------- READ ONE --------
    @Override
    public VentaResponseDTO readOneVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta con id " + id + " no encontrada"));

        return VentaMapper.toResponse(venta);
    }

    // -------- READ ALL --------
    @Override
    public List<VentaResponseDTO> readAllVenta() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    // -------- READ BY ASESOR --------
    @Override
    public List<VentaResponseDTO> readAsesor(Long asesorId) {
        return ventaRepository.findByAsesorId(asesorId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    // -------- READ BY CLIENTE --------
    @Override
    public List<VentaResponseDTO> readCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();
    }

    // -------- UPDATE --------
    @Transactional
    @Override
    public VentaResponseDTO updateVenta(Long id, VentaRequestDTO dto) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta con id " + id + " no encontrada"));

        venta.setProducto(dto.getProducto());
        venta.setDescripcion(dto.getDescripcion());
        venta.setDigitoTarjeta(dto.getDigitoTarjeta());
        venta.setPrecio(dto.getPrecio());
        venta.setEstado(dto.getEstado());
        venta.setMedioPago(dto.getMedioPago());
        venta.setObservaciones(dto.getObservaciones());

        return VentaMapper.toResponse(ventaRepository.save(venta));
    }

    // -------- DELETE --------
    @Override
    public VentaResponseDTO deleteVenta(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta con id " + id + " no encontrada"));

        ventaRepository.delete(venta);
        return VentaMapper.toResponse(venta);
    }
}
