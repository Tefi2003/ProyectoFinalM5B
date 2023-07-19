package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.RecursosServiceImpl;
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

import com.proyecto5.model.Recursos;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class RecursosController {

	@Autowired
	private RecursosServiceImpl recursosServ;

	@GetMapping("/recursos/list")
	public ResponseEntity<List<Recursos>> list() {
		try {
			return new ResponseEntity<>(recursosServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/recursos/search/{id}")
	public ResponseEntity<Recursos> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(recursosServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/recursos/create")
	public ResponseEntity<Recursos> create(@RequestBody Recursos nivel) {
		try {
			return new ResponseEntity<>(recursosServ.save(nivel), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/recursos/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			recursosServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/recursos/update/{id}")
	public ResponseEntity<Recursos> update(@RequestBody Recursos nivelRb, @PathVariable("id") Integer id) {
		Recursos niv = recursosServ.findById(id);

		if (niv == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				niv.setRec_pdfs(nivelRb.getRec_pdfs());
				niv.setRec_enlaces(nivelRb.getRec_enlaces());
				return new ResponseEntity<>(recursosServ.save(nivelRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
