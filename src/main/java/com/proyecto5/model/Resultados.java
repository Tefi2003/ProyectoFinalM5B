package com.proyecto5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@Entity
@Table(name = "resultados")
public class Resultados implements Serializable {

    
    //FK ACTIVIDAD
    @ManyToOne
    @JoinColumn(name = "id_activ")
    private Actividad actividad;

    //FK JUGADOR
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Jugador jugador; 
    
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "resultados")
    private List<ProgresoAprendizaje> progresoAprendizaje; 
    
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Integer id_resultado;

    @Column(name = "re_puntaje")
    private Integer re_puntaje;

    @Column(name = "re_fecha")
    private Timestamp re_fecha;

    @Column(name = "re_hora")
    private Date re_hora;

}
