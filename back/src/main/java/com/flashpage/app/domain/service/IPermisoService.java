package com.flashpage.app.domain.service;

import java.util.List;

import com.flashpage.app.domain.model.Permiso;

public interface IPermisoService {
    // ---------- METODOS CRUD ---------- //
    // ---------- METODO CREATE --------- //
    public Permiso create(Permiso objetoPermiso);
    // ---------- METODO READ ----------- //
    public Permiso readById(Long id);
    // ---------- METODO READ ----------- //
    public List<Permiso> readAll();
    // ---------- METODO UPDATE --------- //
    public Permiso update(Permiso objetoPermiso);
    // ---------- METODO DELETE --------- //
    public Permiso delete(Long id);
}
