package com.flashpage.app.domain.service.impl;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.repository.PersonaRepository;
import com.flashpage.app.domain.service.PersonaService;
import com.flashpage.app.exception.ResourceNotFoundException;

public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    public Persona create(Persona persona) {
        return personaRepository.save(persona);
    }
    // ------------------------ READ ------------------------ //
    public Persona readById(Long id){
        return personaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Persona con id " + id + " no encontrada"));
    }
    // ------------------------ READ ------------------------ //
    // ----------------------- UPDATE ----------------------- //
    // ----------------------- DELETE ----------------------- //
}