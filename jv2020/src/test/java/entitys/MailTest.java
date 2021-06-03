package entitys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.Mail;
import entitys.ModelsException;

public class MailTest {

	private static Mail mailTest1;
	private Mail mailTest2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		try {
			mailTest1 = new Mail("nombre@gmail.com");
		} 
		catch (ModelsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas. No es
	 * necesario en este caso.
	 */
	@AfterAll
	public static void clearGlobalData() {
		mailTest1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada prueba.
	 */
	@BeforeEach
	public void initializeTestData() {
		mailTest2 = new Mail();
	}

	/**
	 * Método que se ejecuta después de cada prueba.
	 */
	@AfterEach
	public void clearTestData() {
		mailTest2 = null;
	}

	// Test's con DATOS VALIDOS

	@Test
	public void testSetText() {
		// TODO Auto-generated method stub
	}

	// Test con DATOS NO VALIDOS
	
	@Test
	public void testSetTextNotValid() {
		// TODO Auto-generated method stub
	}

	@Test
	public void testSetTextWhite() {
		try {
			mailTest2.setText("  ");
			fail("No debe llegar aquí...");
		} 
		catch (ModelsException e) {		
		}
	}

	@Test
	public void testSetTextNull() {
		// TODO Auto-generated method stub
	}

} 
