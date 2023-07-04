package com.proyecto5.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "progresoAprendizaje")
public class ProgresoAprendizaje {

    //FK PROGRESO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_progress")
    private Progreso progreso;

    //FK TIPO APRENDIZAJE
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_apren")
    private TipoAprendizaje tipoAprendizaje;

    
    @ManyToOne
    @JoinColumn(name = "id_resultado")
    private Resultados resultados;
    
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progapre")
    private Integer id_progapre;

    @Column(name = "progapr_nombre")
    private String progapr_nombre;

    @Column(name = "progapr_punntaje_aprendizaje")
    private Integer progapr_punntaje_aprendizaje;

    public Integer getId_progapre() {
        return id_progapre;
    }

    public void setId_progapre(Integer id_progapre) {
        this.id_progapre = id_progapre;
    }

    public String getProgapr_nombre() {
        return progapr_nombre;
    }

    public void setProgapr_nombre(String progapr_nombre) {
        this.progapr_nombre = progapr_nombre;
    }

    public Integer getProgapr_punntaje_aprendizaje() {
        return progapr_punntaje_aprendizaje;
    }

    public void setProgapr_punntaje_aprendizaje(Integer progapr_punntaje_aprendizaje) {
        this.progapr_punntaje_aprendizaje = progapr_punntaje_aprendizaje;
    }

}