package com.proyecto5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.UsuariosRepository;

import java.util.List;

@Service
public class UsuariosServiceImpl extends GenericServiceImpl<Usuarios, Integer> implements GenericService<Usuarios, Integer> {
	@Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public CrudRepository<Usuarios, Integer> getDao() {
        return usuariosRepository;
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
}
