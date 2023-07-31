package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Niveles;
import com.proyecto5.repository.NivelesRepository;

@Service
public class NivelesServiceImpl extends GenericServiceImpl<Niveles, Integer> implements GenericService<Niveles, Integer> {
	@Autowired
    private NivelesRepository nivelesRepository;

    @Override
    public CrudRepository<Niveles, Integer> getDao() {
        return nivelesRepository;
    }

    @Override
    public Integer findMaxId() {
        return null;
    }
}
