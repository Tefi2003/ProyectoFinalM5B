package com.proyecto5.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tipoAprendizaje")
public class TipoAprendizaje implements Serializable {

    //RELACION CON ACTIVIIDAD
    @OneToMany(mappedBy = "tipoAprendizaje")
    private List<Actividad> actividad;

    //RELACION CON PROGRESO_APRENDIZAJE
    @OneToOne(mappedBy = "tipoAprendizaje")
    private ProgresoAprendizaje progresoAprendizaje;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_apren")
    private Integer id_tipo_apren;

    @Column(name = "tapr_nombre")
    private String tapr_nombre;

    @Column(name = "tapr_progreso")
    private int tapr_progreso;

    public Integer getId_tipo_apren() {
        return id_tipo_apren;
    }

    public void setId_tipo_apren(Integer id_tipo_apren) {
        this.id_tipo_apren = id_tipo_apren;
    }

    public String getTapr_nombre() {
        return tapr_nombre;
    }

    public void setTapr_nombre(String tapr_nombre) {
        this.tapr_nombre = tapr_nombre;
    }

    public int getTapr_progreso() {
        return tapr_progreso;
    }

    public void setTapr_progreso(int tapr_progreso) {
        this.tapr_progreso = tapr_progreso;
    }

}
