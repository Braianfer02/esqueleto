package com.flashpage.app.dto;

import com.flashpage.app.model.Persona;

public class PersonaMapper {

    public static Persona toEntity(PersonaRequestDTO dto) {
        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        persona.setDni(dto.getDni());
        persona.setTelefono(dto.getTelefono());
        persona.setRol(dto.getRol());
        persona.setDireccion(dto.getDireccion());
        persona.setLocalidad(dto.getLocalidad());
        persona.setProvincia(dto.getProvincia());
        persona.setCodigoPostal(dto.getCodigoPostal());
        persona.setPais(dto.getPais());
        persona.setUsuario(dto.getUsuario());
        persona.setContraseña(dto.getContraseña());
        return persona;
    }

    public static PersonaResponseDTO toResponse(Persona persona) {
        return new PersonaResponseDTO(
                persona.getId(),
                persona.getNombre() + " " + persona.getApellido(),
                persona.getDni(),
                persona.getTelefono(),
                persona.getRol()
        );
    }
}
