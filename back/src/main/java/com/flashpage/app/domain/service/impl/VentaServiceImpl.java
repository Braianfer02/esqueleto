package com.flashpage.app.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flashpage.app.domain.model.Persona;
import com.flashpage.app.domain.model.Producto;
import com.flashpage.app.domain.model.Venta;
import com.flashpage.app.domain.repository.PersonaRepository;
import com.flashpage.app.domain.repository.ProductoRepository;
import com.flashpage.app.domain.repository.VentaRepository;
import com.flashpage.app.domain.service.VentaService;
import com.flashpage.app.exception.BusinessException;
import com.flashpage.app.exception.ResourceNotFoundException;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final PersonaRepository personaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoRepository productoRepository, PersonaRepository personaRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.personaRepository = personaRepository;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    public Venta create(Venta venta) {

        if (venta.getAsesor() == null)
            throw new BusinessException("La venta requiere un asesor");
        Persona asesor = personaRepository.findById(venta.getAsesor().getId())
                .orElseThrow(() -> new BusinessException("Asesor no encontrado"));

        if (asesor.getRol() == Persona.Rol.CLIENTE)
            throw new BusinessException("El asesor no puede ser un cliente");

        venta.setAsesor(asesor);
        if (venta.getItems() == null || venta.getItems().isEmpty())
            throw new BusinessException("La venta debe tener al menos un item");
        if (venta.getEstado() == null) {
            venta.setEstado(Venta.EstadoVenta.SIN_ESTADO);
        }
        if (venta.getCliente() != null && venta.getCliente().getId() != null) {
            Persona cliente = personaRepository.findById(venta.getCliente().getId())
                    .orElseThrow(() -> new BusinessException("Cliente no encontrado"));
            venta.setCliente(cliente);
        }
        venta.getItems().forEach(item -> {

            if (item.getCantidad() == null || item.getCantidad() <= 0)
                throw new BusinessException("Cantidad inválida");

            if (item.getProducto() == null || item.getProducto().getId() == null)
                throw new BusinessException("Producto inválido");

            // Buscar producto real en BD
            Producto producto = productoRepository.findById(item.getProducto().getId())
                    .orElseThrow(() -> new BusinessException(
                            "Producto con id " + item.getProducto().getId() + " no encontrado"));

            // Congelar precio actual
            item.setPrecioUnitario(producto.getPrecio());

            // Vincular item con la venta
            item.setVenta(venta);
        });
        BigDecimal total = venta.getItems().stream()
                .map(item -> item.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }
    // ------------------------ READ ------------------------ //
    public Venta readById(Long id){
        return ventaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Venta con id " + id + " no encontrada"));
    }
    // ------------------------ READ ------------------------ //
    public List<Venta> readAll(){
        return ventaRepository.findAll();
    }
    // ----------------------- UPDATE ----------------------- //
    public Venta update(Long id, Venta venta) {
        Venta existente = readById(id);

        if (venta.getEstado() != null) existente.setEstado(venta.getEstado());
        if (venta.getMedioPago() != null) existente.setMedioPago(venta.getMedioPago());
        if (venta.getDigitoTarjeta() != null) existente.setDigitoTarjeta(venta.getDigitoTarjeta());
        if (venta.getObservaciones() != null) existente.setObservaciones(venta.getObservaciones());

        // asesor/cliente: si querés permitirlo, ok, pero pensalo (auditoría)
        if (venta.getCliente() != null) existente.setCliente(venta.getCliente());

        // NO: existente.setItems(...)
        return ventaRepository.save(existente);
    }
    // ----------------------- DELETE ----------------------- //
    public void deleteById(Long id) {
        if (!ventaRepository.existsById(id))
            throw new ResourceNotFoundException("Venta con id " + id + " no encontrada");
        ventaRepository.deleteById(id);
    }
}
