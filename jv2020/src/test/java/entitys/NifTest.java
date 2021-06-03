package entitys;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import entitys.Nif;

public class NifTest {
	private static Nif nifTest1; 
	private Nif nifTest2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		// Objetos globales a todos los test, no modificados en las pruebas.
		nifTest1 = new Nif();
	}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas.
	 * No es necesario en este caso.
	 */
	@AfterAll
	public static void clearGlobalData() {
		nifTest1 = null;
	}
	
	/**
	 * Método que se ejecuta antes de cada prueba.
	 */
	@BeforeEach
	public void initializeTestData() {	
		this.nifTest2 = new Nif();
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@AfterEach
	public void clearTestData() {
		this.nifTest2 = null;
	}
	
} 
