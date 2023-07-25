package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
@Entity
@Table(name = "resultados")
public class Resultados implements Serializable {

    
    //FK ACTIVIDAD
    @ManyToOne
    @JoinColumn(name = "id_activ")
    private Actividad actividad;

    @JsonIgnore
    @OneToMany(mappedBy = "resultados")
    private List<ProgresoAprendizaje> progresoAprendizaje;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Integer id_resultado;

    @Column(name = "re_puntaje")
    private Integer re_puntaje;

    @Column(name = "re_fecha")
    private Timestamp re_fecha;

    @PrePersist
    public void prePersist() {
        re_fecha = new Timestamp(System.currentTimeMillis());
    }
}
