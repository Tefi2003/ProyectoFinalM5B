package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Progreso;
import com.proyecto5.repository.ProgresoRepository;

@Service
public class ProgresoServiceImpl extends GenericServiceImpl<Progreso, Integer> implements GenericService<Progreso, Integer> {
	@Autowired
    private ProgresoRepository progresoRepository;

    @Override
    public CrudRepository<Progreso, Integer> getDao() {
        return progresoRepository;
    }

    @Override
    public Integer findMaxId() {
        return progresoRepository.findMaxId();
    }
}
