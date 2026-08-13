package com.example_api.api_example.service;

import com.example_api.api_example.dto.ArticuloRequestDTO;
import com.example_api.api_example.dto.ArticuloResponseDTO;
import java.util.List;

public interface ArticuloService {
    List<ArticuloResponseDTO> findAll();
    ArticuloResponseDTO findById(Long id);
    ArticuloResponseDTO save(ArticuloRequestDTO articuloRequest);
    ArticuloResponseDTO update(Long id, ArticuloRequestDTO articuloRequest);
    void delete(Long id);

    List<ArticuloResponseDTO> buscar(String termino);
}