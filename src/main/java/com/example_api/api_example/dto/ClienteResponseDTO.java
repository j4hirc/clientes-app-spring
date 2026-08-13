package com.example_api.api_example.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String createAt;
}
