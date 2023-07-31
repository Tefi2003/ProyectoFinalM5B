package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Recursos;
import com.proyecto5.repository.RecursosRepository;

@Service
public class RecursosServiceImpl extends GenericServiceImpl<Recursos, Integer> implements GenericService<Recursos, Integer> {
	@Autowired
    private RecursosRepository progresoRepository;

    @Override
    public CrudRepository<Recursos, Integer> getDao() {
        return progresoRepository;
    }

    @Override
    public Integer findMaxId() {
        return null;
    }
}
