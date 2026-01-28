package com.flashpage.app.service;

import java.util.List;

import com.flashpage.app.dto.VentaRequestDTO;
import com.flashpage.app.dto.VentaResponseDTO;

public interface IVentaService {
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    VentaResponseDTO crearVenta(VentaRequestDTO dto);
    // ------------ READ ONE ------------ //
    VentaResponseDTO readOneVenta(Long id);
    // ------------ READ ALL ------------ //
    List<VentaResponseDTO> readAllVenta();
    // ------------ READ ASESOR ------------ //
    List<VentaResponseDTO> readAsesor(Long asesorId);
    // ------------ READ CLIENTE ------------ //
    List<VentaResponseDTO> readCliente(Long clienteId);
    // ------------- UPDATE ------------- //
    VentaResponseDTO updateVenta(Long id, VentaRequestDTO dto);
    // ------------- DELETE ------------- //
    VentaResponseDTO deleteVenta(Long id);
}
