package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model. Resultados;
import com.proyecto5.repository.ResultadosRepository;

@Service
public class ResultadosServiceImpl extends GenericServiceImpl<Resultados, Integer> implements ResultadosService {
	@Autowired
    private ResultadosRepository resultadosRepository;

    @Override
    public CrudRepository<Resultados, Integer> getDao() {
        return resultadosRepository;
    }

}
