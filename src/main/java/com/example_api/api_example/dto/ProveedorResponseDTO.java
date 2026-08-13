package com.example_api.api_example.dto;
import lombok.Data;

@Data
public class ProveedorResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String foto_url;
}