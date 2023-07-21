package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.UsuariosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto5.model.Usuarios;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private final UsuariosServiceImpl usuaServ;

    public UsuariosController(UsuariosServiceImpl usuaServ) {
        this.usuaServ = usuaServ;
    }


    @GetMapping("/usuarios/list")
    public ResponseEntity<List<Usuarios>> list() {
        try {
            return new ResponseEntity<>(usuaServ.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /*Sirve para listar solo los usuarios que sean administradores para la auntenticacion*/
    @GetMapping("/usuarios/list/admin")
    public ResponseEntity<List<Usuarios>> getUsuariosAdmin() {
        try {
            List<Usuarios> usuariosAdmin = usuaServ.findByRole("Administrador");
            return new ResponseEntity<>(usuariosAdmin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Lista solo los usuarios que son jugadores sirva para la inactividad de usuarios y para
    //aunteticacion
    @GetMapping("/usuarios/list/Jugador")
    public ResponseEntity<List<Usuarios>> getUsuariosJugador() {
        try {
            List<Usuarios> usuarioJugador = usuaServ
                    .findByRole("Jugador");
            return new ResponseEntity<>(usuarioJugador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/search/{id}")
    public ResponseEntity<Usuarios> search(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(usuaServ.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /////////////////////////////la ultima
    
    ////////////////////se obtiene el rol
    @PostMapping("/usuarios/create")
    public ResponseEntity<Usuarios> create(@RequestBody Usuarios usuario) {
        try {
            // Guarda el usuario en la base de datos
            Usuarios nuevoUsuario = usuaServ.save(usuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/usuarios/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            usuaServ.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/update/{id}")
    public ResponseEntity<Usuarios> update(@RequestBody Usuarios actividadRb, @PathVariable("id") Integer id) {
        Usuarios act = usuaServ.findById(id);

        if (act == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                act.setUsu_nombre(actividadRb.getUsu_nombre());
                act.setUsu_contra(actividadRb.getUsu_contra());
                act.setUsu_correo(actividadRb.getUsu_correo());
                act.setUsu_nivelacademico(actividadRb.getUsu_nivelacademico());
                //act.setUsu_fecha_inic(actividadRb.getUsu_fecha_inic());
                act.setUsu_fecha_nacimiento(actividadRb.getUsu_fecha_nacimiento());
                act.setRoles(actividadRb.getRoles());

                act.setJugador(actividadRb.getJugador());
                return new ResponseEntity<>(usuaServ.save(actividadRb), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/usuarios/count/admin")
    public ResponseEntity<Long> countUsuariosAdmin() {
        try {
            Long count = usuaServ.countUsuariosByRole(1);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/count/jugador")
    public ResponseEntity<Long> countUsuariosJugador() {
        try {
            Long count = usuaServ.countUsuariosByRole(2);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //INICIAR SESION
    //Iniciar Sesión
    @GetMapping("/usuarios/{user}/{pass}")
    public Usuarios show(@PathVariable String user,@PathVariable String pass) {
        return usuaServ.findByUserPass(user, pass);
    }
    //Autenticar Usuarios
    /*@PostMapping("/usuarios/authenticate")
    public ResponseEntity<Usuarios> authenticateUser(@RequestBody Usuarios usuario) {
        try {
            Usuarios authenticatedUser = usuaServ.authenticateUser(usuario.getUsu_correo(), usuario.getUsu_contra());

            if (authenticatedUser != null) {
                // Return the authenticated user object if authentication is successful.
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            } else {
                // Return a 401 Unauthorized status if authentication fails.
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    /*Metodo para obtener los usuarios jugadores*/
}
