package com.flashpage.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashpage.app.model.Persona;
import com.flashpage.app.service.IPersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    // ---------- IMPLEMENTAMOS ---------- //
    private final IPersonaService personaService;

    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }
    
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    @PostMapping("/create")
    public Persona crearPersona(@RequestBody Persona objetoPersona){
        return personaService.crearPersona(objetoPersona);
    };
    // ------------ READ ONE ------------ //
    @GetMapping("/read/{id}")
    public Persona readOnePersona(@PathVariable Long id){
        return personaService.readOnePersona(id);
    }
    // ------------ READ ALL ------------ //
    @GetMapping("/read")
    public List<Persona> readAllPersona(){
        return personaService.readAllPersona();
    };
    // ---------- UPDATE ----------
    @PutMapping("/update/{id}")
    public Persona updatePersona(
        @PathVariable Long id,@RequestBody Persona objetoPersona) {
        return personaService.updatePersona(id, objetoPersona);
    };
    // ------------- DELETE ------------- //
    @DeleteMapping("/delete/{id}")
    public Persona deletePersona(@PathVariable Long id){
        return personaService.deletePersona(id);
    }
}
