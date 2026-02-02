package com.flashpage.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flashpage.app.domain.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    public Persona create(Persona persona);
    // ------------------------ READ ------------------------ //
    public Persona readById(Long id);
    // ------------------------ READ ------------------------ //
    // ----------------------- UPDATE ----------------------- //
    // ----------------------- DELETE ----------------------- //
}