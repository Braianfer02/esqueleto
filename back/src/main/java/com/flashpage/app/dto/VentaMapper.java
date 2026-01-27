package com.flashpage.app.dto;

import com.flashpage.app.model.Venta;

public class VentaMapper {

    public static Venta toEntity(VentaRequestDTO dto) {
        Venta venta = new Venta();
        venta.setProducto(dto.getProducto());
        venta.setMonto(dto.getMonto());
        return venta;
    }

    public static VentaResponseDTO toResponse(Venta venta) {
        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setId(venta.getId());
        dto.setProducto(venta.getProducto());
        dto.setMonto(venta.getMonto());
        dto.setFecha(venta.getFecha());
        dto.setAsesorId(venta.getAsesor().getId());
        dto.setAsesorNombre(
            venta.getAsesor().getNombre() + " " + venta.getAsesor().getApellido()
        );
        return dto;
    }
}
