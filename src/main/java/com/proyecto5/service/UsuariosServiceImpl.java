package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.UsuariosRepository;

import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuarioService{
	@Autowired
    private UsuariosRepository usuariosRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Long contarUsuariosPorRol(Integer roleId) {
        return usuariosRepository.countByRoleId(roleId);
    }
    public Long countUsuariosByRole(Integer roleName) {
        return usuariosRepository.countByRoleId(roleName);
    }
    public List<Usuarios> findByRole(String role) {
        return usuariosRepository.findByRoles_Role(role);
    }

    @Override
    public List<Usuarios> findByAll() {
        return (List<Usuarios>) usuariosRepository.findAll();
    }

    @Override
    public Usuarios save(Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios saveCrypt(Usuarios usuarios) {
        String passwordCypt = this.passwordEncoder.encode(usuarios.getUsu_contra());
        usuarios.setUsu_contra(passwordCypt);
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios findById(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        usuariosRepository.deleteById(id);
    }
/*
    @Override
    public Usuarios findByCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }

 */
}
