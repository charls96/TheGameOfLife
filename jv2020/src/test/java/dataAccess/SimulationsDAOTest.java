package dataAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataAccess.db4o.SimulationsDAO;
import entityes.Nif;
import entityes.Simulation;
import entityes.User;
import entityes.World;
import utils.EasyDate;

class SimulationsDAOTest {

	private static DataFacade data;
	private static Simulation simulationTestNotExist;
	private Simulation simulationTest;
	
	
	@BeforeAll
	static void initialConfig() {	
		data = new DataFacade();
		simulationTestNotExist = simulationTestNotExist();
	}
	
	private static Simulation simulationTestNotExist() {
		User localUserTest = new User();
		localUserTest.setNif(new Nif("00000008P")); 
		Simulation localSimulationTest = new Simulation(localUserTest, EasyDate.today(), new World());
		return localSimulationTest;
	}
	
	@AfterAll
	static void finalConfig() {	
		data.deleteAllSimulations();
		data.close();
	}

	@BeforeEach
	void initialConfigTest() {	
		this.simulationTest = simulationTestNotExist();
		this.simulationTest.setDate(EasyDate.today().minusDays(2));
	}
	
	@AfterEach
	void finalConfigTest() {	
		this.simulationTest = null;
	}
	
	@Test
	void testGetInstance() {
		SimulationsDAO singleton = SimulationsDAO.getInstance();	
		assertSame(SimulationsDAO.getInstance(), singleton);
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	void testFind() {	
		data.createSimulation(this.simulationTest);
		assertEquals(data.findSimulations(this.simulationTest), this.simulationTest);
		data.deleteSimulation(this.simulationTest);
	}
	
	@Test
	void testFindAll() {
		data.createSimulation(this.simulationTest);
		assertEquals(data.findAllSimulations().size(), 1);
		data.deleteSimulation(this.simulationTest);
	}

	@Test
	void testCreate() {		
		data.createSimulation(this.simulationTest);
		assertSame(this.simulationTest, data.findSimulation(this.simulationTest));
		data.deleteSimulation(this.simulationTest);
	}
	
	@Test
	void testDelete() {	
		data.createSimulation(this.simulationTest);
		assertSame(data.deleteSimulation(this.simulationTest), this.simulationTest);
	}

	@Test
	void testUpdate() {
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testDeleteAll() {	
		data.deleteAllSimulations();
		assertEquals(data.findAllSimulations().size(), 0);
	}
	
	// Test's CON DATOS ANÓMALOS
	
	@Test
	void testFindSimulationNotExist() {
		// TODO
		fail("Test sin implementar.");
	}
	
	@Test
	void testDeleteSimulationNotExist() {	
		try {
			data.deleteSimulation(simulationTestNotExist);
			fail("No debe llegar aquí...");		
		} 
		catch (DataAccessException e) {	
		}
	}
	
	@Test 
	void testUpdateSimulationNotExist() {
		// TODO
		fail("Test sin implementar.");
	}

}
