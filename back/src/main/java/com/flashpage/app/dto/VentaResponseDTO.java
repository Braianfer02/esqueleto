package com.flashpage.app.dto;

import java.math.BigDecimal;

public class VentaResponseDTO {
    private Long id;
    private String producto;
    private BigDecimal precio;

    private Long asesorId;
    private String asesorNombre;

    private Long clienteId;
    private String clienteNombre;
    
    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, String producto, BigDecimal precio, Long asesorId, String asesorNombre,
            Long clienteId, String clienteNombre) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.asesorId = asesorId;
        this.asesorNombre = asesorNombre;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
    }

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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
