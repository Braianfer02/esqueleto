package com.flashpage.app.dto;

import java.math.BigDecimal;
import com.flashpage.app.model.Venta.EstadoVenta;
import com.flashpage.app.model.Venta.MedioPago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
    
public class VentaRequestDTO {

    @NotBlank
    private String producto;

    private String descripcion;

    private String digitoTarjeta;

    @NotNull
    @PositiveOrZero
    private BigDecimal precio;

    @NotNull
    private EstadoVenta estado;

    private MedioPago medioPago;

    private String observaciones;

    @NotNull
    private Long asesorId;

    private Long clienteId;

    public VentaRequestDTO() {
    }

    public VentaRequestDTO(@NotBlank String producto, String descripcion, String digitoTarjeta, @NotNull @PositiveOrZero BigDecimal precio,
            @NotNull EstadoVenta estado, MedioPago medioPago, String observaciones, @NotNull Long asesorId,
            Long clienteId) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.digitoTarjeta = digitoTarjeta;
        this.precio = precio;
        this.estado = estado;
        this.medioPago = medioPago;
        this.observaciones = observaciones;
        this.asesorId = asesorId;
        this.clienteId = clienteId;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getDigitoTarjeta() {
        return digitoTarjeta;
    }

    public void setDigitoTarjeta(String digitoTarjeta) {
        this.digitoTarjeta = digitoTarjeta;
    }
}
