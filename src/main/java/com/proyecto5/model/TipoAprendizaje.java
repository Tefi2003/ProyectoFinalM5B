package com.proyecto5.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tipoAprendizaje")
public class TipoAprendizaje implements Serializable {
/*
    //RELACION CON ACTIVIIDAD
    @JsonIgnore
    @OneToMany(mappedBy = "tipoAprendizaje")
    private List<Actividad> actividad;
*/
/*
    //RELACION CON PROGRESO_APRENDIZAJE
    @JsonIgnore
    @OneToOne(mappedBy = "tipoAprendizaje")
    private ProgresoAprendizaje progresoAprendizaje;
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_apren")
    private Integer id_tipo_apren;

    @Column(name = "tapr_nombre")
    private String tapr_nombre;

    @Column(name = "tapr_progreso")
    private int tapr_progreso;


}
