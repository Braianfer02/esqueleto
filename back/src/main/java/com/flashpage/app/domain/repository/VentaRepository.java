package com.flashpage.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.domain.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {}