package com.proyecto5.model;

import java.io.Serializable;

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

@Getter
@Data
@Setter
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

    //FK USUARIOS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuarios;

    
    //FK ACTIVIDAD
      @OneToMany(mappedBy = "jugador")
      private List<Actividad> actividad;
      
      
      
    
    
    //RELACION EN OTRA
    @OneToMany(mappedBy = "jugador")
    private List<Resultados> resultados;
  

    @OneToMany(mappedBy = "jugador")
    private List<Progreso> progreso;
    
    
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer player_id;

    @Column(name = "player_nombre")
    private String player_nombre;
    
    
    
    

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public String getPlayer_nombre() {
        return player_nombre;
    }

    public void setPlayer_nombre(String player_nombre) {
        this.player_nombre = player_nombre;
    }

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<Actividad> getActividad() {
		return actividad;
	}

	public void setActividad(List<Actividad> actividad) {
		this.actividad = actividad;
	}

    
    
    
    
}
