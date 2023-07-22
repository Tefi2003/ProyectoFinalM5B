package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {
/*
    //RELACION CON JUGADOR
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Jugador jugador;
*/
    //FK: RELACION CON ROL
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
    private String correo;

    @Column(name = "usu_nivelacademico")
    private String usu_nivelacademico;

    @Column(name = "usu_fecha_inic")
    private Timestamp usu_fecha_inic;
    
    @Column(name = "usu_fecha_nacimiento")
    private Date usu_fecha_nacimiento;


    //Generacion de usu_fecha_inic cuando se cree una tabla
    @PrePersist
    public void prePersist() {
        usu_fecha_inic = new Timestamp(System.currentTimeMillis());
    }
    

}
