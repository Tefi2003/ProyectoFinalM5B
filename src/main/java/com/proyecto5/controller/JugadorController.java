package com.proyecto5.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto5.model.Usuarios;
import com.proyecto5.service.JugadorServiceImpl;
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

import com.proyecto5.model.Jugador;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class JugadorController {

	@Autowired
	private JugadorServiceImpl jugadorServ;

	@Autowired
	private UsuariosServiceImpl usuariosService;

	@GetMapping("/jugador/list")
	public ResponseEntity<List<Jugador>> list() {
		try {
			return new ResponseEntity<>(jugadorServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/jugador/search/{id}")
	public ResponseEntity<Jugador> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(jugadorServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/jugador/usuario/{id}")
	public ResponseEntity<Map<String, Integer>> getJugadorUsuario(@PathVariable("id") Integer id) {
		try {
			Usuarios usuarios = usuariosService.findById(id);
			Jugador ju = jugadorServ.getJugadorByUsuario(usuarios);

			if (ju == null) {
				Map<String, Integer> response = new HashMap<>();
				response.put("jugador", 0);
				return ResponseEntity.ok(response); // 204 No Content
			} else {
				Map<String, Integer> response = new HashMap<>();
				response.put("jugador", ju.getPlayer_id());
				response.put("progreso", ju.getProgreso().getId_progress());
				return ResponseEntity.ok(response); // 200 OK
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/jugador/create")
	public ResponseEntity<Jugador> create(@RequestBody Jugador jugador) {
		try {
			System.out.println(jugador.toString());
			return new ResponseEntity<>(jugadorServ.save(jugador), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/jugador/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			jugadorServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/jugador/update/{id}")
	public ResponseEntity<Jugador> update(@RequestBody Jugador actividadRb, @PathVariable("id") Integer id) {
		Jugador act = jugadorServ.findById(id);

		if (act == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				act.setPlayer_nombre(actividadRb.getPlayer_nombre());
				act.setUsuarios(actividadRb.getUsuarios());
				return new ResponseEntity<>(jugadorServ.save(act), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}


	@GetMapping("/jugadores/registrados-por-dia")
	public ResponseEntity<Map<String, Integer>> getJugadoresRegistradosPorDia() {
		try {
			// Obtener la lista de usuarios que tienen el rol de "jugador"
			List<Usuarios> usuarioJugador = usuariosService.findByRole("Jugador");
			// Crear un mapa para almacenar la cantidad de jugadores registrados por día
			Map<String, Integer> jugadoresPorDia = new HashMap<>();

			// Recorrer los usuarios y contar la cantidad de jugadores por día
			for (Usuarios usuario : usuarioJugador) {
				// Verificar si el usuario tiene una entidad "Jugador" relacionada
				Jugador jugador = jugadorServ.getJugadorByUsuario(usuario);
				if (jugador != null) {
					LocalDate fechaInicio = usuario.getUsu_fecha_inic().toLocalDateTime().toLocalDate();
					String fechaStr = fechaInicio.toString();

					// Aumentar el contador correspondiente para esta fecha
					jugadoresPorDia.put(fechaStr, jugadoresPorDia.getOrDefault(fechaStr, 0) + 1);
				}
			}

			return new ResponseEntity<>(jugadoresPorDia, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
