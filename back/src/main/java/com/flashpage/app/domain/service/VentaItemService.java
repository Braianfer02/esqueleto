package com.flashpage.app.domain.service;

import java.util.List;

import com.flashpage.app.domain.model.VentaItem;

public interface VentaItemService {
    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    VentaItem create(VentaItem ventaItem);
    // ------------------------ READ ------------------------ //
    VentaItem readById(Long id);
    // ------------------------ READ ------------------------ //
    List<VentaItem> readAll();
    // ----------------------- UPDATE ----------------------- //
    VentaItem update(Long id, VentaItem ventaItem);
    // ----------------------- DELETE ----------------------- //
    void deleteById(Long id);
}
