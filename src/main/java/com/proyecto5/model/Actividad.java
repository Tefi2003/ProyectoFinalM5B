package com.proyecto5.model;

import java.io.Serializable;
import java.util.List;

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

@Getter
@Data
@Setter
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
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Jugador jugador;
    
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
    
    
    

    public Integer getId_activ() {
        return id_activ;
    }

    public void setId_activ(Integer id_activ) {
        this.id_activ = id_activ;
    }

    public String getAct_nombre() {
        return act_nombre;
    }

    public void setAct_nombre(String act_nombre) {
        this.act_nombre = act_nombre;
    }

    public String getAct_descripcion() {
        return act_descripcion;
    }

    public void setAct_descripcion(String act_descripcion) {
        this.act_descripcion = act_descripcion;
    }

    public String getAct_dificultad() {
        return act_dificultad;
    }

    public void setAct_dificultad(String act_dificultad) {
        this.act_dificultad = act_dificultad;
    }

    public Integer getAct_puntaje_max() {
        return act_puntaje_max;
    }

    public void setAct_puntaje_max(Integer act_puntaje_max) {
        this.act_puntaje_max = act_puntaje_max;
    }

    public Integer getAct_puntaje_min() {
        return act_puntaje_min;
    }

    public void setAct_puntaje_min(Integer act_puntaje_min) {
        this.act_puntaje_min = act_puntaje_min;
    }

    public Integer getAct_puntaje_alcanzado() {
        return act_puntaje_alcanzado;
    }

    public void setAct_puntaje_alcanzado(Integer act_puntaje_alcanzado) {
        this.act_puntaje_alcanzado = act_puntaje_alcanzado;
    }

    public String getAct_aprendizaje() {
        return act_aprendizaje;
    }

    public void setAct_aprendizaje(String act_aprendizaje) {
        this.act_aprendizaje = act_aprendizaje;
    }

	public boolean isAct_estado() {
		return act_estado;
	}

	public void setAct_estado(boolean act_estado) {
		this.act_estado = act_estado;
	}

	public String getAct_respuesta() {
		return act_respuesta;
	}

	public void setAct_respuesta(String act_respuesta) {
		this.act_respuesta = act_respuesta;
	}

	public Recursos getRecursos() {
		return recursos;
	}

	public void setRecursos(Recursos recursos) {
		this.recursos = recursos;
	}

	public TipoAprendizaje getTipoAprendizaje() {
		return tipoAprendizaje;
	}

	public void setTipoAprendizaje(TipoAprendizaje tipoAprendizaje) {
		this.tipoAprendizaje = tipoAprendizaje;
	}

	public Niveles getNiveles() {
		return niveles;
	}

	public void setNiveles(Niveles niveles) {
		this.niveles = niveles;
	}

	public List<Resultados> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultados> resultados) {
		this.resultados = resultados;
	}
    
    

    

}
