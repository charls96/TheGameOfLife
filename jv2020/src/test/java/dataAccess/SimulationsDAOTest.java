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
import entitys.Nif;
import entitys.Simulation;
import entitys.User;
import entitys.World;
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
		assertEquals(data.findSimulation(this.simulationTest), this.simulationTest);
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
		data.createSimulation(this.simulationTest);
		Simulation localSimulationTest = data.findSimulation(this.simulationTest);
		localSimulationTest.setDate(EasyDate.today().minusDays(1));
		data.updateSimulation(localSimulationTest);
		assertEquals(data.findSimulation(localSimulationTest.getId()), localSimulationTest);
		data.deleteSimulation(localSimulationTest);
	}

	@Test
	void testDeleteAll() {	
		data.deleteAllSimulations();
		assertEquals(data.findAllSimulations().size(), 0);
	}
	
	// Test's CON DATOS ANÓMALOS
	
	@Test
	void testFindSimulationNotExist() {
		assertNull(data.findSimulation(simulationTestNotExist));
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
		try {	
			data.updateSimulation(simulationTestNotExist); 
			fail("No debe llegar aquí...");
		} 
		catch (DataAccessException e) {	
		}
	}

}
