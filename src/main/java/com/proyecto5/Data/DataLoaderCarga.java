package com.proyecto5.Data;

import com.proyecto5.model.*;
import com.proyecto5.repository.*;
import com.proyecto5.service.UsuariosServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderCarga implements CommandLineRunner {

    private final ResultadosRepository resultadosRepository;
    private final ActividadRepository actividadRepository;
    private final ProgresoAprendizajeRepository progresoAprendizajeRepository;
    private final TipoAprendizajeRepository tipoAprendizajeRepository;
    private final UsuariosRepository usuariosRepository;
    private final JugadorRepository jugadorRepository;
    private final ProgresoRepository progresoRepository;
    private final RecursosRepository recursosRepository;
    private final NivelesRepository nivelesRepository;
    private final RolesRepository rolesRepository;
    private final UsuariosServiceImpl usuariosService;

    public DataLoaderCarga(ResultadosRepository resultadosRepository, ActividadRepository actividadRepository, ProgresoAprendizajeRepository progresoAprendizajeRepository, TipoAprendizajeRepository tipoAprendizajeRepository, UsuariosRepository usuariosRepository, JugadorRepository jugadorRepository, ProgresoRepository progresoRepository, RecursosRepository recursosRepository, NivelesRepository nivelesRepository, RolesRepository rolesRepository, UsuariosServiceImpl usuariosService) {
        this.resultadosRepository = resultadosRepository;
        this.actividadRepository = actividadRepository;
        this.progresoAprendizajeRepository = progresoAprendizajeRepository;
        this.tipoAprendizajeRepository = tipoAprendizajeRepository;
        this.usuariosRepository = usuariosRepository;
        this.jugadorRepository = jugadorRepository;
        this.progresoRepository = progresoRepository;
        this.recursosRepository = recursosRepository;
        this.nivelesRepository = nivelesRepository;
        this.rolesRepository = rolesRepository;
        this.usuariosService = usuariosService;
    }

    @Override
    public void run(String... args) throws Exception {
        Roles rol1 = new Roles();
        rol1.setRol_nombre("Administrador");
        rolesRepository.save(rol1);

        Roles rol2 = new Roles();
        rol2.setRol_nombre("Jugador");
        rolesRepository.save(rol2);

        Usuarios usuario1 = new Usuarios();
        usuario1.setUsu_nombre("Eddy Belduma");
        usuario1.setUsu_contra("Edy12345");
        usuario1.setCorreo("elvissb6@gmail.com");
        usuario1.setUsu_nivelacademico("1° - 3° año");
        usuario1.setUsu_fecha_nacimiento("2003-11-03");
        usuario1.setRoles(rol1);
        usuariosService.saveCrypt(usuario1);

        Usuarios usuario2 = new Usuarios();
        usuario2.setUsu_nombre("Jugador defecto");
        usuario2.setUsu_contra("Player12345");
        usuario2.setCorreo("player@gmail.com");
        usuario2.setUsu_nivelacademico("4° - 6° año");
        usuario2.setUsu_fecha_nacimiento("2008-12-23");
        usuario2.setRoles(rol2);
        usuariosService.saveCrypt(usuario2);

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

        Resultados resultados = new Resultados();
        resultados.setRe_puntaje(5);
        resultados.setActividad(actividad1);
        resultadosRepository.save(resultados);

        ProgresoAprendizaje progresoAprendizaje = new ProgresoAprendizaje();
        progresoAprendizaje.setTipoAprendizaje(tipoAprendizaje1);
        progresoAprendizaje.setResultados(resultados);
        progresoAprendizaje.setProgapr_nombre("Aprendizaje de juegos");
        progresoAprendizaje.setProgapr_punntaje_aprendizaje(0);
        progresoAprendizajeRepository.save(progresoAprendizaje);

        Progreso progreso = new Progreso();
        progreso.setProgresoAprendizaje(progresoAprendizaje);
        progreso.setProg_fecha_init("2023-05-14");
        progreso.setProg_puntaje_total(20);
        progreso.setProg_nivel(1);
        progresoRepository.save(progreso);

        Jugador jugador = new Jugador();
        jugador.setActividad(actividad1);
        jugador.setProgreso(progreso);
        jugador.setUsuarios(usuario2);
        jugador.setPlayer_nombre("PlayerStart");
        jugadorRepository.save(jugador);
    }
}
