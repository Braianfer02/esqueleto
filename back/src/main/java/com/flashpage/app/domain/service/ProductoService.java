package com.flashpage.app.domain.service;

import java.util.List;

import com.flashpage.app.domain.model.Producto;

public interface ProductoService {
    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    Producto create(Producto producto);
    // ------------------------ READ ------------------------ //
    Producto readById(Long id);
    // ------------------------ READ ------------------------ //
    List<Producto> readAll();
    // ----------------------- UPDATE ----------------------- //
    Producto update(Long id, Producto producto);
    // ----------------------- DELETE ----------------------- //
    void deleteById(Long id);
}
