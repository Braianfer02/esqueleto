package com.flashpage.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.model.Persona;
import com.flashpage.app.repository.PersonaRepository;
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
    public Persona crearPersona(Persona objetoPersona){
        return personaRepository.save(objetoPersona);
    };
    // ------------ READ ONE ------------ //

    public Persona readOnePersona(Long id){
        return personaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Persona con id " + id + " no encontrada"
            ));
    }
    // ------------ READ ALL ------------ //
    public List<Persona> readAllPersona(){
        return personaRepository.findAll();
    };
    // ---------- UPDATE ----------
    @Transactional
    public Persona updatePersona(Long id, Persona objetoPersona) {

        Persona personaExistente = readOnePersona(id);

        personaExistente.setNombre(objetoPersona.getNombre());
        personaExistente.setApellido(objetoPersona.getApellido());
        personaExistente.setDni(objetoPersona.getDni());
        personaExistente.setTelefono(objetoPersona.getTelefono());
        personaExistente.setRol(objetoPersona.getRol());
        personaExistente.setDireccion(objetoPersona.getDireccion());
        personaExistente.setLocalidad(objetoPersona.getLocalidad());
        personaExistente.setProvincia(objetoPersona.getProvincia());
        personaExistente.setCodigoPostal(objetoPersona.getCodigoPostal());
        personaExistente.setPais(objetoPersona.getPais());
        personaExistente.setUsuario(objetoPersona.getUsuario());
        personaExistente.setContraseña(objetoPersona.getContraseña());

        return personaRepository.save(personaExistente);
    };
    // ------------- DELETE ------------- //
    public Persona deletePersona(Long id){
        Persona objetoPersona = readOnePersona(id);
        personaRepository.deleteById(id);
        return objetoPersona;
    }
}
