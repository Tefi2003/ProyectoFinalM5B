package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

    //RELACION CON JUGADOR
    @JsonIgnore
    @OneToOne(mappedBy = "usuarios", cascade = CascadeType.ALL)
    private Jugador jugador;

    //FK: RELACION CON ROL
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Roles roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;

    @Column(name = "usu_nombre", nullable = false)
    private String usu_nombre;

    @Column(length = 255, name = "usu_contra", nullable = false)
    private String usu_contra;

    @Column(name = "usu_correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "usu_nivelacademico", nullable = false)
    private String usu_nivelacademico;

    @Column(name = "usu_fecha_inic", nullable = false)
    private Timestamp usu_fecha_inic;
    
    @Column(name = "usu_fecha_nacimiento", nullable = false)
    private String usu_fecha_nacimiento;


    //Generacion de usu_fecha_inic cuando se cree una tabla
    @PrePersist
    public void prePersist() {
        usu_fecha_inic = new Timestamp(System.currentTimeMillis());
    }
    

}
