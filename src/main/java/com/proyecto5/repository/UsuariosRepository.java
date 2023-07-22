package com.proyecto5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto5.model.Usuarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
    @Query("SELECT COUNT(u) FROM Usuarios u WHERE u.roles.id_rol = :roleId")
    Long countByRoleId(@Param("roleId") Integer roleId);
    @Query("SELECT u FROM Usuarios u WHERE u.roles.rol_nombre = :rol")
    List<Usuarios> findByRoles_Role(@Param("rol") String rol);

    Usuarios findByCorreo(String correo);
}
