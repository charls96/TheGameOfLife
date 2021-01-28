import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	private static User user1; 
	private User user2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void resetData() {	
		user1 = new User("00000001R"
				,"Luis"
				,"Roca Mora"
				,"Roncal, 10, 30130, Murcia"
				,"luis@gmail.com"
				,"2000-10-12"
				,"2020-10-12"
				,"Miau#12", 
				"REGISTERED"
				);
		
		user2 = new User();
	}
	
	// Test's CON DATOS VALIDOS
	
	@Test
	public void testCopia() {
		
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
		user2.setNif("00000001R");
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
		user2.setAddress("Roncal, 10, 30130, Murcia");
		assertEquals(user2.getAddress(), "Roncal, 10, 30130, Murcia");
	}
	
	@Test
	public void testSetMail() {
		user2.setMail("luis@gmail.com");
		assertEquals(user2.getMail(), "luis@gmail.com");
	}
	
	@Test
	public void testSetBirthDate() {
		user2.setBirthDate("1999-12-12");
		assertEquals(user2.getBirthDate(), "1999-12-12");
	}
	
	@Test
	public void testSetRegisteredDate() {
		user2.setRegisteredDate("2021-10-10");
		assertEquals(user2.getRegisteredDate(), "2021-10-10");
	}

	@Test
	public void testSetPassword() {
		user2.setPassword("Miau#12");
		assertEquals(user2.getPassword(), "Miau#12");
	}

	@Test
	public void testSetRol() {
		user2.setRole("GUEST");
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
			user1.setBirthDate("2025-10-12");
			// No debe haber cambios...
			assertEquals(user1.getBirthDate(), "2000-10-10");
	}
	
	@Test
	public void testSetBirthDateMayor() {	
			user1.setBirthDate("2018-10-12");
			// No debe haber cambios...
			assertEquals(user1.getBirthDate(), "2000-10-10");
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
			user1.setRegisteredDate("2025-10-12");
			// No debe haber cambios...
			assertEquals(user1.getRegisteredDate(), "2020-10-12");
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
			user2.setPassword("  ");	
			// No debe haber cambios...
			assertEquals(user2.getPassword(), "Miau#0");
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
