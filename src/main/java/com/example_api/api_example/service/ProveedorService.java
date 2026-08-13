package com.example_api.api_example.service;

import com.example_api.api_example.dto.ProveedorRequestDTO;
import com.example_api.api_example.dto.ProveedorResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProveedorService {
    List<ProveedorResponseDTO> findAll();
    ProveedorResponseDTO findById(Long id);
    ProveedorResponseDTO save(ProveedorRequestDTO proveedorRequest);
    ProveedorResponseDTO update(Long id, ProveedorRequestDTO proveedorRequest);
    void delete(Long id);
    ProveedorResponseDTO uploadFoto(MultipartFile archivo, Long id);
}