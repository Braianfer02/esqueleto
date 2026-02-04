package com.flashpage.app.security.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Persona.Rol;
import com.flashpage.app.domain.repository.PersonaRepository;

@Component
public class SecuritySeed implements CommandLineRunner {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public SecuritySeed(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // seed mínimo: DUENO si no existe
        String username = "admin";
        if (personaRepository.findByUsername(username).isPresent()) return;

        Persona objetPersona = new Persona();
        objetPersona.setNombre("Admin");
        objetPersona.setApellido("System");
        objetPersona.setDni("00000000");
        objetPersona.setEmail("admin@local");
        objetPersona.setUsername(username);
        objetPersona.setPasswordHash(passwordEncoder.encode("admin123"));
        objetPersona.setActivo(true);
        objetPersona.setRol(Rol.DUENO);

        personaRepository.save(objetPersona);
    }
}
