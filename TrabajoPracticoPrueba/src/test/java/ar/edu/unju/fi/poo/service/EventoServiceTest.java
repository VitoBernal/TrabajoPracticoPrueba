package ar.edu.unju.fi.poo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.fi.poo.entity.Evento;

/**
 * Prueba el servicio contra la base de datos MySQL real.
 * @Transactional deshace los cambios al terminar cada test,
 * así la base queda limpia.
 */
@SpringBootTest
@Transactional
class EventoServiceTest {

	@Autowired
	private EventoService eventoService;

	@Test
	void guardarEvento() {
		Evento evento = new Evento("Feria de Ciencias", "Facultad de Ingeniería", LocalDate.of(2026, 11, 20));

		Evento guardado = eventoService.guardar(evento);

		assertNotNull(guardado.getId());
		assertEquals("Feria de Ciencias", guardado.getNombre());
	}

	@Test
	void buscarEventoPorId() {
		Evento guardado = eventoService.guardar(new Evento("Hackathon", "Aula 5", LocalDate.of(2026, 12, 1)));

		Evento encontrado = eventoService.buscarPorId(guardado.getId());

		assertEquals("Hackathon", encontrado.getNombre());
	}

	@Test
	void listarEventos() {
		eventoService.guardar(new Evento("Charla Java", "Aula 1", LocalDate.of(2026, 11, 10)));
		eventoService.guardar(new Evento("Charla Spring", "Aula 2", LocalDate.of(2026, 11, 11)));

		assertTrue(eventoService.listarTodos().size() >= 2);
	}

	@Test
	void buscarEventosPorLugar() {
		eventoService.guardar(new Evento("Taller MySQL", "Laboratorio Test", LocalDate.of(2026, 11, 15)));

		assertEquals(1, eventoService.buscarPorLugar("Laboratorio Test").size());
	}

	@Test
	void eliminarEvento() {
		Evento guardado = eventoService.guardar(new Evento("Evento a borrar", "Aula 3", LocalDate.of(2026, 11, 5)));
		Long id = guardado.getId();

		eventoService.eliminar(id);

		assertFalse(eventoService.listarTodos().stream().anyMatch(e -> e.getId().equals(id)));
	}

	@Test
	void noGuardaEventoSinNombre() {
		Evento sinNombre = new Evento("", "Aula 4", LocalDate.of(2026, 11, 8));

		assertThrows(IllegalArgumentException.class, () -> eventoService.guardar(sinNombre));
	}

	@Test
	void buscarIdInexistenteLanzaError() {
		assertThrows(RuntimeException.class, () -> eventoService.buscarPorId(-1L));
	}
}
