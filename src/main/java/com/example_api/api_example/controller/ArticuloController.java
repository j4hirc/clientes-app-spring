package com.example_api.api_example.controller;

import com.example_api.api_example.dto.ArticuloRequestDTO;
import com.example_api.api_example.dto.ArticuloResponseDTO;
import com.example_api.api_example.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<ArticuloResponseDTO> index() {
        return articuloService.findAll();
    }

    @GetMapping("/buscar")
    public List<ArticuloResponseDTO> buscar(@RequestParam String termino) {
        return articuloService.buscar(termino);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloResponseDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(articuloService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticuloResponseDTO> create(@RequestBody ArticuloRequestDTO articuloRequest) {
        return new ResponseEntity<>(articuloService.save(articuloRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloResponseDTO> update(@RequestBody ArticuloRequestDTO articuloRequest, @PathVariable Long id) {
        return new ResponseEntity<>(articuloService.update(id, articuloRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        articuloService.delete(id);
    }
}