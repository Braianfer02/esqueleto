package com.flashpage.app.service;

import java.util.List;

import com.flashpage.app.model.Venta;

public interface IVentaService {
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    public Venta crearVenta(Venta objetoVenta);
    // ------------ READ ONE ------------ //
    public Venta readOneVenta(Long id);
    // ------------ READ ALL ------------ //
    public List<Venta> readAllVenta();
    // ------------- UPDATE ------------- //
    public Venta updateVenta(Long id, Venta objetoVenta);
    // ------------- DELETE ------------- //
    public Venta deleteVenta(Long id);
}
