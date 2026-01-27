package com.flashpage.app.dto;

public class VentaRequestDTO {
    
    private String producto;
    private Double monto;

    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
