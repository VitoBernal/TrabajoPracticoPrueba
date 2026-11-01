package ar.edu.unju.fi.poo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.entity.Evento;
import ar.edu.unju.fi.poo.repository.EventoRepository;

/**
 * Capa de Lógica de Negocio.
 * Valida los datos y usa el repositorio para hablar con la base de datos.
 */
@Service
public class EventoService {

	private final EventoRepository eventoRepository;

	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public Evento guardar(Evento evento) {
		if (evento.getNombre() == null || evento.getNombre().isBlank()) {
			throw new IllegalArgumentException("El nombre del evento es obligatorio");
		}
		return eventoRepository.save(evento);
	}

	public Evento buscarPorId(Long id) {
		return eventoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No existe un evento con id " + id));
	}

	public List<Evento> listarTodos() {
		return eventoRepository.findAll();
	}

	public List<Evento> buscarPorLugar(String lugar) {
		return eventoRepository.findByLugar(lugar);
	}

	public void eliminar(Long id) {
		eventoRepository.deleteById(id);
	}
}
