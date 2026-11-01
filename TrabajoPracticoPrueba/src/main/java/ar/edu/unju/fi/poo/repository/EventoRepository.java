package ar.edu.unju.fi.poo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.entity.Evento;

/**
 * Capa de Acceso a Datos.
 * JpaRepository ya trae save, findById, findAll, deleteById, count, etc.
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	// Spring genera la consulta a partir del nombre del método:
	// SELECT * FROM eventos WHERE lugar = ?
	List<Evento> findByLugar(String lugar);
}
