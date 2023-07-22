package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.model.Actividad;
import com.proyecto5.service.ActividadServiceImpl;
import com.proyecto5.service.ResultadosServiceImpl;
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

import com.proyecto5.model.Resultados;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ResultadosController {

	@Autowired
	private ResultadosServiceImpl usuaServ;

	@Autowired
	private ActividadServiceImpl actividadService;

	@GetMapping("/resultados/list")
	public ResponseEntity<List<Resultados>> list() {
		try {
			return new ResponseEntity<>(usuaServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/resultados/search/{id}")
	public ResponseEntity<Resultados> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(usuaServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/resultados/create")
	public ResponseEntity<Resultados> create(@RequestBody Resultados actividad) {
		try {
			return new ResponseEntity<>(usuaServ.save(actividad), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/resultados/delete/{id}")
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

	@PutMapping("/resultados/update/{id}")
	public ResponseEntity<Resultados> update(@RequestBody Resultados actividadRb, @PathVariable("id") Integer id) {
		Resultados act = usuaServ.findById(id);

		if (act == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				act.setRe_fecha(actividadRb.getRe_fecha());
				act.setRe_hora(actividadRb.getRe_hora());
				act.setRe_puntaje(actividadRb.getRe_puntaje());

				Actividad actividad = actividadService.findById(actividadRb.getActividad().getId_activ());
				act.setActividad(actividad);

				return new ResponseEntity<>(usuaServ.save(act), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
}
