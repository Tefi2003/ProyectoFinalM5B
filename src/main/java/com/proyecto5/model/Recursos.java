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
@Table(name = "recursos")
public class Recursos implements Serializable {
/*
    //RELACION CON Actividad
    @JsonIgnore
    @OneToMany(mappedBy = "recursos")
    private List<Actividad> actividad;
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer id_recurso;

    @Column(name = "rec_enlaces")
    private String rec_enlaces;

}

