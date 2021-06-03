package entitys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.Address;
import entitys.Mail;
import entitys.Nif;
import entitys.Password;
import entitys.Session;
import entitys.User;
import utils.EasyDate;

class SessionTest {

	private static Session sessionTest1;
	private static User userTest;
	private Session sessionTest2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		// Objetos no modificados en las pruebas.
		userTest = new User(new Nif("00000001R"), 
				"Luis", "Roca Mora",
				new Address("Roncal", "10", "30130", "Murcia"), 
				new Mail("luis@gmail.com"), 
				new EasyDate(2000, 3, 21),
				new EasyDate(2019, 11, 17), 
				new Password("Miau#12"), 
				User.RoleUser.REGISTERED);
		sessionTest1 = new Session(userTest); 
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void initializeTestData() {	
			sessionTest2 = new Session();
	}

	/**
	 * Método que se ejecuta después de cada pruebas.
	 */
	@AfterEach
	public void clearGlobalData() {	
		sessionTest2 = null;
	}

	// Test's con DATOS VALIDOS

	@Test
	public void testSetUser() {
		this.sessionTest2.setUser(userTest);
		assertEquals(this.sessionTest2.getUser(), userTest);
	}

	@Test
	public void testSetEndTime() {
		// TODO Auto-generated method stub	
	}

	@Test
	void testClone() {
		assertNotSame(this.sessionTest1, this.sessionTest1.clone());
	}
	
	@Test
	void testEqualsObject() {	
		// TODO Auto-generated method stub
	}

	
	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetUserNull() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testSetEndDateNull() {
		try {
			sessionTest2.setEndTime(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
} 
