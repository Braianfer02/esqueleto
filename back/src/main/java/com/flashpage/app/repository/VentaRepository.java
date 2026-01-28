package com.flashpage.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    List<Venta> findByAsesorId(Long asesorId);
    List<Venta> findByClienteId(Long clienteId);
}
