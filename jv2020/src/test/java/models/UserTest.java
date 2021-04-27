package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.User;
import models.User.RoleUser;
import utils.EasyDate;

public class UserTest {
	private static User user1; 
	private User user2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@BeforeAll
	public static void initData() {
		// Objetos no modicados en las pruebas.
		user1 = new User(new Nif("00000001R"),
				"Luis",
				"Roca Mora",
				new Address("Roncal", "10", "30130", "Murcia"),
				new Mail("luis@gmail.com"),
				new EasyDate(2000, 10, 12),
				new EasyDate(2020, 10, 12),
				new Password("Miau#12"), 
				RoleUser.REGISTERED
				);
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void resetData() {		
		user2 = new User();
	}

	// Test's CON DATOS VALIDOS

	@Test
	public void testClon() {

		User user;

		//user = new User(user1);

		user = user1.clone();

		assertNotSame(user, user1);

		assertNotSame(user.getNif(), (user1.getNif()));
		assertEquals(user.getNif(), (user1.getNif()));

		assertNotSame(user.getName(), (user1.getName()));
		assertEquals(user.getName(), (user1.getName()));

		assertNotSame(user.getSurnames(), (user1.getSurnames()));
		assertEquals(user.getSurnames(), (user1.getSurnames()));

		assertNotSame(user.getAddress(), (user1.getAddress()));
		assertEquals(user.getAddress(), (user1.getAddress()));

		assertNotSame(user.getMail(), (user1.getMail()));
		assertEquals(user.getMail(), (user1.getMail()));

		assertNotSame(user.getBirthDate(), (user1.getBirthDate()));
		assertEquals(user.getBirthDate(), (user1.getBirthDate()));

		assertNotSame(user.getRegisteredDate(), (user1.getRegisteredDate()));
		assertEquals(user.getRegisteredDate(), (user1.getRegisteredDate()));

		assertNotSame(user.getPassword(), (user1.getPassword()));
		assertEquals(user.getPassword(), (user1.getPassword()));

		assertNotSame(user.getRole(), (user1.getRole()));
		assertEquals(user.getRole(), (user1.getRole()));
	}

	@Test
	public void testSetNif() {
		user2.setNif(new Nif("00000001R"));
		assertEquals(user2.getNif(), "00000001R");
	}

	@Test
	public void testSetName() {
		user2.setName("Luis");
		assertEquals(user2.getName(), "Luis");
	}

	@Test
	public void testSetSurnames() {
		user2.setSurnames("Roca Mora");
		assertEquals(user2.getSurnames(), "Roca Mora");
	}

	@Test
	public void testSetAddress() {
		user2.setAddress(new Address("Roncal", "10", "30130", "Murcia"));
		assertEquals(user2.getAddress(), "Roncal, 10, 30130, Murcia");
	}

	@Test
	public void testSetMail() {
		user2.setMail(new Mail("luis@gmail.com"));
		assertEquals(user2.getMail(), "luis@gmail.com");
	}

	@Test
	public void testSetBirthDate() {
		EasyDate date = new EasyDate(1999, 12, 12);
		user2.setBirthDate(date);
		assertEquals(user2.getBirthDate(), date);
	}

	@Test
	public void testSetRegisteredDate() {
		EasyDate date = new EasyDate(2021, 01, 10);
		user2.setRegisteredDate(date);
		assertEquals(user2.getRegisteredDate(), date);
	}

	@Test
	public void testSetPassword() {
		user2.setPassword(new Password("Miau#12"));
		assertEquals(user2.getPassword(), "Miau#12");
	}

	@Test
	public void testSetRol() {
		user2.setRole(RoleUser.GUEST);
		assertEquals(user2.getRole(), "GUEST");
	}

	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetNameNull() {
		try {
			user2.setName(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetSurnamesNull() {
		try {
			user2.setSurnames(null);

			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetAddressNull() {
		try {
			user2.setAddress(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetMailNull() {
		try {
			user2.setMail(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetBirthDateNull() {
		try {
			user2.setBirthDate(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetBirthDateFuture() {	
		EasyDate date = user1.getBirthDate();
		user1.setBirthDate(new EasyDate(2025, 10, 12));
		// No debe haber cambios...
		assertEquals(user1.getBirthDate(), date);
	}

	@Test
	public void testSetBirthDateMayor() {	
		EasyDate date = user1.getBirthDate();
		user1.setBirthDate(new EasyDate(2018, 10, 12));
		// No debe haber cambios...
		assertEquals(user1.getBirthDate(), date);
	}

	@Test
	public void testSetRegisteredDateNull() {
		try {
			user2.setRegisteredDate(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) {	
		}
	}

	@Test
	public void testSetRegisteredDateFuture() {	
		EasyDate date = user1.getRegisteredDate();
		user1.setRegisteredDate(new EasyDate(2125, 10, 12));
		// No debe haber cambios...
		assertEquals(user1.getRegisteredDate(), date);
	}

	@Test
	public void testSetPasswordNull() {
		try {
			user2.setPassword(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
	}

	@Test
	public void testSetPasswordWhite() {
		user2.setPassword(new Password("  "));	
		// No debe haber cambios...
		assertEquals(user2.getPassword(), "Miau#00");
	}

	@Test
	public void testSetRoleNull() {
		try {
			user2.setRole(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
	}

} 
