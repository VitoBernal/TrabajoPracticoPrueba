package ar.edu.unju.fi.poo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Capa de Dominio: representa la tabla "eventos" en MySQL.
 * Hibernate crea la tabla automáticamente al iniciar la app.
 */
@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(length = 100)
	private String lugar;

	private LocalDate fecha;

	// JPA necesita un constructor vacío
	public Evento() {
	}

	public Evento(String nombre, String lugar, LocalDate fecha) {
		this.nombre = nombre;
		this.lugar = lugar;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", lugar=" + lugar + ", fecha=" + fecha + "]";
	}
}
