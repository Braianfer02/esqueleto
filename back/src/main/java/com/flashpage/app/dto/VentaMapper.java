package com.flashpage.app.dto;

import com.flashpage.app.model.Persona;
import com.flashpage.app.model.Venta;

public class VentaMapper {

    public static Venta toEntity(VentaRequestDTO dto, Persona asesor, Persona cliente) {

        Venta venta = new Venta();
        venta.setProducto(dto.getProducto());
        venta.setDescripcion(dto.getDescripcion());
        venta.setDigitoTarjeta(dto.getDigitoTarjeta());
        venta.setPrecio(dto.getPrecio());
        venta.setEstado(dto.getEstado());
        venta.setMedioPago(dto.getMedioPago());
        venta.setObservaciones(dto.getObservaciones());
        venta.setAsesor(asesor);
        venta.setCliente(cliente);

        return venta;
    }

    public static VentaResponseDTO toResponse(Venta venta) {
        return new VentaResponseDTO(
            venta.getId(),
            venta.getProducto(),
            venta.getPrecio(),
            venta.getAsesor() != null ? venta.getAsesor().getId() : null,
            venta.getAsesor() != null ? venta.getAsesor().getNombre() + " " + venta.getAsesor().getApellido() : null,
            venta.getCliente() != null ? venta.getCliente().getId() : null,
            venta.getCliente() != null ? venta.getCliente().getNombre() + " " + venta.getCliente().getApellido() : null
        );
    }
}
