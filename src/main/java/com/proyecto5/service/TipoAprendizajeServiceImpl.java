package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.TipoAprendizaje;
import com.proyecto5.repository.TipoAprendizajeRepository;

@Service
public class TipoAprendizajeServiceImpl extends GenericServiceImpl<TipoAprendizaje, Integer> implements GenericService<TipoAprendizaje, Integer> {
	@Autowired
    private TipoAprendizajeRepository tipoAprendizajeRepository;

    @Override
    public CrudRepository<TipoAprendizaje, Integer> getDao() {
        return tipoAprendizajeRepository;
    }


}
