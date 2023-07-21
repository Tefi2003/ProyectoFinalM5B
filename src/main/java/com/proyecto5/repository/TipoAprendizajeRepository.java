package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.TipoAprendizaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoAprendizajeRepository extends JpaRepository<TipoAprendizaje, Integer>{

}
