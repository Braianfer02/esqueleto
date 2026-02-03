package com.flashpage.app.domain.service;

import java.util.List;

import com.flashpage.app.domain.model.Venta;

public interface VentaService {
    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    Venta create(Venta venta);
    // ------------------------ READ ------------------------ //
    Venta readById(Long id);
    // ------------------------ READ ------------------------ //
    List<Venta> readAll();
    // ----------------------- UPDATE ----------------------- //
    Venta update(Long id, Venta venta);
    // ----------------------- DELETE ----------------------- //
    void deleteById(Long id);
}
