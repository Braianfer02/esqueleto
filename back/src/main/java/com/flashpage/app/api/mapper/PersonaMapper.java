package com.flashpage.app.api.mapper;

import com.flashpage.app.api.dto.PersonaDTO;
import com.flashpage.app.domain.model.Persona;

public class PersonaMapper {

    public static PersonaDTO.Response toResponse(Persona objetoPersona) {
        Long jefeId = (objetoPersona.getJefe() != null) ? objetoPersona.getJefe().getId() : null;

        return new PersonaDTO.Response(
            objetoPersona.getId(),
            objetoPersona.getNombre(),
            objetoPersona.getApellido(),
            objetoPersona.getDni(),
            objetoPersona.getEmail(),
            objetoPersona.getUsername(),
            objetoPersona.getActivo(),
            objetoPersona.getRol(),
            jefeId
        );
    }

    public static Persona toEntity(PersonaDTO.Create dto) {
        Persona objetoPersona = new Persona();
        objetoPersona.setNombre(dto.nombre());
        objetoPersona.setApellido(dto.apellido());
        objetoPersona.setDni(dto.dni());
        objetoPersona.setEmail(dto.email());
        objetoPersona.setUsername(dto.username());
        objetoPersona.setActivo(true);
        objetoPersona.setRol(dto.rol());
        return objetoPersona;
    }

    public static void applyUpdate(PersonaDTO.Update dto, Persona objetoPersona) {
        if (dto.nombre() != null) objetoPersona.setNombre(dto.nombre());
        if (dto.apellido() != null) objetoPersona.setApellido(dto.apellido());
        if (dto.dni() != null) objetoPersona.setDni(dto.dni());
        if (dto.email() != null) objetoPersona.setEmail(dto.email());
        if (dto.username() != null) objetoPersona.setUsername(dto.username());
        if (dto.activo() != null) objetoPersona.setActivo(dto.activo());
        if (dto.rol() != null) objetoPersona.setRol(dto.rol());
    }
}
