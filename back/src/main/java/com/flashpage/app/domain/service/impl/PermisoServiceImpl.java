package com.flashpage.app.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Permiso;
import com.flashpage.app.domain.repository.PermisoRepository;
import com.flashpage.app.domain.service.IPermisoService;
import com.flashpage.app.exception.ResourceNotFoundException;

@Service
public class PermisoServiceImpl implements IPermisoService {

    private PermisoRepository permisoRepository;

    public PermisoServiceImpl(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    @Override
    public Permiso create(Permiso objetoPermiso) {
        if (permisoRepository.existsByCodigo(objetoPermiso.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un permiso con código: " + objetoPermiso.getCodigo());
        }
        return permisoRepository.save(objetoPermiso);
    }

    @Override
    public Permiso readById(Long id) {
        return permisoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado"));
    }

    @Override
    public List<Permiso> readAll() {
        return permisoRepository.findAll();
    }

    @Override
    public Permiso update(Permiso objetoPermiso) {
        Permiso permisoExistente = permisoRepository.findById(objetoPermiso.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado"));
        permisoExistente.setCodigo(objetoPermiso.getCodigo());
        permisoExistente.setDescripcion(objetoPermiso.getDescripcion());
        return permisoRepository.save(permisoExistente);
    }

    @Override
    public Permiso delete(Long id) {
        Permiso permisoExistente = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado"));
        permisoRepository.delete(permisoExistente);
        return permisoExistente;
    }
    
}
