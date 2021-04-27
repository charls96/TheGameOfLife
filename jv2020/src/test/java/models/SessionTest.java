package models;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.User.RoleUser;
import utils.EasyDate;

class SessionTest {

	private static Session session1;
	private Session session2;
	private static User user;

	
	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void initData() {
		// Objetos no modicados en las pruebas.
		user = new User(new Nif("00000001R"),
				"Luis",
				"Roca Mora",
				new Address("Roncal", "10", "30130", "Murcia"),
				new Mail("luis@gmail.com"),
				new EasyDate(2000, 10, 12),
				new EasyDate(2020, 10, 12),
				new Password("Miau#12"), 
				RoleUser.REGISTERED
				);
		session1 = new Session(user);
	}
	
	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		session2 = new Session();
	}

	/**
	 * Método que se ejecuta después de cada pruebas.
	 */
	@AfterEach
	public void borrarDatosPrueba() {	
		session2 = null;
	}

	// Test's con DATOS VALIDOS
	@Test
	public void testSessionClon() {
		assertNotSame(session1, new Session(session1));
	}

	@Test
	public void testSetUsr() {
		session2.setUser(user);
		assertEquals(session2.getUser(), user);
	}


	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetUserNull() {
		try {
			session2.setUser(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
			assertTrue(session2.getUser() != null);
		}
	}
	

} 
