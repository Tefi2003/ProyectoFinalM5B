package com.proyecto5.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {

    //fk: RELACION CON RECURSOS
    @ManyToOne
    @JoinColumn(name = "id_recurso")
    private Recursos recursos;

    //fk: RELACION CON TIPO APRENDIZAJE
    @ManyToOne
    @JoinColumn(name = "id_tipo_apren")
    private TipoAprendizaje tipoAprendizaje;

    //fk: RELACION CON NIVELES
    @ManyToOne
    @JoinColumn(name = "id_nivel")
    private Niveles niveles;

    //RELACION CON JUGADOR    
    @JsonIgnore
    @OneToMany(mappedBy = "actividad")
    private List<Jugador> jugador; 
    
    
    //RELACION CON RESULTADOS
    @OneToMany(mappedBy = "actividad")
    private List<Resultados> resultados;

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activ")
    private Integer id_activ;

    @Column(name = "act_nombre")
    private String act_nombre;

    @Column(name = "act_descripcion")
    private String act_descripcion;

    @Column(name = "act_dificultad")
    private String act_dificultad;

    @Column(name = "act_puntaje_max")
    private Integer act_puntaje_max;

    @Column(name = "act_puntaje_min")
    private Integer act_puntaje_min;

    @Column(name = "act_puntaje_alcanzado")
    private Integer act_puntaje_alcanzado;

    @Column(name = "act_aprendizaje")
    private String act_aprendizaje;

    @Column(name = "act_estado")
    private boolean act_estado;
    
    @Column(name = "act_respuesta")
    private String act_respuesta;

}
