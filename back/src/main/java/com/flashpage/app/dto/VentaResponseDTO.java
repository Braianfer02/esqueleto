package com.flashpage.app.dto;

import java.time.LocalDate;

public class VentaResponseDTO {
    
    private Long id;
    private String cliente;
    private LocalDate fecha;
    private Long asesorId;
    private String nombreAsesor;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Long getAsesorId() {
        return asesorId;
    }
    public void setAsesorId(Long asesorId) {
        this.asesorId = asesorId;
    }
    public String getNombreAsesor() {
        return nombreAsesor;
    }
    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

}
