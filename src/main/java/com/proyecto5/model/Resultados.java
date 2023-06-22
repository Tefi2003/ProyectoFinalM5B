package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "resultados")
public class Resultados implements Serializable{
	
	
	
	//FK
			@OneToOne(cascade=CascadeType.ALL)
			@JoinColumn(name="id_nivel")
			private Niveles niveles;
			
			
			
	//RELACION CON 
			@OneToMany(mappedBy = "resultados")
		    private List<Jugador> jugador;	
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Integer id_resultado;
	
	@Column(name = "re_puntaje")
    private Integer re_puntaje;
	
	@Column(name = "re_fecha")
    private Timestamp re_fecha;
	
	@Column(name = "re_hora")
    private Date re_hora;
	
	
	
	

	public Integer getId_resultado() {
		return id_resultado;
	}

	public void setId_resultado(Integer id_resultado) {
		this.id_resultado = id_resultado;
	}

	public Integer getRe_puntaje() {
		return re_puntaje;
	}

	public void setRe_puntaje(Integer re_puntaje) {
		this.re_puntaje = re_puntaje;
	}

	public Timestamp getRe_fecha() {
		return re_fecha;
	}

	public void setRe_fecha(Timestamp re_fecha) {
		this.re_fecha = re_fecha;
	}

	public Date getRe_hora() {
		return re_hora;
	}

	public void setRe_hora(Date re_hora) {
		this.re_hora = re_hora;
	}
	
	
	

}
