package com.proyecto5.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proyecto5.model.Niveles;
import com.proyecto5.model.Recursos;
import com.proyecto5.model.TipoAprendizaje;
import com.proyecto5.repository.ActividadRepository;
import com.proyecto5.service.ActividadServiceImpl;
import com.proyecto5.service.NivelesServiceImpl;
import com.proyecto5.service.RecursosServiceImpl;
import com.proyecto5.service.TipoAprendizajeServiceImpl;
import com.sun.jdi.PrimitiveValue;
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


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ActividadController {

	@Autowired
	private ActividadServiceImpl actividadServ;

	@Autowired
	private RecursosServiceImpl recursosService;

	@Autowired
	private TipoAprendizajeServiceImpl tipoAprendizajeService;

	@Autowired
	private NivelesServiceImpl nivelesService;

	@Autowired
	private ActividadRepository actividadRepository;

	public ActividadController(ActividadServiceImpl actividadServ) {
		this.actividadServ = actividadServ;
	}

	@GetMapping("/actividad/list")
	public ResponseEntity<List<Actividad>> list() {
		try {
			return new ResponseEntity<>(actividadServ.findByAll(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/actividad/latest")
	public ResponseEntity<Map<String, Integer>> getLatestActividad() {
		try {
			Integer maxId = actividadServ.findMaxId();
			Actividad act = actividadServ.findById(maxId);

			if (maxId == null) {
				return ResponseEntity.noContent().build(); // 204 No Content
			} else {
				Map<String, Integer> response = new HashMap<>();
				response.put("id_activ", maxId);
				response.put("puntaje", act.getAct_puntaje_alcanzado());
				return ResponseEntity.ok(response); // 200 OK
			}
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
				Niveles nivelAsociado = nivelesService.findById(actividadRb.getNiveles().getId_nivel());
				act.setNiveles(nivelAsociado);

				Recursos recursosAsociados = recursosService.findById(actividadRb.getRecursos().getId_recurso());
				act.setRecursos(recursosAsociados);

				TipoAprendizaje tipoAprendizaje = tipoAprendizajeService.findById(actividadRb.getTipoAprendizaje().getId_tipo_apren());
				act.setTipoAprendizaje(tipoAprendizaje);
				return new ResponseEntity<>(actividadServ.save(act), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@GetMapping("/actividades/count")
	public Long contador(){
		return actividadRepository.count();
	}


	@GetMapping("/tipoapren/{id}")
	public ResponseEntity<Map<String, Object>> actividadesAsignadas(@PathVariable("id") Integer id) {
		try {
			List<Actividad> actividadesAsignadas = actividadRepository.findByTipoAprendizajeId(id);
			if (actividadesAsignadas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// Obtener el conteo de actividades asignadas
			int conteoActividadesAsignadas = actividadesAsignadas.size();

			// Crear un mapa para almacenar el resultado
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("conteoActividadesAsignadas", conteoActividadesAsignadas);

			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
