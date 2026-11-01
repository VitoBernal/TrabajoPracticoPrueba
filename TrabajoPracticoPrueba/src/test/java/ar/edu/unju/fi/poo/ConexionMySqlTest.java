package ar.edu.unju.fi.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Si este test pasa, tu proyecto se conecta bien a MySQL.
 */
@SpringBootTest
class ConexionMySqlTest {

	@Autowired
	private DataSource dataSource;

	@Test
	void seConectaAMySql() throws Exception {
		try (Connection conexion = dataSource.getConnection()) {
			assertTrue(conexion.isValid(2));
			assertEquals("MySQL", conexion.getMetaData().getDatabaseProductName());
			System.out.println("Conectado a MySQL " + conexion.getMetaData().getDatabaseProductVersion());
		}
	}
}
