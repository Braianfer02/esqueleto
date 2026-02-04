package com.flashpage.app.api.mapper;

import com.flashpage.app.api.dto.ProductoDTO;
import com.flashpage.app.domain.model.Producto;

public class ProductoMapper {
    public static ProductoDTO.Response toResponse(Producto objetoProducto) {
        return new ProductoDTO.Response(
            objetoProducto.getId(),
            objetoProducto.getProducto(),
            objetoProducto.getDescripcion(),
            objetoProducto.getPrecio(),
            objetoProducto.getCreatedAt(),
            objetoProducto.getUpdatedAt()
        );
    }

    public static Producto toEntity(ProductoDTO.Create dto) {
        Producto objetoProducto = new Producto();
        objetoProducto.setProducto(dto.producto());
        objetoProducto.setDescripcion(dto.descripcion());
        objetoProducto.setPrecio(dto.precio());
        return objetoProducto;
    }

    public static void applyUpdate(ProductoDTO.Update dto, Producto objetoProducto) {
        if (dto.producto() != null) objetoProducto.setProducto(dto.producto());
        if (dto.descripcion() != null) objetoProducto.setDescripcion(dto.descripcion());
        if (dto.precio() != null) objetoProducto.setPrecio(dto.precio());
    }

}
