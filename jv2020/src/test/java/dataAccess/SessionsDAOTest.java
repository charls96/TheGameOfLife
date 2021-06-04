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

import dataAccess.db4o.SessionsDAO;
import entitys.Nif;
import entitys.Session;
import entitys.User;
import utils.EasyDate;

class SessionsDAOTest {

	private static DataFacade data;
	private static Session sessionTestNotExist;
	private Session sessionTest;
	
	
	@BeforeAll
	static void initialConfig() {	
		data = new DataFacade();
		sessionTestNotExist = sessionTestNotExist();
	}
	
	private static Session sessionTestNotExist() {
		User localUserTest = new User();
		localUserTest.setNif(new Nif("00000008P")); 
		Session localSessionTest = new Session(localUserTest);
		return localSessionTest;
	}
	
	@AfterAll
	static void finalConfig() {	
		data.deleteAllSessions();
		data.close();
	}

	@BeforeEach
	void initialConfigTest() {	
		this.sessionTest = sessionTestNotExist();
		this.sessionTest.setEndTime(EasyDate.now().plusHours(1));
	}
	
	@AfterEach
	void finalConfigTest() {	
		this.sessionTest = null;
	}
	
	@Test
	void testGetInstance() {	
		// TODO
		fail("Test sin implementar.");		
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	void testFind() {	
		data.createSession(this.sessionTest);
		assertEquals(data.findSession(this.sessionTest), this.sessionTest);
		data.deleteSession(this.sessionTest);
	}
	
	@Test
	void testFindAll() {
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testCreate() {		
		data.createSession(this.sessionTest);
		assertSame(this.sessionTest, data.findSession(this.sessionTest));
		data.deleteSession(this.sessionTest);
	}
	
	@Test
	void testDelete() {	
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testUpdate() {
		data.createSession(this.sessionTest);
		Session localSessionTest = data.findSession(this.sessionTest);
		localSessionTest.setEndTime(EasyDate.now().plusHours(1));
		data.updateSession(localSessionTest);
		assertEquals(data.findSession(localSessionTest.getId()), localSessionTest);
		data.deleteSession(this.sessionTest);
	}

	@Test
	void testDeleteAll() {	
		// TODO
		fail("Test sin implementar.");
	}
	
	// Test's CON DATOS ANÓMALOS
	
	@Test
	void testFindSessionNotExist() {
		assertNull(data.findSession(sessionTestNotExist));
	}
	
	@Test
	void testDeleteSessionNotExist() {	
		// TODO
		fail("Test sin implementar.");
	}
	
	@Test 
	void testUpdateSessionNotExist() {
		try {	
			data.updateSession(sessionTestNotExist); 
			fail("No debe llegar aquí...");
		} 
		catch (DataAccessException e) {	
		}
	}

}
