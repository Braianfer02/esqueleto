package com.flashpage.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta objetoVenta){
        Venta ventaCreada = ventaService.crearVenta(objetoVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreada);
    };
    // ------------ READ ONE ------------ //
    @GetMapping("/read/{id}")
    public ResponseEntity<Venta> readOneVenta(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.readOneVenta(id));
    }
    @GetMapping("/read")
    // ------------ READ ALL ------------ //
    public ResponseEntity<List<Venta>> readAllVenta(){
        return ResponseEntity.ok(ventaService.readAllVenta());
    };
    // ---------- UPDATE ----------
    @PutMapping("/update/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id,@RequestBody Venta objetoVenta) {
        return ResponseEntity.ok(ventaService.updateVenta(id, objetoVenta));
    };
    // ------------- DELETE ------------- //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Venta> deleteVenta(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.deleteVenta(id));
    }
}