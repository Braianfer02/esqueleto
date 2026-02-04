package com.flashpage.app.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.flashpage.app.domain.model.Venta.EstadoVenta;
import com.flashpage.app.domain.model.Venta.MedioPago;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class VentaDTO {

    public record ItemRequest(
            @NotNull Long productoId,
            @NotNull @Positive Integer cantidad
    ) {}

    // REQUEST (create)
    public record Create(
            @NotNull Long asesorId,
            Long clienteId,
            MedioPago medioPago,
            @Size(max=16) String digitoTarjeta,
            String observaciones,
            @NotNull @Size(min = 1) List<ItemRequest> items,
            EstadoVenta estado // opcional (si no viene, service pone SIN_ESTADO)
    ) {}

    // REQUEST (update) tipo PATCH: todo opcional
    public record Update(
            EstadoVenta estado,
            MedioPago medioPago,
            @Size(max=16) String digitoTarjeta,
            String observaciones,
            Long clienteId
    ) {}

    public record ItemResponse(
            Long productoId,
            String producto,
            Integer cantidad,
            BigDecimal precioUnitario,
            BigDecimal subtotal
    ) {}

    // RESPONSE
    public record Response(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            EstadoVenta estado,
            MedioPago medioPago,
            @Size(max=16) String digitoTarjeta,
            Long asesorId,
            Long clienteId,
            BigDecimal total,
            @NotEmpty List<ItemResponse> items
    ) {}
}
