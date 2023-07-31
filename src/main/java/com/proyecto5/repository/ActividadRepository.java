package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Actividad;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ActividadRepository extends JpaRepository<Actividad, Integer>{
    @Query("SELECT MAX(a.id_activ) FROM Actividad a")
    Integer findMaxId();

    @Query("SELECT a FROM Actividad a WHERE a.tipoAprendizaje.id_tipo_apren = :tipoAprendizajeId")
    List<Actividad> findByTipoAprendizajeId(Integer tipoAprendizajeId);
}
