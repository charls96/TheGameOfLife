package models;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.Password;

class PasswordTest {

	private static Password passwordTest1;
	private Password passwordTest2;
	
	/** 
	 * Método que se ejecuta una sola vez al principio del conjunto de pruebas
	 */
	@BeforeAll
	static void iniciarlizeGlobalData() {	
		passwordTest1 = new Password("ClaveTest");
	}

	/**
	 * Método que se ejecuta antes de cada @test.
	 */
	@BeforeEach
	void clearTestData() {
		this.passwordTest2 = new Password();
	}
	
	// TEST CON DATOS VÁLIDOS

	@Test
	void testSetText() {	
		// TODO Auto-generated method stub	
	}
	
	@Test
	void testEqualsObject() {	
		Password claveTest = new Password(this.passwordTest1);	
		assertEquals(this.passwordTest1.getText(), claveTest.getText());
	}

	@Test
	void testClone() {
		// TODO Auto-generated method stub
	}
	
	// TEST CON DATOS NO VÁLIDOS
	//*************************
	
	@Test
	void testSetPasswordNull() {	
		try {	
			this.passwordTest2.setText(null);		
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) {
		}	
	}
	
	@Test
	public void testSetPasswordNotValid() {
		// TODO Auto-generated method stub
	}


}
