package com.flashpage.app.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductoDTO {
    // REQUEST (create)
    public record Create(
        @NotBlank String producto,
        String descripcion,
        @NotNull @Positive BigDecimal precio
    ) {}

    // REQUEST (update) tipo PATCH: todo opcional
    public record Update(
        String producto,
        String descripcion,
        BigDecimal precio
    ) {}

    // RESPONSE
    public record Response(
        Long id,
        String producto,
        String descripcion,
        BigDecimal precio,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}
}
