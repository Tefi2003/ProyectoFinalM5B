package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Progreso;
import org.springframework.data.jpa.repository.Query;

public interface ProgresoRepository extends JpaRepository<Progreso, Integer>{
    @Query("SELECT MAX(a.id_progress) FROM Progreso a")
    Integer findMaxId();
}
