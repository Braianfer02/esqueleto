package com.flashpage.app.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Rol;
import com.flashpage.app.domain.repository.RolRepository;
import com.flashpage.app.domain.service.IRolService;
import com.flashpage.app.exception.ResourceNotFoundException;

@Service
public class RolServiceImpl implements IRolService {
    
    private RolRepository rolRepository;
    
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // ---------- METODOS CRUD ---------- //
    // ---------- METODO CREATE --------- //
    @Override
    public Rol create(Rol objetoRol){
        return rolRepository.save(objetoRol);
    }
    // ---------- METODO READ ----------- //
    @Override
    public Rol readById(Long id){
        return rolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

    }
    // ---------- METODO READ ----------- //
    @Override
    public List<Rol> readAll(){
        return rolRepository.findAll();
    }
    // ---------- METODO UPDATE --------- //
    @Override
    public Rol update(Rol objetoRol){
        Rol rolExistente = rolRepository.findById(objetoRol.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        rolExistente.setNombre(objetoRol.getNombre());
        rolExistente.setDescripcion(objetoRol.getDescripcion());
        return rolRepository.save(rolExistente);
    }
    // ---------- METODO DELETE --------- //
    @Override
    public Rol delete(Long id){
        Rol rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
        rolRepository.delete(rolExistente);
        return rolExistente;
    }
}
