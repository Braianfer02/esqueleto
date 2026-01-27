package com.flashpage.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{}
