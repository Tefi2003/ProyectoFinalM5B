package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Actividad;
import com.proyecto5.repository.ActividadRepository;
import com.proyecto5.service.ActividadService;
import com.proyecto5.service.GenericServiceImpl;

@Service
public class ActividadServiceImpl extends GenericServiceImpl<Actividad, Integer> implements ActividadService {
	@Autowired
    private ActividadRepository actividadRepository;

    @Override
    public CrudRepository<Actividad, Integer> getDao() {
        return actividadRepository;
    }
}
