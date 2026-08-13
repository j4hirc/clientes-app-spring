package com.example_api.api_example.controller;

import com.example_api.api_example.dto.ProveedorRequestDTO;
import com.example_api.api_example.dto.ProveedorResponseDTO;
import com.example_api.api_example.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorResponseDTO> index() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(proveedorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> create(@RequestBody ProveedorRequestDTO proveedorRequest) {
        return new ResponseEntity<>(proveedorService.save(proveedorRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> update(@RequestBody ProveedorRequestDTO proveedorRequest, @PathVariable Long id) {
        return new ResponseEntity<>(proveedorService.update(id, proveedorRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        proveedorService.delete(id);
    }


    @PostMapping("/upload")
    public ResponseEntity<ProveedorResponseDTO> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        ProveedorResponseDTO proveedorActualizado = proveedorService.uploadFoto(archivo, id);
        return new ResponseEntity<>(proveedorActualizado, HttpStatus.CREATED);
    }
}