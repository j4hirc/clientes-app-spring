package com.example_api.api_example.service.implementation;

import com.example_api.api_example.dto.ArticuloRequestDTO;
import com.example_api.api_example.dto.ArticuloResponseDTO;
import com.example_api.api_example.model.Articulo;
import com.example_api.api_example.model.Proveedor;
import com.example_api.api_example.repository.ArticuloRepository;
import com.example_api.api_example.repository.ProveedorRepository;
import com.example_api.api_example.service.ArticuloService;
import com.example_api.api_example.service.mapper.ArticuloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ArticuloMapper articuloMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ArticuloResponseDTO> findAll() {
        return articuloRepository.findAll().stream()
                .map(articuloMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ArticuloResponseDTO findById(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
        return articuloMapper.toDTO(articulo);
    }

    @Override
    @Transactional
    public ArticuloResponseDTO save(ArticuloRequestDTO articuloRequest) {
        // Obtenemos el proveedor usando el ID que viene del Frontend
        Proveedor proveedor = proveedorRepository.findById(articuloRequest.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        Articulo articulo = articuloMapper.toEntity(articuloRequest, proveedor);
        return articuloMapper.toDTO(articuloRepository.save(articulo));
    }

    @Override
    @Transactional
    public ArticuloResponseDTO update(Long id, ArticuloRequestDTO articuloRequest) {
        Articulo articuloActual = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        Proveedor proveedor = proveedorRepository.findById(articuloRequest.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        articuloActual.setCodigo(articuloRequest.getCodigo());
        articuloActual.setNombre(articuloRequest.getNombre());
        articuloActual.setCategoria(articuloRequest.getCategoria());
        articuloActual.setProveedor(proveedor);

        return articuloMapper.toDTO(articuloRepository.save(articuloActual));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        articuloRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticuloResponseDTO> buscar(String termino) {
        return articuloRepository.buscarPorTodo(termino)
                .stream()
                .map(articuloMapper::toDTO)
                .collect(Collectors.toList());
    }
}