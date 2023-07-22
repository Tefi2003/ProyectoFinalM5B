package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.NivelesServiceImpl;
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

import com.proyecto5.model.Niveles;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class NivelesController {
	
	@Autowired
	private NivelesServiceImpl nivelesServ;

	@GetMapping("/niveles/list")
	public ResponseEntity<List<Niveles>> list() {
		try {
			return new ResponseEntity<>(nivelesServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/niveles/search/{id}")
	public ResponseEntity<Niveles> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(nivelesServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/niveles/create")
	public ResponseEntity<Niveles> create(@RequestBody Niveles nivel) {
		try {
			return new ResponseEntity<>(nivelesServ.save(nivel), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/niveles/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			nivelesServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/niveles/update/{id}")
	public ResponseEntity<Niveles> update(@RequestBody Niveles nivelRb, @PathVariable("id") Integer id) {
		Niveles niv = nivelesServ.findById(id);

		if (niv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				niv.setNv_numero_nivel(nivelRb.getNv_numero_nivel());
				return new ResponseEntity<>(nivelesServ.save(niv), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
