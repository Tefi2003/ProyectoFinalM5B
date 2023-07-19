package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.ProgresoAprendizajeServiceImpl;
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

import com.proyecto5.model.ProgresoAprendizaje;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProgresoAprendizajeController {

	@Autowired
	private ProgresoAprendizajeServiceImpl proaprServ;

	@GetMapping("/progresoAprendizaje/list")
	public ResponseEntity<List<ProgresoAprendizaje>> list() {
		try {
			return new ResponseEntity<>(proaprServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/progresoAprendizaje/search/{id}")
	public ResponseEntity<ProgresoAprendizaje> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(proaprServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/progresoAprendizaje/create")
	public ResponseEntity<ProgresoAprendizaje> create(@RequestBody ProgresoAprendizaje proapre) {
		try {
			return new ResponseEntity<>(proaprServ.save(proapre), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/progresoAprendizaje/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			proaprServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@PutMapping("/progresoAprendizaje/update/{id}")
	public ResponseEntity<ProgresoAprendizaje> update(@RequestBody ProgresoAprendizaje proapreRb, @PathVariable("id") Integer id) {
		ProgresoAprendizaje proapr = proaprServ.findById(id);

		if (proapr == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				proapr.setProgapr_nombre(proapreRb.getProgapr_nombre());
				proapr.setProgapr_punntaje_aprendizaje(proapreRb.getProgapr_punntaje_aprendizaje());
				return new ResponseEntity<>(proaprServ.save(proapreRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
