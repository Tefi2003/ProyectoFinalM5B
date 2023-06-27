package com.proyecto5.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Data
@Setter
@Entity
@Table(name = "niveles")
public class Niveles implements Serializable {

    //RELACION CON ACTIVIDAD
    @OneToMany(mappedBy = "niveles")
    private List<Actividad> actividad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Integer id_nivel;

    @Column(name = "nv_numero_nivel")
    private Integer nv_numero_nivel;

    public Integer getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(Integer id_nivel) {
        this.id_nivel = id_nivel;
    }

    public Integer getNv_numero_nivel() {
        return nv_numero_nivel;
    }

    public void setNv_numero_nivel(Integer nv_numero_nivel) {
        this.nv_numero_nivel = nv_numero_nivel;
    }

}