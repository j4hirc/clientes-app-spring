package com.example_api.api_example.repository;

import com.example_api.api_example.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    @Query("SELECT a FROM Articulo a WHERE " +
            "LOWER(a.codigo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
            "LOWER(a.categoria) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Articulo> buscarPorTodo(@Param("termino") String termino);

}