package com.proyecto5.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "progresoAprendizaje")
public class ProgresoAprendizaje implements Serializable {
/*
    //FK PROGRESO
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_progress")
    private Progreso progreso;
*/
    //FK TIPO APRENDIZAJE
    @OneToOne
    @JoinColumn(name = "id_tipo_apren")
    private TipoAprendizaje tipoAprendizaje;

    @ManyToOne
    @JoinColumn(name = "id_resultado")
    private Resultados resultados;

    @JsonIgnore
    @OneToMany(mappedBy = "progresoAprendizaje")
    private List<Progreso> progreso;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progapre")
    private Integer id_progapre;

    @Column(name = "progapr_nombre")
    private String progapr_nombre;

    @Column(name = "progapr_punntaje_aprendizaje")
    private Integer progapr_punntaje_aprendizaje;
}