package com.flashpage.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashpage.app.domain.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByProducto(String producto);
    boolean existsByProductoAndIdNot(String producto, Long id);
}