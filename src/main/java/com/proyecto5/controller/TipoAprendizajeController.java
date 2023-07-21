package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.repository.TipoAprendizajeRepository;
import com.proyecto5.service.TipoAprendizajeServiceImpl;
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

import com.proyecto5.model.TipoAprendizaje;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class TipoAprendizajeController {
	
	@Autowired
	private TipoAprendizajeServiceImpl tipoServ;
	@Autowired
	private final TipoAprendizajeRepository tipoAprendizajeRepository;

	@Autowired
	public TipoAprendizajeController(TipoAprendizajeRepository tipoAprendizajeRepository) {
		this.tipoAprendizajeRepository = tipoAprendizajeRepository;
	}

	@GetMapping("/tipoapren/list")
	public ResponseEntity<List<TipoAprendizaje>> list() {
		try {
			return new ResponseEntity<>(tipoServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/tipoapren/search/{id}")
	public ResponseEntity<TipoAprendizaje> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(tipoServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/tipoapren/create")
	public ResponseEntity<TipoAprendizaje> create(@RequestBody TipoAprendizaje tipoapren) {
		try {
			return new ResponseEntity<>(tipoServ.save(tipoapren), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/tipoapren/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			tipoServ.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar el Registro");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tipoapren/update/{id}")
	public ResponseEntity<TipoAprendizaje> update(@RequestBody TipoAprendizaje actividadRb, @PathVariable("id") Integer id) {
		TipoAprendizaje act = tipoServ.findById(id);

		if (act == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				act.setTapr_nombre(actividadRb.getTapr_nombre());
				act.setTapr_progreso(actividadRb.getTapr_progreso());
				return new ResponseEntity<>(tipoServ.save(actividadRb), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}


	@GetMapping("/tipoapren/count")
	public Long contador(){
		return tipoAprendizajeRepository.count();
 	}
}
