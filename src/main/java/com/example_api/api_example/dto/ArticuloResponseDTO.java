package com.example_api.api_example.dto;
import lombok.Data;

@Data
public class ArticuloResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String categoria;
    private Long proveedorId;
}