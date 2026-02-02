package com.flashpage.app.domain.service;

import java.util.List;
import com.flashpage.app.domain.model.Rol;

public interface IRolService {
    // ---------- METODOS CRUD ---------- //
    // ---------- METODO CREATE --------- //
    public Rol create(Rol objetoRol);
    // ---------- METODO READ ----------- //
    public Rol readById(Long id);
    // ---------- METODO READ ----------- //
    public List<Rol> readAll();
    // ---------- METODO UPDATE --------- //
    public Rol update(Rol objetoRol);
    // ---------- METODO DELETE --------- //
    public Rol delete(Long id);
}
