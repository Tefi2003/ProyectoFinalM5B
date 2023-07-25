package com.proyecto5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "progreso")
public class Progreso implements Serializable {
    /*
        //RELACION CON pro apre
        @JsonIgnore
        @OneToMany(mappedBy = "progreso")
        private List<ProgresoAprendizaje> progresoAprendizaje;
    */
    //RELACION CON Jugador
    @JsonIgnore
    @OneToOne(mappedBy = "progreso")
    private Jugador jugador;

    //FK PROGRESO APRENDIZAJE
    @ManyToOne
    @JoinColumn(name = "id_progapre")
    private ProgresoAprendizaje progresoAprendizaje;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progress")
    private Integer id_progress;

    @Column(name = "prog_nivel")
    private Integer prog_nivel;

    @Column(name = "prog_puntaje_total")
    private Integer prog_puntaje_total;

    @Column(name = "prog_fecha_init")
    private String prog_fecha_init;

    @Column(name = "prog_fecha_act")
    private Timestamp prog_fecha_act;

    @PrePersist
    public void prePersist() {
        prog_fecha_act = new Timestamp(System.currentTimeMillis());
    }


}
