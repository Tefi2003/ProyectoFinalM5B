package com.proyecto5.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

    //FK USUARIOS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;

    //FK ACTIVIDAD
    @ManyToOne
    @JoinColumn(name = "id_activ")
    private Actividad actividad;

    //RELACION CON progreso
    @OneToOne
    @JoinColumn(name = "id_progress")
    private Progreso progreso;
/*
    @ManyToOne
    @JoinColumn(name = "id_progress")
    private Progreso progreso;
 */
/*
    @OneToMany(mappedBy = "jugador")
    private List<Progreso> progreso;
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer player_id;

    @Column(name = "player_nombre")
    private String player_nombre;


}
