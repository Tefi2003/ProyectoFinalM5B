package com.proyecto5.Data;

import com.proyecto5.model.Actividad;
import com.proyecto5.model.Niveles;
import com.proyecto5.model.Recursos;
import com.proyecto5.model.TipoAprendizaje;
import com.proyecto5.repository.ActividadRepository;
import com.proyecto5.repository.NivelesRepository;
import com.proyecto5.repository.RecursosRepository;
import com.proyecto5.repository.TipoAprendizajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActividadDataLoader implements CommandLineRunner {

    private final RecursosRepository recursosRepository;
    private final NivelesRepository nivelesRepository;
    private final TipoAprendizajeRepository tipoAprendizajeRepository;
    private final ActividadRepository actividadRepository;

    public ActividadDataLoader(RecursosRepository recursosRepository, NivelesRepository nivelesRepository, TipoAprendizajeRepository tipoAprendizajeRepository, ActividadRepository actividadRepository) {
        this.recursosRepository = recursosRepository;
        this.nivelesRepository = nivelesRepository;
        this.tipoAprendizajeRepository = tipoAprendizajeRepository;
        this.actividadRepository = actividadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        TipoAprendizaje tipoAprendizaje1 = new TipoAprendizaje();
        tipoAprendizaje1.setTapr_nombre("Aprendizaje con juegos");
        tipoAprendizaje1.setTapr_progreso(0);
        tipoAprendizajeRepository.save(tipoAprendizaje1);

        TipoAprendizaje tipoAprendizaje2 = new TipoAprendizaje();
        tipoAprendizaje2.setTapr_nombre("Aprendizaje con lógica");
        tipoAprendizaje2.setTapr_progreso(0);
        tipoAprendizajeRepository.save(tipoAprendizaje2);

        TipoAprendizaje tipoAprendizaje3 = new TipoAprendizaje();
        tipoAprendizaje3.setTapr_nombre("Aprendizaje comprensivo");
        tipoAprendizaje3.setTapr_progreso(0);
        tipoAprendizajeRepository.save(tipoAprendizaje3);

        Niveles nv1 = new Niveles();
        nv1.setNv_numero_nivel(1);
        nivelesRepository.save(nv1);

        Niveles nv2 = new Niveles();
        nv2.setNv_numero_nivel(2);
        nivelesRepository.save(nv2);

        Niveles nv3 = new Niveles();
        nv3.setNv_numero_nivel(3);
        nivelesRepository.save(nv3);

        Recursos recursos1 = new Recursos();
        recursos1.setRec_nombre("Recurso1");
        recursosRepository.save(recursos1);

        Actividad actividad1 = new Actividad();
        actividad1.setAct_nombre("Juego de memoria");
        actividad1.setAct_descripcion("Juego de cartas para fortalecer la memoria del aprendiz");
        actividad1.setAct_dificultad("FÁCIL");
        actividad1.setAct_puntaje_max(20);
        actividad1.setAct_puntaje_min(5);
        actividad1.setAct_puntaje_alcanzado(5);
        actividad1.setAct_aprendizaje("Aprendiendo mediante la visualización y memorización");
        actividad1.setAct_estado(true);
        actividad1.setAct_respuesta("Esperando respuesta");
        actividad1.setNiveles(nv1);
        actividad1.setRecursos(recursos1);
        actividad1.setTipoAprendizaje(tipoAprendizaje1);
        actividadRepository.save(actividad1);

        Actividad actividad2 = new Actividad();
        actividad2.setAct_nombre("Cuestionario y cuento");
        actividad2.setAct_descripcion("Cuestionario de preguntas sobre un texto determinado");
        actividad2.setAct_dificultad("MEDIO");
        actividad2.setAct_puntaje_max(25);
        actividad2.setAct_puntaje_min(5);
        actividad2.setAct_puntaje_alcanzado(5);
        actividad2.setAct_aprendizaje("Comprensión lectora y atención");
        actividad2.setAct_estado(true);
        actividad2.setAct_respuesta("Esperando respuesta");
        actividad2.setNiveles(nv2);
        actividad2.setRecursos(recursos1);
        actividad2.setTipoAprendizaje(tipoAprendizaje3);
        actividadRepository.save(actividad2);
    }
}
