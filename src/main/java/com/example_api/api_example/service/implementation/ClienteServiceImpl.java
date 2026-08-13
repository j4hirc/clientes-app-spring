package com.example_api.api_example.service.implementation;


import com.example_api.api_example.dto.ClienteRequestDTO;
import com.example_api.api_example.dto.ClienteResponseDTO;
import com.example_api.api_example.model.Cliente;
import com.example_api.api_example.repository.ClienteRepository;
import com.example_api.api_example.service.ClienteService;
import com.example_api.api_example.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO save(ClienteRequestDTO clienteRequest) {
        Cliente cliente = clienteMapper.toEntity(clienteRequest);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(clienteGuardado);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteRequestDTO clienteRequest) {
        Cliente clienteActual = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteActual.setNombre(clienteRequest.getNombre());
        clienteActual.setApellido(clienteRequest.getApellido());
        clienteActual.setEmail(clienteRequest.getEmail());

        Cliente clienteActualizado = clienteRepository.save(clienteActual);
        return clienteMapper.toDTO(clienteActualizado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
