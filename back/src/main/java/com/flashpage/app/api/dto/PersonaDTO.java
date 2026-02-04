package com.flashpage.app.api.dto;

import com.flashpage.app.domain.model.Persona.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonaDTO {

    // REQUEST (create)
    public record Create(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String dni,
        @Email @NotBlank String email,
        String username,
        String password,
        @NotNull Rol rol,
        Long jefeId
    ) {}

    // REQUEST (update) tipo PATCH: todo opcional
    public record Update(
        String nombre,
        String apellido,
        String dni,
        @Email String email,
        String username,
        String password,
        Boolean activo,
        Rol rol
    ) {}

    // RESPONSE
    public record Response(
        Long id,
        String nombre,
        String apellido,
        String dni,
        String email,
        String username,
        boolean activo,
        Rol rol,
        Long jefeId
    ) {}
}
