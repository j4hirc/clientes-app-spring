package com.example_api.api_example.service;

import com.example_api.api_example.dto.ClienteRequestDTO;
import com.example_api.api_example.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> findAll();
    ClienteResponseDTO findById(Long id);
    ClienteResponseDTO save(ClienteRequestDTO clienteRequest);
    ClienteResponseDTO update(Long id, ClienteRequestDTO clienteRequest);
    void delete(Long id);
}
