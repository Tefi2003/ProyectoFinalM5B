package com.proyecto5.repository;

import com.proyecto5.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
    Jugador findByUsuarios(Usuarios usuarios);
}
