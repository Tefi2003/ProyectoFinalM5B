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

import com.proyecto5.model.Progreso;
import com.proyecto5.service.ProgresoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProgresoController {
	
	@Autowired
	private ProgresoService progresoServ;

	@GetMapping("/progreso/list")
	public ResponseEntity<List<Progreso>> list() {
		try {
			return new ResponseEntity<>(progresoServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/progreso/search/{id}")
	public ResponseEntity<Progreso> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(progresoServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/progreso/create")
	public ResponseEntity<Progreso> create(@RequestBody Progreso progreso) {
		try {
			return new ResponseEntity<>(progresoServ.save(progreso), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/progreso/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			progresoServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/progreso/update/{id}")
	public ResponseEntity<Progreso> update(@RequestBody Progreso progreosRb, @PathVariable("id") Integer id) {
		Progreso pro = progresoServ.findById(id);

		if (pro == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				pro.setProg_nivel(progreosRb.getProg_nivel());
				pro.setProg_puntaje_total(progreosRb.getProg_puntaje_total());
				pro.setProg_hora_promd(progreosRb.getProg_hora_promd());
				pro.setProg_fecha_init(progreosRb.getProg_fecha_init());
				pro.setProg_fecha_act(progreosRb.getProg_fecha_act());
				return new ResponseEntity<>(progresoServ.save(progreosRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
