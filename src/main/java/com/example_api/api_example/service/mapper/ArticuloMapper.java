package com.example_api.api_example.service.mapper;

import com.example_api.api_example.dto.ArticuloRequestDTO;
import com.example_api.api_example.dto.ArticuloResponseDTO;
import com.example_api.api_example.model.Articulo;
import com.example_api.api_example.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ArticuloMapper {

    public Articulo toEntity(ArticuloRequestDTO dto, Proveedor proveedor) {
        Articulo articulo = new Articulo();
        articulo.setCodigo(dto.getCodigo());
        articulo.setNombre(dto.getNombre());
        articulo.setCategoria(dto.getCategoria());
        articulo.setProveedor(proveedor);
        return articulo;
    }

    public ArticuloResponseDTO toDTO(Articulo articulo) {
        ArticuloResponseDTO dto = new ArticuloResponseDTO();
        dto.setId(articulo.getId());
        dto.setCodigo(articulo.getCodigo());
        dto.setNombre(articulo.getNombre());
        dto.setCategoria(articulo.getCategoria());

        if (articulo.getProveedor() != null) {
            dto.setProveedor(articulo.getProveedor());
        }

        return dto;
    }
}