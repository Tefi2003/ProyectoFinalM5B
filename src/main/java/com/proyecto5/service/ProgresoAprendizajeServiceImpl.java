package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.ProgresoAprendizaje;
import com.proyecto5.repository.ProgresoAprendizajeRepository;

import java.util.List;

@Service
public class ProgresoAprendizajeServiceImpl extends GenericServiceImpl<ProgresoAprendizaje, Integer> implements GenericService<ProgresoAprendizaje, Integer> {
	@Autowired
    private ProgresoAprendizajeRepository progresoAprendizajeRepository;

    @Override
    public CrudRepository<ProgresoAprendizaje, Integer> getDao() {
        return progresoAprendizajeRepository;
    }

}
