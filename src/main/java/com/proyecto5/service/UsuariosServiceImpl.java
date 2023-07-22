package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.UsuariosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuarioService{
	@Autowired
    private UsuariosRepository usuariosRepository;

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
    public Usuarios findById(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        usuariosRepository.deleteById(id);
    }

    //Encuentra el correo y clave del usuario para que se pueda logear
    public Usuarios findByUserPass(String user, String pass) {
        ArrayList<Usuarios> allUsers = new ArrayList();
        allUsers=(ArrayList<Usuarios>) usuariosRepository.findAll();
        Optional<Usuarios> result = allUsers.stream()
                .filter(u -> u.getCorreo().equals(user) && u.getUsu_contra().equals(pass))
                .findFirst();
        if(result.isPresent()) {
            return result.get();
        } else {
            System.out.println("Inexistente");
            return null;
        }
    }
}
