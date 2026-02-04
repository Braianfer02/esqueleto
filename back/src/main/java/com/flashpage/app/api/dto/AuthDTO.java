package com.flashpage.app.api.dto;

import com.flashpage.app.domain.model.Persona.Rol;
import jakarta.validation.constraints.NotBlank;

public class AuthDTO {

    public record LoginRequest(
            @NotBlank String username,
            @NotBlank String password
    ) {}

    public record LoginResponse(
            String token,
            Long personaId,
            String username,
            Rol rol
    ) {}
}
