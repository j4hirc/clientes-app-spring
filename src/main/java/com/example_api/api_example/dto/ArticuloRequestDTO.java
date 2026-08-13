package com.example_api.api_example.dto;
import lombok.Data;

@Data
public class ArticuloRequestDTO {
    private String codigo;
    private String nombre;
    private String categoria;
}