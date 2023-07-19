package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Jugador;
import com.proyecto5.repository.JugadorRepository;

@Service
public class JugadorServiceImpl extends GenericServiceImpl<Jugador, Integer> implements GenericService<Jugador, Integer> {
	@Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public CrudRepository<Jugador, Integer> getDao() {
        return jugadorRepository;
    }
}
