package com.flashpage.app.service;

import java.util.List;

import com.flashpage.app.model.Persona;

public interface IPersonaService {
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    public Persona crearPersona(Persona objetoPersona);
    // ------------ READ ONE ------------ //
    public Persona readOnePersona(Long id);
    // ------------ READ ALL ------------ //
    public List<Persona> readAllPersona();
    // ------------- UPDATE ------------- //
    public Persona updatePersona(Long id, Persona objetoPersona);
    // ------------- DELETE ------------- //
    public Persona deletePersona(Long id);
}
