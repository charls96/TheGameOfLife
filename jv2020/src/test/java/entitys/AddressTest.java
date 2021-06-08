package entitys;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.Address;
import entitys.ModelsException;


public class AddressTest {
	private static Address addressTest1; 
	private Address addressTest2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		// Objetos globales a todos los test, no modicados en las pruebas.
		addressTest1 = new Address("Calle Prueba", "5", "30800", "Poblacion");
	}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas.
	 * No es necesario en este caso.
	 */
	@AfterAll
	public static void clearGlobalData() {
		addressTest1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void initializeTestData() {	
		this.addressTest2 = new Address();
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@AfterEach
	public void clearTestData() {
		this.addressTest2 = null;
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	public void testSetStreet() {
		this.addressTest2.setStreet("Calle Larga");
		assertEquals(this.addressTest2.getStreet(), "Calle Larga");
	}
	
	@Test
	public void testSetNumber() {
		this.addressTest2.setNumber("0");
		assertEquals(this.addressTest2.getNumber(), "0");
	}
	
	@Test
	public void testSetPostalCode() {
		this.addressTest2.setPostalCode("00000");
		assertEquals(this.addressTest2.getPostalCode(), "00000");
	}
	
	@Test
	public void testSetLocation() {
		// TODO Auto-generated method stub	
	}

	@Test
	void testClone() {
		// TODO Auto-generated method stub
	}
	
	@Test
	void testEqualsObject() {	
		assertTrue(addressTest1.equals(addressTest1.clone()));
	}

	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetStreetNull() {
		try {
			this.addressTest2.setStreet(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetStreetNotValid() {
		try {
			this.addressTest2.setStreet("n0 valid4");
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) { 
		}
	}
	
	@Test
	public void testSetNumberNull() {
		try {
			this.addressTest2.setNumber(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) { 
}
	}
	
	@Test
	public void testSetNumberNotValid() {
		try {
			this.addressTest2.setNumber("n0 valid4");
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) { 
		}
	}
	
	@Test
	public void testSetPostalCodeNull() {
		try {
			this.addressTest2.setPostalCode(null);
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) { 
		}
	}
	
	@Test
	public void testSetPostalCodeNotValid() {
		try {
			this.addressTest2.setPostalCode("n0 valid4");
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) { 
		}
	}
	
    @Test
	public void testSetLocationNull() {
		try {
			this.addressTest2.setLocation(null);
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) { 
		}
	}
	
	@Test
	public void testSetLocationNotValid() {
		// TODO Auto-generated method stub
	}

} 