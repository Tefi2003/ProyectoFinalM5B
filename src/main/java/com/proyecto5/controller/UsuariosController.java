package com.proyecto5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto5.model.*;
import com.proyecto5.service.EncryServiceImpl;
import com.proyecto5.service.JugadorServiceImpl;
import com.proyecto5.service.RolesServiceImpl;
import com.proyecto5.service.UsuariosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private JugadorServiceImpl jugadorService;

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

    @GetMapping("/usuarios/login/{correo}/{password}")
    public ResponseEntity<Map<String, String>> login(@PathVariable String correo, @PathVariable String password) {
        // Buscar el usuario en la base de datos por su nombre de usuario (o cualquier campo que sea único)
        Usuarios usuario = encryService.findUsuarioByCorrreo(correo);
        Jugador perfilUsuario = jugadorService.getJugadorByUsuario(usuario);

        if (usuario == null) {
            // Usuario no encontrado, devolver mensaje de error
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario no encontrado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // Obtener la contraseña encriptada almacenada en la base de datos para este usuario
        String storedPassword = usuario.getUsu_contra();
        String id =String.valueOf(usuario.getId_usuario());
        String nivelAcademico = usuario.getUsu_nivelacademico();
        String fechaNa = usuario.getUsu_fecha_nacimiento();
        String fechaIn = String.valueOf(usuario.getUsu_fecha_inic());
        String nombre = usuario.getUsu_nombre();

        // Verificar la contraseña ingresada con la contraseña almacenada en la base de datos
        if (encryService.verifyPassword(password, storedPassword)) {

            // Inicio de sesión exitoso, devolver mensaje de éxito
            Map<String, String> response = new HashMap<>();
            response.put("message", "Inicio de sesión exitoso");
            response.put("id_usuario", id);
            response.put("usu_nombre", nombre);
            response.put("correo", correo);
            response.put("usu_nivelacademico", nivelAcademico);
            response.put("usu_fecha_nacimiento", fechaNa);
            response.put("usu_fecha_inic", fechaIn);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Contraseña incorrecta, devolver mensaje de error
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contraseña incorrecta");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/usuarios/login2")
    public ResponseEntity<?> login2(@RequestParam String correo, @RequestParam String password) {
        // Buscar el usuario en la base de datos por su nombre de usuario (o cualquier campo que sea único)
        Usuarios usuario = encryService.findUsuarioByCorrreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Obtener la contraseña encriptada almacenada en la base de datos para este usuario
        String storedPassword = usuario.getUsu_contra();

        // Verificar la contraseña ingresada con la contraseña almacenada en la base de datos
        if (encryService.verifyPassword(password, storedPassword)) {
            // Si la autenticación es exitosa, devuelve un objeto JSON con la información del usuario y su rol
            Map<String, Object> response = new HashMap<>();
            response.put("correo", correo);
            response.put("rol", usuario.getRoles().getId_rol()); //Obtiene el rol para redireccionar la pagina en angular dependiendo
            //del rol

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
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
            Usuarios nuevoUsuario = usuaServ.saveCrypt(usuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
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
                act.setCorreo(actividadRb.getCorreo());
                act.setUsu_nivelacademico(actividadRb.getUsu_nivelacademico());
                act.setUsu_fecha_nacimiento(actividadRb.getUsu_fecha_nacimiento());
                Roles rolesAsocion = rolesService.findById(actividadRb.getRoles().getId_rol());
                act.setRoles(rolesAsocion);

                return new ResponseEntity<>(usuaServ.save(act), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/usuarios/{correo}/password")
    public ResponseEntity<String> updatePassword(@PathVariable String correo, @RequestBody String newPassword) {
        // Buscar el usuario en la base de datos por su ID
        Usuarios usuario = encryService.findUsuarioByCorrreo(correo);

        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        // Encriptar la nueva contraseña antes de almacenarla en la base de datos
        String hashedPassword = encryService.encryPassword(newPassword);

        // Actualizar la contraseña del usuario con la nueva contraseña encriptada
        usuario.setUsu_contra(hashedPassword);

        // Guardar el usuario actualizado en la base de datos
        usuaServ.save(usuario);

        return new ResponseEntity<>("Contraseña actualizada exitosamente", HttpStatus.OK);
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

    //Iniciar Sesión
    @GetMapping("/usuarios/{user}/{pass}")
    public Usuarios show(@PathVariable String user, @PathVariable String pass) {
        return usuaServ.findByUserPass(user, pass);
    }

    @GetMapping("/usuarios/login3")
    public ResponseEntity<?> loginPerfil(@RequestParam String correo, @RequestParam String password) {
        // Buscar el usuario en la base de datos por su correo
        Usuarios usuario = encryService.findUsuarioByCorrreo(correo);
        Jugador perfilUsuario = jugadorService.getJugadorByUsuario(usuario);


        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Obtener la contraseña encriptada almacenada en la base de datos para este usuario
        String storedPassword = usuario.getUsu_contra();

        // Verificar la contraseña ingresada con la contraseña almacenada en la base de datos
        if (encryService.verifyPassword(password, storedPassword)) {
            // Si la autenticación es exitosa, obtiene el rol del usuario
            int id = 0;
            Roles rolUsuario = usuario.getRoles();
            Usuarios juador = perfilUsuario.getUsuarios();

            Progreso progreso = perfilUsuario.getProgreso();
            ProgresoAprendizaje progresoA = progreso.getProgresoAprendizaje();

            Actividad actividad = perfilUsuario.getActividad();

            TipoAprendizaje tipoaprendizaje = actividad.getTipoAprendizaje();


            // Devuelve un objeto JSON con la información completa del jugador, su rol, y datos relacionados
            Map<String, Object> response = new HashMap<>();
            response.put("id_usuario", id);
            response.put("usu_correo", correo);
            response.put("usuarios", usuario);
            response.put("jugador", perfilUsuario);
            response.put("rol", rolUsuario);
            response.put("progreso", perfilUsuario);
            response.put("progresoA", progreso);
            response.put("tipoaprendizaje", tipoaprendizaje);
            response.put("actividad", perfilUsuario);
            //response.put("resultados", perfilUsuario);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }
    }
}
