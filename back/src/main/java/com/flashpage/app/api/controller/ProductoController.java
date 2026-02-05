package com.flashpage.app.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flashpage.app.api.dto.ProductoDTO;
import com.flashpage.app.api.mapper.ProductoMapper;
import com.flashpage.app.domain.model.Producto;
import com.flashpage.app.domain.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // -------------------- METODOS CRUD -------------------- //
    // ----------------------- CREATE ----------------------- //
    @PostMapping
    public ResponseEntity<ProductoDTO.Response> create(@Valid @RequestBody ProductoDTO.Create req) {
        Producto producto = ProductoMapper.toEntity(req);
        Producto creado = productoService.create(producto);

        return ResponseEntity
                .created(URI.create("/productos/" + creado.getId()))
                .body(ProductoMapper.toResponse(creado));
    }

    // ------------------------ READ ------------------------ //
    @GetMapping
    public ResponseEntity<List<ProductoDTO.Response>> readAll() {
        List<ProductoDTO.Response> list = productoService.readAll()
                .stream()
                .map(ProductoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(list);
    }

    // ------------------------ READ ------------------------ //
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> readById(@PathVariable Long id) {
        Producto p = productoService.readById(id);
        return ResponseEntity.ok(ProductoMapper.toResponse(p));
    }

    // ----------------------- UPDATE ----------------------- //
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDTO.Response> update(@PathVariable Long id, @Valid @RequestBody ProductoDTO.Update req) {
        // tu service update hoy recibe Producto, así que armamos uno “parcial”
        Producto patch = new Producto();
        patch.setProducto(req.producto());
        patch.setDescripcion(req.descripcion());
        patch.setPrecio(req.precio());

        Producto actualizado = productoService.update(id, patch);
        return ResponseEntity.ok(ProductoMapper.toResponse(actualizado));
    }

    // ----------------------- DELETE ----------------------- //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
