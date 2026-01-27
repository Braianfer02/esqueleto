package com.flashpage.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Venta {

    // Le asignamos ID unico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Colocamos datos del producto
    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private String precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id", nullable = false)
    private Persona Asesor;

    // Creamos: Constructor vacio, Constructor con parametros, Getter y Setter
    public Venta() {
    }

    public Venta(Long id, String producto, String precio, Persona asesor) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        Asesor = asesor;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Persona getAsesor() {
        return Asesor;
    }

    public void setAsesor(Persona asesor) {
        Asesor = asesor;
    }    
}
