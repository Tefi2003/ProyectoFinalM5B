package com.proyecto5.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "progreso")
public class Progreso implements Serializable {
/*
    //RELACION CON pro apre
    @OneToMany(mappedBy = "progreso")
    private List<ProgresoAprendizaje> progresoAprendizaje;
/*
    //RELACION CON Jugador
    @OneToOne(mappedBy = "progreso")
    private Jugador jugador;
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progress")
    private Integer id_progress;

    @Column(name = "prog_nivel")
    private Integer prog_nivel;

    @Column(name = "prog_puntaje_total")
    private Integer prog_puntaje_total;

    @Column(name = "prog_hora_promd")
    private Time prog_hora_promd;

    @Column(name = "prog_fecha_init")
    private String prog_fecha_init;

    @Column(name = "prog_fecha_act")
    private Timestamp prog_fecha_act;

}
