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

    @GetMapping("/usuarios/loginR")
    public ResponseEntity<?> loginRelacion(@RequestParam String correo, @RequestParam String password) {
        // Buscar el usuario en la base de datos por su correo
        Usuarios usuario = encryService.findUsuarioByCorrreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Obtener la contraseña encriptada almacenada en la base de datos para este usuario
        String storedPassword = usuario.getUsu_contra();

        // Verificar la contraseña ingresada con la contraseña almacenada en la base de datos
        if (encryService.verifyPassword(password, storedPassword)) {
            // Si la autenticación es exitosa, obtiene el rol del usuario
            Roles rolUsuario = usuario.getRoles();
            String nombre= usuario.getUsu_nombre();
            // Devuelve un objeto JSON con la información básica del usuario y su rol
            Map<String, Object> response = new HashMap<>();
            response.put("id_usuario", usuario.getId_usuario());
            response.put("usu_correo", correo);
            response.put("rol", rolUsuario);

            // Cargar relaciones específicas según el tipo de usuario (administrador o jugador)
            if (rolUsuario.getId_rol() == 1) {
                // Si es administrador, carga las relaciones específicas para el administrador
                // Por ejemplo, si el administrador tiene roles adicionales:
                // response.put("rolesAdicionales", obtenerRolesAdicionales(usuario));
                // ...
                response.put("rol", rolUsuario);
                response.put("usu_nombre", nombre);
                // Puedes agregar aquí otras relaciones específicas para el administrador
            } else if (rolUsuario.getId_rol() == 2) {
                // Si es jugador, carga las relaciones específicas para el jugador
                Jugador perfilUsuario = jugadorService.getJugadorByUsuario(usuario);
                Progreso progreso = perfilUsuario.getProgreso();
                //ProgresoAprendizaje progresoA = progreso.getProgresoAprendizaje();
                Actividad actividad = perfilUsuario.getActividad();
                TipoAprendizaje tipoAprendizaje = actividad.getTipoAprendizaje();
                response.put("rol", rolUsuario);
                response.put("jugador", perfilUsuario);
                response.put("progreso", progreso);
                //response.put("progresoA", progresoA);
                response.put("tipoaprendizaje", tipoAprendizaje);
                response.put("actividad", actividad);

                // Puedes agregar aquí otras relaciones específicas para el jugador
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }
    }
}
