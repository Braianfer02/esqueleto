package com.flashpage.app.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.repository.PersonaRepository;
import com.flashpage.app.domain.service.PersonaService;
import com.flashpage.app.exception.BusinessException;
import com.flashpage.app.exception.ResourceNotFoundException;

@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    public Persona create(Persona persona) {

        if (personaRepository.existsByDni(persona.getDni())) throw new BusinessException("DNI ya existe");
        if (personaRepository.existsByEmail(persona.getEmail())) throw new BusinessException("Email ya existe");
        if (persona.getUsername() != null && persona.getUsername().isBlank())
            throw new BusinessException("Username inválido");
        if (persona.getUsername() != null && personaRepository.existsByUsername(persona.getUsername()))
            throw new BusinessException("Username ya existe");
            
        return personaRepository.save(persona);
    }
    // ------------------------ READ ------------------------ //
    public Persona readById(Long id){
        return personaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Persona con id " + id + " no encontrada"));
    }
    // ------------------------ READ ------------------------ //
    public Optional<Persona> findByDni(String dni){
        return personaRepository.findByDni(dni);
    }
    // ------------------------ READ ------------------------ //
    public Optional<Persona> findByUsername(String username){
        return personaRepository.findByUsername(username);
    }
    // ------------------------ READ ------------------------ //
    public List<Persona> readAll(){
        return personaRepository.findAll();
    }
    // ----------------------- UPDATE ----------------------- //
    public Persona update(Long id, Persona persona){
        Persona personaEdit = readById(id);
        
        if (personaRepository.existsByDniAndIdNot(persona.getDni(), id)) throw new BusinessException("DNI ya existe");
        if (personaRepository.existsByEmailAndIdNot(persona.getEmail(), id)) throw new BusinessException("Email ya existe");
        if (persona.getUsername() != null && personaRepository.existsByUsernameAndIdNot(persona.getUsername(), id))
            throw new BusinessException("Username ya existe");

        if(persona.getNombre() != null) personaEdit.setNombre(persona.getNombre());
        if(persona.getApellido() != null) personaEdit.setApellido(persona.getApellido());
        if(persona.getDni() != null) personaEdit.setDni(persona.getDni());
        if(persona.getEmail() != null) personaEdit.setEmail(persona.getEmail());
        if(persona.getUsername() != null) personaEdit.setUsername(persona.getUsername());
        if(persona.getPasswordHash() != null) personaEdit.setPasswordHash(persona.getPasswordHash());
        if(persona.getActivo() != persona.getActivo()) personaEdit.setActivo(persona.getActivo());
        if(persona.getRol() != null) personaEdit.setRol(persona.getRol());

        return personaRepository.save(personaEdit);
    }
    // ----------------------- DELETE ----------------------- //
    public void deleteById(Long id){
        if(!personaRepository.existsById(id)){
            throw new ResourceNotFoundException("Persona con id " + id + " no encontrada");
        }
        personaRepository.deleteById(id);
    }
}