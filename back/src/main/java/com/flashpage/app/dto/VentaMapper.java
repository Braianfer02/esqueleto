package com.flashpage.app.dto;

import com.flashpage.app.model.Venta;

public class VentaMapper {

    public static VentaResponseDTO toResponse(Venta venta) {
        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setId(venta.getId());
        dto.setProducto(venta.getProducto());
        dto.setPrecio(venta.getPrecio());
        dto.setEstado(venta.getEstado());
        dto.setFechaVenta(venta.getFechaVenta());

        dto.setAsesorId(venta.getAsesor().getId());
        dto.setAsesorNombre(
            venta.getAsesor().getNombre() + " " + venta.getAsesor().getApellido()
        );

        if (venta.getCliente() != null) {
            dto.setClienteId(venta.getCliente().getId());
            dto.setClienteNombre(
                venta.getCliente().getNombre() + " " + venta.getCliente().getApellido()
            );
        }
        return dto;
    }
}
