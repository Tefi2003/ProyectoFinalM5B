package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.UsuariosRepository;

@Service
public class UsuariosServiceImpl extends GenericServiceImpl<Usuarios, Integer> implements UsuariosService {
	@Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public CrudRepository<Usuarios, Integer> getDao() {
        return usuariosRepository;
    }

}
