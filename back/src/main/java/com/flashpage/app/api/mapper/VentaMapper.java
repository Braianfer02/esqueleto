package com.flashpage.app.api.mapper;

import java.util.stream.Collectors;

import com.flashpage.app.api.dto.VentaDTO;
import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Producto;
import com.flashpage.app.domain.model.Venta;
import com.flashpage.app.domain.model.VentaItem;

public class VentaMapper {

    public static Venta toEntity(VentaDTO.Create dto) {
        Venta objetoVenta = new Venta();

        objetoVenta.setEstado(dto.estado()); // puede ser null, el service lo setea a SIN_ESTADO
        objetoVenta.setMedioPago(dto.medioPago());
        objetoVenta.setDigitoTarjeta(dto.digitoTarjeta());
        objetoVenta.setObservaciones(dto.observaciones());

        Persona asesor = new Persona();
        asesor.setId(dto.asesorId());
        objetoVenta.setAsesor(asesor);
        if (dto.clienteId() != null) {
            Persona cliente = new Persona();
            cliente.setId(dto.clienteId());
            objetoVenta.setCliente(cliente);
        }

        objetoVenta.setItems(dto.items().stream().map(it -> {
            VentaItem item = new VentaItem();
            item.setCantidad(it.cantidad());

            Producto objetoProducto = new Producto();
            objetoProducto.setId(it.productoId());
            item.setProducto(objetoProducto);

            item.setVenta(objetoVenta);
            return item;
        }).collect(Collectors.toList()));

        return objetoVenta;
    }

    public static void applyUpdate(VentaDTO.Update dto, Venta objetoVenta) {
        if (dto.estado() != null) objetoVenta.setEstado(dto.estado());
        if (dto.medioPago() != null) objetoVenta.setMedioPago(dto.medioPago());
        if (dto.digitoTarjeta() != null) objetoVenta.setDigitoTarjeta(dto.digitoTarjeta());
        if (dto.observaciones() != null) objetoVenta.setObservaciones(dto.observaciones());
        if (dto.clienteId() != null) {
            Persona cliente = new Persona();
            cliente.setId(dto.clienteId());
            objetoVenta.setCliente(cliente);
        }
    }

    public static VentaDTO.Response toResponse(Venta objetoVenta) {
        Long asesorId = objetoVenta.getAsesor() != null ? objetoVenta.getAsesor().getId() : null;
        Long clienteId = objetoVenta.getCliente() != null ? objetoVenta.getCliente().getId() : null;

        String masked = objetoVenta.getDigitoTarjeta();
        if (masked != null && masked.length() > 4) {
            masked = "****" + masked.substring(masked.length() - 4);
        }

        return new VentaDTO.Response(
                objetoVenta.getId(),
                objetoVenta.getCreatedAt(),
                objetoVenta.getUpdatedAt(),
                objetoVenta.getEstado(),
                objetoVenta.getMedioPago(),
                masked,
                asesorId,
                clienteId,
                objetoVenta.getTotal(),
                objetoVenta.getItems() == null ? java.util.List.of() :
                        objetoVenta.getItems().stream().map(item ->
                                new VentaDTO.ItemResponse(
                                        item.getProducto() != null ? item.getProducto().getId() : null,
                                        item.getProducto() != null ? item.getProducto().getProducto() : null,
                                        item.getCantidad(),
                                        item.getPrecioUnitario(),
                                        item.getSubtotal()
                                )
                        ).collect(java.util.stream.Collectors.toList())
        );
    }
}
