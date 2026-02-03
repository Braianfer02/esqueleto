package com.flashpage.app.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Producto;
import com.flashpage.app.domain.repository.ProductoRepository;
import com.flashpage.app.domain.service.ProductoService;
import com.flashpage.app.exception.BusinessException;
import com.flashpage.app.exception.ResourceNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    public Producto create(Producto producto){
        if(productoRepository.existsByProducto(producto.getProducto()))
            throw new BusinessException("Producto ya existe");
        return productoRepository.save(producto);
    }
    // ------------------------ READ ------------------------ //
    public Producto readById(Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Prodcuto con id " + id + " no encontrada"));
    }
    // ------------------------ READ ------------------------ //
    public List<Producto> readAll(){
        return productoRepository.findAll();
    }
    // ----------------------- UPDATE ----------------------- //        
    public Producto update(Long id, Producto producto){

        Producto productoNuevo = readById(id);

        if (producto.getProducto() != null &&
            productoRepository.existsByProductoAndIdNot(producto.getProducto(), id)) {
            throw new BusinessException("Producto ya existe");
        }

        if (producto.getProducto() != null) productoNuevo.setProducto(producto.getProducto());
        if (producto.getDescripcion() != null) productoNuevo.setDescripcion(producto.getDescripcion());
        if (producto.getPrecio() != null) productoNuevo.setPrecio(producto.getPrecio());

        return productoRepository.save(productoNuevo);
    }
    // ----------------------- DELETE ----------------------- //
    public void deleteById(Long id){
        productoRepository.deleteById(id);
    }
}
