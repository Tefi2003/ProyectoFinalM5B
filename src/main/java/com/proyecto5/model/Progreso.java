package com.proyecto5.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "progreso")
public class Progreso implements Serializable {

    //FK
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Jugador jugador;


    //RELACION CON pro apre
    @OneToMany(mappedBy = "progreso")
    private List<ProgresoAprendizaje> progresoAprendizaje;


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
    private Timestamp prog_fecha_init;

    @Column(name = "prog_fecha_act")
    private Timestamp prog_fecha_act;


    public Integer getId_progress() {
        return id_progress;
    }

    public void setId_progress(Integer id_progress) {
        this.id_progress = id_progress;
    }

    public Integer getProg_nivel() {
        return prog_nivel;
    }

    public void setProg_nivel(Integer prog_nivel) {
        this.prog_nivel = prog_nivel;
    }

    public Integer getProg_puntaje_total() {
        return prog_puntaje_total;
    }

    public void setProg_puntaje_total(Integer prog_puntaje_total) {
        this.prog_puntaje_total = prog_puntaje_total;
    }

    public Time getProg_hora_promd() {
        return prog_hora_promd;
    }

    public void setProg_hora_promd(Time prog_hora_promd) {
        this.prog_hora_promd = prog_hora_promd;
    }

    public Timestamp getProg_fecha_init() {
        return prog_fecha_init;
    }

    public void setProg_fecha_init(Timestamp prog_fecha_init) {
        this.prog_fecha_init = prog_fecha_init;
    }

    public Timestamp getProg_fecha_act() {
        return prog_fecha_act;
    }

    public void setProg_fecha_act(Timestamp prog_fecha_act) {
        this.prog_fecha_act = prog_fecha_act;
    }


}
