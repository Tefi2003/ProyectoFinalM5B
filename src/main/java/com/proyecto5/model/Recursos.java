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
import java.sql.Blob;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
@Entity
@Table(name = "recursos")
public class Recursos implements Serializable {

    //RELACION CON Actividad
    @OneToMany(mappedBy = "recursos")
    private List<Actividad> actividad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer id_recurso;

    @Column(name = "rec_enlaces")
    private String rec_enlaces;

    @Column(name = "rec_pdfs")
    private Blob rec_pdfs;
    
    @Column (name = "rec_imagenes")
    private Blob rec_imagenes;

}

