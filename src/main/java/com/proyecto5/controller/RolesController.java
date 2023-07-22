 package com.proyecto5.controller;

import java.util.List;

import com.proyecto5.service.RolesServiceImpl;
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

import com.proyecto5.model.Roles;
import com.proyecto5.repository.RolesRepository;

import java.util.Optional;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class RolesController {

	@Autowired
	private RolesServiceImpl actividadServ;
          @Autowired
private RolesRepository rolesRepository;

	@GetMapping("/roles/list")
	public ResponseEntity<List<Roles>> list() {
		try {
			return new ResponseEntity<>(actividadServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	@GetMapping("/roles/search/{id}")
	public ResponseEntity<Roles> search(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(actividadServ.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
        
        @GetMapping("/roles/{id}")
public ResponseEntity<Roles> obtenerRolPorId(@PathVariable("id") Integer id) {
    Optional<Roles> rol = rolesRepository.findById(id);
    
    if (rol.isPresent()) {
        return ResponseEntity.ok(rol.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

//////////////////////obtener rolees
  @GetMapping("/roles")
    public List<Roles> obtenerRoles() {
        return rolesRepository.findAll();
    }

                                                                
	
	

	@PostMapping("/roles/create")
	public ResponseEntity<Roles> create(@RequestBody Roles roles) {
		try {
			return new ResponseEntity<>(actividadServ.save(roles), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	

	@DeleteMapping("/roles/delete/{id}")
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

	@PutMapping("/roles/update/{id}")
	public ResponseEntity<Roles> update(@RequestBody Roles actividadRb, @PathVariable("id") Integer id) {
		Roles act = actividadServ.findById(id);

		if (act == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				act.setRol_nombre(actividadRb.getRol_nombre());
				return new ResponseEntity<>(actividadServ.save(act), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
