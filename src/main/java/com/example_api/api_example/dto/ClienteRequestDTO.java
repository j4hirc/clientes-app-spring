package com.example_api.api_example.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nombre;
    private String apellido;
    private String email;
}