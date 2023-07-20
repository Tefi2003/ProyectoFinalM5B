package com.proyecto5.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "niveles")
public class Niveles implements Serializable {
/*
    //RELACION CON Actividad
    @OneToMany(mappedBy = "niveles")
    private List<Actividad> actividad;
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Integer id_nivel;

    @Column(name = "nv_numero_nivel")
    private Integer nv_numero_nivel;


}