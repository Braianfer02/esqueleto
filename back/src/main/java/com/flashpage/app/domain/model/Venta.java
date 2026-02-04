package com.flashpage.app.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @Column(length = 16)
    private String digitoTarjeta;

    @Column(length = 1000)
    private String observaciones;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id", nullable = false)
    private Persona asesor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Persona cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaItem> items;

    public enum EstadoVenta {
        SIN_ESTADO,
        CANCELADO,
        RECHAZADO,
        PENDIENTE,
        COMPLETADO,
    }

    public enum MedioPago {
        EFECTIVO,
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime timeNow = LocalDateTime.now();
        this.createdAt = timeNow;
        this.updatedAt = timeNow;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDigitoTarjeta() {
        return digitoTarjeta;
    }

    public void setDigitoTarjeta(String digitoTarjeta) {
        this.digitoTarjeta = digitoTarjeta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public List<VentaItem> getItems() {
        return items;
    }

    public void setItems(List<VentaItem> items) {
        this.items = items;
    }
}