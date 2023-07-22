package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.JugadorServiceImpl;
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
	
	
	

	@PostMapping("/jugador/create")
	public ResponseEntity<Jugador> create(@RequestBody Jugador jugador) {
		try {
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
				//act.setUsuarios(actividadRb.getUsuarios());
				return new ResponseEntity<>(jugadorServ.save(actividadRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
