package com.proyecto5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto5.service.ProgresoServiceImpl;
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

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ProgresoController {
	
	@Autowired
	private ProgresoServiceImpl progresoServ;

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

	@GetMapping("/progreso/latest")
	public ResponseEntity<Map<String, Integer>> getLatestActividad() {
		try {
			Integer maxId = progresoServ.findMaxId();
			Progreso prog = progresoServ.findById(maxId);

			if (maxId == null) {
				return ResponseEntity.noContent().build(); // 204 No Content
			} else {
				Map<String, Integer> response = new HashMap<>();
				response.put("id_prog", maxId);
				response.put("puntaje", prog.getProg_puntaje_total());
				return ResponseEntity.ok(response); // 200 OK
			}
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
				pro.setProg_fecha_init(progreosRb.getProg_fecha_init());
				return new ResponseEntity<>(progresoServ.save(pro), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
