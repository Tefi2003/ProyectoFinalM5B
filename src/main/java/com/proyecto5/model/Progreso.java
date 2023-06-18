package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Data
@Setter
@Entity
@Table(name = "progreso")
public class Progreso implements Serializable{

	
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
    private Date prog_fecha_init;
    
    @Column(name = "prog_fecha_act")
    private Date prog_fecha_act;
    
    @Column(name = "prog_tipo_aprendizaje")
    private String prog_tipo_aprendizaje;

    
    
    
    
    
    
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

	public Date getProg_fecha_init() {
		return prog_fecha_init;
	}

	public void setProg_fecha_init(Date prog_fecha_init) {
		this.prog_fecha_init = prog_fecha_init;
	}

	public Date getProg_fecha_act() {
		return prog_fecha_act;
	}

	public void setProg_fecha_act(Date prog_fecha_act) {
		this.prog_fecha_act = prog_fecha_act;
	}

	public String getProg_tipo_aprendizaje() {
		return prog_tipo_aprendizaje;
	}

	public void setProg_tipo_aprendizaje(String prog_tipo_aprendizaje) {
		this.prog_tipo_aprendizaje = prog_tipo_aprendizaje;
	}
    
   
}
