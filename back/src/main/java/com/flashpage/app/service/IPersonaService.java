package com.flashpage.app.service;

import java.util.List;

import com.flashpage.app.dto.PersonaRequestDTO;
import com.flashpage.app.dto.PersonaResponseDTO;
import com.flashpage.app.model.Persona.Rol;

public interface IPersonaService {
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    PersonaResponseDTO crearPersona(PersonaRequestDTO dto);
    // ------------ READ ONE ------------ //
    PersonaResponseDTO readOnePersona(Long id);
    // ------------ READ ALL ------------ //
    public List<PersonaResponseDTO> readAllPersona();
    // ------------ READ ALL ROL ------------ //
    public List<PersonaResponseDTO> readAllRol(Rol rol);
    // ------------- UPDATE ------------- //
    PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO dto);
    // ------------- DELETE ------------- //
    PersonaResponseDTO deletePersona(Long id);
}
