package com.proyecto5.controller;

import java.util.List;

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

import com.proyecto5.model.Actividad;
import com.proyecto5.service.ActividadService;



@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ActividadController {

	@Autowired
	private ActividadService actividadServ;

	@GetMapping("/actividad/list")
	public ResponseEntity<List<Actividad>> list() {
		try {
			return new ResponseEntity<>(actividadServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/actividad/search/{id}")
	public ResponseEntity<Actividad> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(actividadServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/actividad/create")
	public ResponseEntity<Actividad> create(@RequestBody Actividad actividad) {
		try {
			return new ResponseEntity<>(actividadServ.save(actividad), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/actividad/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			actividadServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/actividad/update/{id}")
	public ResponseEntity<Actividad> update(@RequestBody Actividad actividadRb, @PathVariable("id") Integer id) {
		Actividad act = actividadServ.findById(id);

		if (act == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				act.setAct_nombre(actividadRb.getAct_nombre());
				act.setAct_descripcion(actividadRb.getAct_descripcion());
				act.setAct_dificultad(actividadRb.getAct_dificultad());
				act.setAct_puntaje_max(actividadRb.getAct_puntaje_max());
				act.setAct_puntaje_min(actividadRb.getAct_puntaje_min());
				act.setAct_puntaje_alcanzado(actividadRb.getAct_puntaje_alcanzado());
				act.setAct_aprendizaje(actividadRb.getAct_aprendizaje());
				act.setAct_estado(actividadRb.isAct_estado());
				act.setAct_respuesta(actividadRb.getAct_respuesta());
				act.setNiveles(actividadRb.getNiveles());
				act.setRecursos(actividadRb.getRecursos());
				act.setTipoAprendizaje(actividadRb.getTipoAprendizaje());
                                act.setAct_respuesta(actividadRb.getAct_respuesta());
				return new ResponseEntity<>(actividadServ.save(actividadRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
