package dataAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataAccess.DataAccessException;
import dataAccess.DataFacade;
import dataAccess.memory.WorldsDAO;
import dataAccess.memory.UsersDAO;
import entitys.Address;
import entitys.Mail;
import entitys.Nif;
import entitys.Password;
import entitys.Session;
import entitys.World;
import entitys.User;
import utils.EasyDate;

class WorldsDAOTest {

	private static DataFacade data;
	private static World worldTestNotExist;
	private World worldTest;
	
	
	@BeforeAll
	static void initialConfig() {	
		data = new DataFacade();
		worldTestNotExist = worldTestNotExist();
	}
	
	private static World worldTestNotExist() {
		World localWorldTest = new World();
		localWorldTest.setName("WorldTestNotExist");
		return localWorldTest;
	}
	
	@AfterAll
	static void finalConfig() {	
		data.close();
	}

	@BeforeEach
	void initialConfigTest() {	
		this.worldTest = worldTestNotExist();
		this.worldTest.setName("WorldTest");
	}
	
	@AfterEach
	void finalConfigTestTest() {	
		this.worldTest = null;
	}
	
	@Test
	void testGetInstance() {
		WorldsDAO singleton = WorldsDAO.getInstance();	
		assertSame(WorldsDAO.getInstance(), singleton);
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	void testFind() {	
		// TODO
		fail("Test sin implementar.");
	}
	
	@Test
	void testFindAll() {
		data.createWorld(this.worldTest);
		assertEquals(data.findAllWorlds().size(), 2);
		data.deleteWorld(this.worldTest);
	}

	@Test
	void testCreate() {		
		data.createWorld(this.worldTest);
		assertSame(this.worldTest, data.findWorld(this.worldTest));
		data.deleteWorld(this.worldTest);
	}
	
	@Test
	void testDelete() {	
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testUpdate() {
		data.createWorld(this.worldTest);
		World localWorldTest = data.findWorld(this.worldTest);
		localWorldTest.setConstants(new HashMap<String,int[]>());
		data.updateWorld(localWorldTest);
		assertEquals(data.findWorld(localWorldTest.getId()), localWorldTest);
		data.deleteWorld(this.worldTest);
	}

	@Test
	void testDeleteAll() {	
		// TODO
		fail("Test sin implementar.");
	}
	
	// Test's CON DATOS ANÓMALOS
	
	@Test
	void testFindWorldNotExist() {
		assertNull(data.findWorld(worldTestNotExist));
	}
	
	@Test
	void testDeleteWorldNotExist() {	
		// TODO
		fail("Test sin implementar.");
	}
	
	@Test 
	void testUpdateWorldNotExist() {
		try {	
			data.updateWorld(worldTestNotExist); 
			fail("No debe llegar aquí...");
		} 
		catch (DataAccessException e) {	
		}
	}

}
