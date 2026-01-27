package com.flashpage.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto;

    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private LocalDate fechaVenta;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @Column(length = 1000)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id", nullable = false)
    private Persona asesor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Persona cliente;

    public Venta() {
    }

    public Venta(Long id, String producto, String descripcion, BigDecimal precio, LocalDate fechaVenta,
            LocalDateTime createdAt, LocalDateTime updatedAt, EstadoVenta estado, MedioPago medioPago,
            String observaciones, Persona asesor, Persona cliente) {
        this.id = id;
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaVenta = fechaVenta;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.estado = estado;
        this.medioPago = medioPago;
        this.observaciones = observaciones;
        this.asesor = asesor;
        this.cliente = cliente;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.fechaVenta = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum EstadoVenta {
        Cancelada,
        Rechazada,
        Pendiente,
        Ticket,
        Preventa,
        Suspendida,
        Auditada,
        Cumplido,
    }

    public enum MedioPago {
        Efectivo,
        Tarjeta,
        Transferencia,
        Qr,
        Deposito,
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public Persona getAsesor() {
        return asesor;
    }

    public void setAsesor(Persona asesor) {
        this.asesor = asesor;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }
}
