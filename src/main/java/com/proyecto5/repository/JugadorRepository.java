package com.proyecto5.repository;

import com.proyecto5.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Jugador;
import org.springframework.data.jpa.repository.Query;

public interface JugadorRepository extends JpaRepository<Jugador, Integer>{
    Jugador findByUsuarios(Usuarios usuarios);

    @Query("SELECT MAX(a.player_id) FROM Jugador a")
    Integer findMaxId();
}
