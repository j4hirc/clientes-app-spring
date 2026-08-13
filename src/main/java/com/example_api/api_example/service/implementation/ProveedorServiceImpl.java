package com.example_api.api_example.service.implementation;
import com.example_api.api_example.dto.ProveedorRequestDTO;
import com.example_api.api_example.dto.ProveedorResponseDTO;
import com.example_api.api_example.model.Proveedor;
import com.example_api.api_example.repository.ProveedorRepository;
import com.example_api.api_example.service.ProveedorService;
import com.example_api.api_example.service.mapper.ProveedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.bucket}")
    private String supabaseBucket;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> findAll() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorResponseDTO findById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return proveedorMapper.toDTO(proveedor);
    }

    @Override
    @Transactional
    public ProveedorResponseDTO save(ProveedorRequestDTO proveedorRequest) {
        Proveedor proveedor = proveedorMapper.toEntity(proveedorRequest);
        return proveedorMapper.toDTO(proveedorRepository.save(proveedor));
    }

    @Override
    @Transactional
    public ProveedorResponseDTO update(Long id, ProveedorRequestDTO proveedorRequest) {
        Proveedor proveedorActual = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        proveedorActual.setNombre(proveedorRequest.getNombre());
        proveedorActual.setDescripcion(proveedorRequest.getDescripcion());
        proveedorActual.setFoto_url(proveedorRequest.getFoto_url());

        return proveedorMapper.toDTO(proveedorRepository.save(proveedorActual));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProveedorResponseDTO uploadFoto(MultipartFile archivo, Long id) {
        Proveedor proveedorActual = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        if (!archivo.isEmpty()) {
            try {
                String originalFilename = archivo.getOriginalFilename();
                String extension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                String nombreArchivo = UUID.randomUUID().toString() + extension;

                String urlUpload = supabaseUrl + "/storage/v1/object/" + supabaseBucket + "/" + nombreArchivo;

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + supabaseKey);
                headers.set("apikey", supabaseKey);
                headers.setContentType(MediaType.valueOf(archivo.getContentType() != null ? archivo.getContentType() : "application/octet-stream"));

                HttpEntity<byte[]> requestEntity = new HttpEntity<>(archivo.getBytes(), headers);

                ResponseEntity<String> response = restTemplate.exchange(urlUpload, HttpMethod.POST, requestEntity, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    String urlPublica = supabaseUrl + "/storage/v1/object/public/" + supabaseBucket + "/" + nombreArchivo;

                    proveedorActual.setFoto_url(urlPublica);
                    Proveedor proveedorGuardado = proveedorRepository.save(proveedorActual);

                    System.out.println("Archivo guardado en Supabase Bucket: " + urlPublica);
                    return proveedorMapper.toDTO(proveedorGuardado);
                } else {
                    throw new RuntimeException("Error al subir archivo a Supabase: " + response.getBody());
                }

            } catch (IOException e) {
                throw new RuntimeException("Error al procesar el archivo: " + e.getMessage());
            }
        }

        return proveedorMapper.toDTO(proveedorActual);
    }
}