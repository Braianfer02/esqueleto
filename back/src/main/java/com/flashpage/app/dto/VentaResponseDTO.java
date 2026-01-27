package com.flashpage.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.flashpage.app.model.Venta.EstadoVenta;
import com.flashpage.app.model.Venta.MedioPago;

public class VentaResponseDTO {

    private Long id;
    private String producto;
    private String descripcion;
    private BigDecimal precio;
    private LocalDate fechaVenta;
    private EstadoVenta estado;
    private MedioPago medioPago;
    private String observaciones;

    private Long asesorId;
    private String asesorNombre;

    private Long clienteId;
    private String clienteNombre;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    public EstadoVenta getEstado() {
        return estado;
    }
    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }
    public MedioPago getMedioPago() {
        return medioPago;
    }
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Long getAsesorId() {
        return asesorId;
    }
    public void setAsesorId(Long asesorId) {
        this.asesorId = asesorId;
    }
    public String getAsesorNombre() {
        return asesorNombre;
    }
    public void setAsesorNombre(String asesorNombre) {
        this.asesorNombre = asesorNombre;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public String getClienteNombre() {
        return clienteNombre;
    }
    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }
}
