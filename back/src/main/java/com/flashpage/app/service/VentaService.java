package com.flashpage.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.exception.ResourceNotFoundException;
import com.flashpage.app.model.Venta;
import com.flashpage.app.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService implements IVentaService{
    
    // ---------- IMPLEMENTAMOS ---------- //
    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }
    
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    public Venta crearVenta(Venta objetoVenta){
        return ventaRepository.save(objetoVenta);
    };
    // ------------ READ ONE ------------ //

    public Venta readOneVenta(Long id){
        return ventaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Venta con id " + id + " no encontrada"
            ));
    }
    // ------------ READ ALL ------------ //
    public List<Venta> readAllVenta(){
        return ventaRepository.findAll();
    };
    // ---------- UPDATE ----------
    @Transactional
    public Venta updateVenta(Long id, Venta objetoVenta) {

        Venta ventaExistente = readOneVenta(id);

        ventaExistente.setProducto(objetoVenta.getProducto());
        ventaExistente.setPrecio(objetoVenta.getPrecio());
        ventaExistente.setAsesor(objetoVenta.getAsesor());


        return ventaRepository.save(ventaExistente);
    };
    // ------------- DELETE ------------- //
    public Venta deleteVenta(Long id){
        Venta objetoVenta = readOneVenta(id);
        ventaRepository.deleteById(id);
        return objetoVenta;
    }
}
