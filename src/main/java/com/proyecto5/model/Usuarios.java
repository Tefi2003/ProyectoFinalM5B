package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "usuarios")
public class Usuarios implements Serializable{
	
	
	
	//en otra
		@OneToOne(mappedBy = "usuarios")
			private Jugador jugador;
		
		@ManyToOne
	    @JoinColumn(name = "id_rol")
	    private Roles roles;
		
		

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id_usuario;
	
	@Column(name = "usu_nombre")
    private String usu_nombre;

	@Column(name = "usu_contra")
    private String usu_contra;
	
	@Column(name = "usu_correo")
    private String usu_correo;
	
	@Column(name = "usu_nivelacademico")
    private String usu_nivelacademico;
	
	@Column(name = "usu_fecha_inic")
    private Timestamp usu_fecha_inic;
	
	@Column(name = "usu_rol")
    private String usu_rol;
	
	
	
	

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsu_nombre() {
		return usu_nombre;
	}

	public void setUsu_nombre(String usu_nombre) {
		this.usu_nombre = usu_nombre;
	}

	public String getUsu_contra() {
		return usu_contra;
	}

	public void setUsu_contra(String usu_contra) {
		this.usu_contra = usu_contra;
	}

	public String getUsu_correo() {
		return usu_correo;
	}

	public void setUsu_correo(String usu_correo) {
		this.usu_correo = usu_correo;
	}

	public String getUsu_nivelacademico() {
		return usu_nivelacademico;
	}

	public void setUsu_nivelacademico(String usu_nivelacademico) {
		this.usu_nivelacademico = usu_nivelacademico;
	}

	public Timestamp getUsu_fecha_inic() {
		return usu_fecha_inic;
	}

	public void setUsu_fecha_inic(Timestamp usu_fecha_inic) {
		this.usu_fecha_inic = usu_fecha_inic;
	}

	public String getUsu_rol() {
		return usu_rol;
	}

	public void setUsu_rol(String usu_rol) {
		this.usu_rol = usu_rol;
	}
	
	
	
	
	
}
