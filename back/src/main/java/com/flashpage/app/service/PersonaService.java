package com.flashpage.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.model.Persona;
import com.flashpage.app.model.Persona.Rol;
import com.flashpage.app.repository.PersonaRepository;
import com.flashpage.app.dto.PersonaMapper;
import com.flashpage.app.dto.PersonaRequestDTO;
import com.flashpage.app.dto.PersonaResponseDTO;
import com.flashpage.app.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class PersonaService implements IPersonaService{

    // ---------- IMPLEMENTAMOS ---------- //
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    @Override
    public PersonaResponseDTO crearPersona(PersonaRequestDTO dto){
        // DTO -> ENTITY
        Persona objetPersona = PersonaMapper.toEntity(dto);
        // GUARDAR EN BD
        Persona guardarPersona = personaRepository.save(objetPersona);
        // ENTITY -> RESPONSEDTO
        return PersonaMapper.toResponse(guardarPersona);
    };
    // ------------ READ ONE ------------ //
    @Override
    public PersonaResponseDTO readOnePersona(Long id){
        // BUSCAMOS A LA PERSONA
        Persona objetoPersona = personaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Persona con id " + id + " no encontrada"));
        // RETORNAMOS PERSONA DTO
        return PersonaMapper.toResponse(objetoPersona);
    }
    // ------------ READ ALL ------------ //
    @Override
    public List<PersonaResponseDTO> readAllPersona(){
        // RETORNAMOS LISTA DE PERSONA DTO
        return personaRepository.findAll().stream().map(PersonaMapper::toResponse).toList();
    };
    @Override
    // ------------ READ ALL ROL ------------ //
    public List<PersonaResponseDTO> readAllRol(Rol rol) {
        return personaRepository.findByRol(rol)
                .stream()
                .map(PersonaMapper::toResponse)
                .toList();
    }
    // ------------ UPDATE -------------- //
    @Transactional
    @Override
    public PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO dto) {

        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona con id " + id + " no encontrada"));

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

        return PersonaMapper.toResponse(personaRepository.save(persona));
    }
    // ------------- DELETE ------------- //
    @Override
    public PersonaResponseDTO deletePersona(Long id){
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Persona con id " + id + " no encontrada"));
        personaRepository.delete(persona);
        return PersonaMapper.toResponse(persona);
    }
}
