package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.model.Roles;
import com.proyecto5.service.EncryServiceImpl;
import com.proyecto5.service.RolesServiceImpl;
import com.proyecto5.service.UsuariosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto5.model.Usuarios;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private final UsuariosServiceImpl usuaServ;

    @Autowired
    private RolesServiceImpl rolesService;

    @Autowired
    private EncryServiceImpl encryService;

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
/*
    @GetMapping("/usuarios/login")
    public ResponseEntity<Usuarios> login(@RequestParam String correo) {
        try {
            Usuarios usuario = usuaServ.findByCorreo(correo);
            if (usuario != null) {
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                // Usuario no encontrado
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/usuarios/login")
    public ResponseEntity<String> login(@RequestParam String correo, @RequestParam String password) {
        // Buscar el usuario en la base de datos por su nombre de usuario (o cualquier campo que sea único)
        Usuarios usuario = encryService.findUsuarioByUsername(correo);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Obtener la contraseña encriptada almacenada en la base de datos para este usuario
        String storedPassword = usuario.getUsu_contra();

        // Verificar la contraseña ingresada con la contraseña almacenada en la base de datos
        if (encryService.verifyPassword(password, storedPassword)) {
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
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

    @PostMapping("/usuario/crearcrypto")
    public ResponseEntity<Usuarios> crearUsuario(@RequestBody Usuarios usuario) {
        // Obtener la contraseña sin encriptar desde el objeto usuario
        String password = usuario.getUsu_contra();

        // Encriptar la contraseña antes de guardarla en la base de datos
        String passwordEncriptada = encryService.encryPassword(password);

        // Asignar la contraseña encriptada al objeto usuario antes de guardarlo
        usuario.setUsu_contra(passwordEncriptada);

        // Guardar el usuario en la base de datos
        Usuarios nuevoUsuario = encryService.saveUser(usuario);

        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
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
                act.setCorreo(actividadRb.getCorreo());
                act.setUsu_nivelacademico(actividadRb.getUsu_nivelacademico());
                act.setUsu_fecha_nacimiento(actividadRb.getUsu_fecha_nacimiento());

                Roles rolesAsocion = rolesService.findById(actividadRb.getRoles().getId_rol());
                act.setRoles(rolesAsocion);

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
}
