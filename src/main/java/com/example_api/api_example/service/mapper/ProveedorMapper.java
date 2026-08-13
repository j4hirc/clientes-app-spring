package com.example_api.api_example.service.mapper;

import com.example_api.api_example.dto.ProveedorRequestDTO;
import com.example_api.api_example.dto.ProveedorResponseDTO;
import com.example_api.api_example.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor toEntity(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setDescripcion(dto.getDescripcion());
        proveedor.setFoto_url(dto.getFoto_url());
        return proveedor;
    }

    public ProveedorResponseDTO toDTO(Proveedor proveedor) {
        ProveedorResponseDTO dto = new ProveedorResponseDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setDescripcion(proveedor.getDescripcion());
        dto.setFoto_url(proveedor.getFoto_url());
        return dto;
    }
}