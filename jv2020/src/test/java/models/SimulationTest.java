package models;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.Address;
import entitys.Mail;
import entitys.Nif;
import entitys.Password;
import entitys.Simulation;
import entitys.User;
import entitys.World;
import entitys.User.RoleUser;
import utils.EasyDate;

public class SimulationTest {

	private static User usrTest;
	private static EasyDate dateTest;
	private static World worldTest;
	private static Simulation simulationTest1;
	private Simulation simulationTest2;

	/**
	 * Metodo que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		// Objetos no modificados en las pruebas.
		usrTest = new User(new Nif("00000000T"), "Jose", "Riquelme Serrano",
				new Address("Roncal", "5", "30152", "Murcia"), 
				new Mail("jose@gmail.com"),
				new EasyDate(2000, 01, 20), 
				new EasyDate(2018, 10, 15), 
				new Password("Miau#12"), 
				RoleUser.REGISTERED);
		dateTest = new EasyDate(2018, 10, 20, 10, 35, 2);
		worldTest = new World();
		simulationTest1 = new Simulation(usrTest, dateTest, worldTest);
	} 

	@AfterAll
	public static void clearGlobalData() {
		simulationTest1 = null;
	}

	@BeforeEach
	public void initializeTestData() {
		this.simulationTest2 = new Simulation();
	}

	// Test's CON DATOS NO VALIDOS
	
	@Test
	public void testSetUser() {
		this.simulationTest2.setUser(usrTest);
		assertSame(simulationTest2.getUser(), usrTest);
	}

	@Test
	public void testSetWorld() {
		this.simulationTest2.setWorld(worldTest);
		assertSame(simulationTest2.getWorld(), worldTest);


	}

	@Test
	public void testSetDate() {
		this.simulationTest2.setDate(dateTest);
		assertSame(simulationTest2.getDate(), dateTest);


	}

	@Test
	void testClone() {	
		// TODO Auto-generated method stub	
	}
	
	
	@Test
	void testEqualsObject() {	
		Simulation simulationTest = simulationTest1.clone();
		assertTrue(simulationTest1.equals(simulationTest));
	}
	
	// Test's CON DATOS NO VALIDOS
	
	@Test
	public void testSetUserNull() {
		// TODO Auto-generated method stub
	}

	@Test
	public void testSetWorldNull() {
		try {
			simulationTest2.setWorld(null);
			fail("No debe llegar aqui...");
		} 
		catch (AssertionError e) {
		}
	}

	@Test
	public void testSetFechaNull() {
		// TODO Auto-generated method stub
	}

}
