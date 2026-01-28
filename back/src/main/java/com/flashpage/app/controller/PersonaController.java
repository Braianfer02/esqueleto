package com.flashpage.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.flashpage.app.dto.PersonaRequestDTO;
import com.flashpage.app.dto.PersonaResponseDTO;
import com.flashpage.app.model.Persona.Rol;
import com.flashpage.app.service.IPersonaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final IPersonaService personaService;

    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    // -------- CREATE --------
    @PostMapping("/create")
    public PersonaResponseDTO crearPersona(@Valid @RequestBody PersonaRequestDTO dto) {
        return personaService.crearPersona(dto);
    }

    // -------- READ ONE --------
    @GetMapping("/read/one/{id}")
    public PersonaResponseDTO obtenerPersona(@PathVariable Long id) {
        return personaService.readOnePersona(id);
    }

    // -------- READ ALL --------
    @GetMapping("/read")
    public List<PersonaResponseDTO> obtenerTodas() {
        return personaService.readAllPersona();
    }

    // -------- READ BY ROL --------
    @GetMapping("/read/rol/{rol}")
    public List<PersonaResponseDTO> obtenerPorRol(@PathVariable Rol rol) {
        return personaService.readAllRol(rol);
    }

    // -------- UPDATE --------
    @PutMapping("/update/{id}")
    public PersonaResponseDTO actualizarPersona(
            @PathVariable Long id,
            @Valid @RequestBody PersonaRequestDTO dto) {
        return personaService.updatePersona(id, dto);
    }

    // -------- DELETE --------
    @DeleteMapping("/delete/{id}")
    public PersonaResponseDTO eliminarPersona(@PathVariable Long id) {
        return personaService.deletePersona(id);
    }
}
