package com.proyecto5.Data;

import com.proyecto5.model.Roles;
import com.proyecto5.model.Usuarios;
import com.proyecto5.repository.RolesRepository;
import com.proyecto5.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDataLoader implements CommandLineRunner {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;

    public UsuarioDataLoader(UsuariosRepository usuariosRepository, RolesRepository rolesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Roles rol1 = new Roles();
        rol1.setRol_nombre("Administrador");
        rolesRepository.save(rol1);

        Roles rol2 = new Roles();
        rol2.setRol_nombre("Jugador");
        rolesRepository.save(rol2);


        Usuarios usuario1 = new Usuarios();
        usuario1.setUsu_nombre("Eddy Belduma");
        usuario1.setUsu_contra("Edy12345");
        usuario1.setCorreo("elvissb6@gmail.com");
        usuario1.setUsu_nivelacademico("1° - 3° año");
        usuario1.setUsu_fecha_nacimiento("2003-11-03");
        usuario1.setRoles(rol1);
        usuariosRepository.save(usuario1);

        Usuarios usuario2 = new Usuarios();
        usuario2.setUsu_nombre("Jugador defecto");
        usuario2.setUsu_contra("player12345");
        usuario2.setCorreo("player@gmail.com");
        usuario2.setUsu_nivelacademico("4° - 6° año");
        usuario2.setUsu_fecha_nacimiento("2008-12-23");
        usuario2.setRoles(rol2);
        usuariosRepository.save(usuario2);
    }
}
