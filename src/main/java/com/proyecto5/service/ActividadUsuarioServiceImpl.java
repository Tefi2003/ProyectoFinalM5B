package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.ActividadUsuario;
import com.proyecto5.repository.ActividadUsuarioRepository;


@Service
public class ActividadUsuarioServiceImpl extends GenericServiceImpl<ActividadUsuario, Integer> implements ActividadUsuarioService {
	@Autowired
    private ActividadUsuarioRepository actividadUsuarioRepository;

    @Override
    public CrudRepository<ActividadUsuario, Integer> getDao() {
        return actividadUsuarioRepository;
    }

}
