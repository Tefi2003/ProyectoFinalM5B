package com.proyecto5.service;

import com.proyecto5.model.Usuarios;

import java.util.List;

public interface UsuariosService {

    public Usuarios findByUserPass(String user, String pass);
    public List<Usuarios> findAll();
}
