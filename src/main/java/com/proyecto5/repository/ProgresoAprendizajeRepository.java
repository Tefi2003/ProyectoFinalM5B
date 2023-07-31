package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.ProgresoAprendizaje;
import org.springframework.data.jpa.repository.Query;

public interface ProgresoAprendizajeRepository extends JpaRepository<ProgresoAprendizaje, Integer>{
    @Query("SELECT MAX(a.id_progapre) FROM ProgresoAprendizaje a")
    Integer findMaxId();
    /*List<ProgresoAprendizaje> contar(Integer jugadorId);*/
    /*@Query("SELECT u FROM  ProgresoAprendizaje u JOIN Jugador j ON u.id_progapre=: j.")
    Long Encontrarusers(@Param("roleId") Integer roleId);*/

}
