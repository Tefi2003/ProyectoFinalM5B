package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.ProgresoAprendizaje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgresoAprendizajeRepository extends JpaRepository<ProgresoAprendizaje, Integer>{
    /*List<ProgresoAprendizaje> contar(Integer jugadorId);*/
    /*@Query("SELECT u FROM  ProgresoAprendizaje u JOIN Jugador j ON u.id_progapre=: j.")
    Long Encontrarusers(@Param("roleId") Integer roleId);*/

}
