package com.flashpage.app.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;
    
    @Column(nullable = false)
    private LocalDate fechaVenta;

    @ManyToOne
    @JoinColumn(name = "asesor_id", nullable = false)
    private Persona asesor;

    // Creamos: Constructor vacio, Constructor con parametros, Getter y Setter 

}
