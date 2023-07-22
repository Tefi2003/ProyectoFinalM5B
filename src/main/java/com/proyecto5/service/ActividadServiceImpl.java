package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Actividad;
import com.proyecto5.repository.ActividadRepository;

@Service
public class ActividadServiceImpl extends GenericServiceImpl<Actividad, Integer> implements GenericService<Actividad, Integer> {
	@Autowired
    private ActividadRepository actividadRepository;

    public ActividadServiceImpl(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    @Override
    public CrudRepository<Actividad, Integer> getDao() {
        return actividadRepository;
    }

}
