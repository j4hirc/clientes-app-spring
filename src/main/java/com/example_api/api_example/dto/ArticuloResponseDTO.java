package com.example_api.api_example.dto;
import com.example_api.api_example.model.Proveedor;
import lombok.Data;

@Data
public class ArticuloResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String categoria;
    private Proveedor proveedor;
}