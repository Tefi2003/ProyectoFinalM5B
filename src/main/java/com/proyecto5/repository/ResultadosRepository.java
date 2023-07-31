package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Resultados;
import org.springframework.data.jpa.repository.Query;

public interface ResultadosRepository extends JpaRepository<Resultados, Integer>{
    @Query("SELECT MAX(a.id_resultado) FROM Resultados a")
    Integer findMaxId();
}
