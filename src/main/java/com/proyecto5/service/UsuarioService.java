package com.proyecto5.service;

import com.proyecto5.model.Usuarios;

import java.util.List;

public interface UsuarioService {
    public List<Usuarios> findByAll();

    public Usuarios save(Usuarios usuarios);

    public Usuarios saveCrypt(Usuarios usuarios);

    public Usuarios findById(Integer id);

    public void delete(Integer id);
}
