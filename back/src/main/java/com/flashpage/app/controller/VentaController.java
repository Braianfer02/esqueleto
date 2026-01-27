package com.flashpage.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashpage.app.model.Venta;
import com.flashpage.app.service.IVentaService;

@RestController
@RequestMapping("/api/venta")
public class VentaController {
    
    // ---------- IMPLEMENTAMOS ---------- //
    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }
    
    // ---------- METODOS CRUD ---------- //
    // ------------- CREATE ------------- //
    @PostMapping("/create")
    public Venta crearVenta(@RequestBody Venta objetoVenta){
        return ventaService.crearVenta(objetoVenta);
    };
    // ------------ READ ONE ------------ //
    @GetMapping("/read/{id}")
    public Venta readOneVenta(@PathVariable Long id){
        return ventaService.readOneVenta(id);
    }
    @GetMapping("/read")
    // ------------ READ ALL ------------ //
    public List<Venta> readAllVenta(){
        return ventaService.readAllVenta();
    };
    // ---------- UPDATE ----------
    @PutMapping("/update/{id}")
    public Venta updateVenta(@PathVariable Long id,@RequestBody Venta objetoVenta) {
        return ventaService.updateVenta(id, objetoVenta);
    };
    // ------------- DELETE ------------- //
    @DeleteMapping("/delete/{id}")
    public Venta deleteVenta(@PathVariable Long id){
        return ventaService.deleteVenta(id);
    }
}