package com.proyecto5.model;

import java.io.Serializable;
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
@Table(name = "usuarios")
public class Usuarios implements Serializable{

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
    private Date usu_fecha_inic;
	
	@Column(name = "usu_rol")
    private String usu_rol;
	
}
