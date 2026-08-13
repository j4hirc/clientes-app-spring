package com.example_api.api_example.service.mapper;

import com.example_api.api_example.dto.ClienteRequestDTO;
import com.example_api.api_example.dto.ClienteResponseDTO;
import com.example_api.api_example.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

    public ClienteResponseDTO toDTO(Cliente entity) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setEmail(entity.getEmail());
        dto.setCreateAt(entity.getCreateAt() != null ? entity.getCreateAt().toString() : null);
        return dto;
    }
}