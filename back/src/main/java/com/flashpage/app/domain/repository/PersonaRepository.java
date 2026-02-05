package com.flashpage.app.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.domain.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDni(String dni);
    boolean existsByDni(String dni);

    Optional<Persona> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Persona> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByDniAndIdNot(String dni, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByUsernameAndIdNot(String username, Long id);
}