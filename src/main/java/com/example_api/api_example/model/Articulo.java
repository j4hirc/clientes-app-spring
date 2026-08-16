    package com.example_api.api_example.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "articulos")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Articulo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String codigo;
        private String nombre;
        private String categoria;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "proveedor_id")
        private Proveedor proveedor;

    }
