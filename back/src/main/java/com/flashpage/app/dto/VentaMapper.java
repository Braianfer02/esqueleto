package com.flashpage.app.dto;

import com.flashpage.app.model.Venta;

public class VentaMapper {

    public static Venta toEntity(VentaRequestDTO dto) {
        Venta venta = new Venta();
        venta.setProducto(dto.getProducto());
        venta.setDescripcion(dto.getDescripcion());
        venta.setPrecio(dto.getPrecio());
        venta.setEstado(dto.getEstado());
        venta.setMedioPago(dto.getMedioPago());
        venta.setObservaciones(dto.getObservaciones());
        return venta;
    }

    public static VentaResponseDTO toResponse(Venta venta) {
        return new VentaResponseDTO(
            venta.getId(),
            venta.getProducto(),
            venta.getPrecio(),
            venta.getAsesor().getId(),
            venta.getAsesor().getNombre(),
            venta.getCliente() != null ? venta.getCliente().getId() : null,
            venta.getCliente() != null ? venta.getCliente().getNombre() : null
        );
    }
}
