package com.flashpage.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.model.Persona;
import com.flashpage.app.model.Persona.Rol;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    List<Persona> findByRol(Rol rol);
}
