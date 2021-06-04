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

import dataAccess.db4o.UsersDAO;
import entitys.Address;
import entitys.Mail;
import entitys.Nif;
import entitys.Password;
import entitys.Simulation;
import entitys.User;
import entitys.User.RoleUser;
import utils.EasyDate;

class UsersDAOTest {

	private static DataFacade data;
	private static User userTestNotExist;
	private User userTest;

	@BeforeAll
	static void initialConfig() {	
		data = new DataFacade();
		userTestNotExist = userTestNotExist();
	}

	private static User userTestNotExist() {
		return new User(new Nif("00000008P"), "Pepe", "Márquez Alón", 
				new Address("Alta", "10", "30012", "Murcia"), 
				new Mail("pepe@gmail.com"), new EasyDate(1990, 11, 12),
				new EasyDate(2018, 02, 05), new Password("Miau#32"), RoleUser.REGISTERED);
	}

	@AfterAll
	static void finalConfig() {	
		data.deleteAllUsers();
		data.close();
	}

	@BeforeEach
	void initialConfigTest() {	
		this.userTest = userTestNotExist();
		this.userTest.setNif(new Nif("00000002W"));
	}

	@AfterEach
	void finalConfigTest() {	
		this.userTest = null;
	}

	@Test
	void testGetInstance() {
		UsersDAO singleton = UsersDAO.getInstance();	
		assertSame(UsersDAO.getInstance(), singleton);
	}

	// Test's CON DATOS VALIDOS

	@Test
	void testFind() {	
		assertEquals(data.findUser("00000000T").getId(), "00000000T");
		assertEquals(data.findUser("admin@gmail.com").getId(), "00000000T");
	}

	@Test
	void testFindAll() {
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testCreate() {		
		data.createUser(this.userTest);
		assertSame(userTest, data.findUser(this.userTest));
		data.deleteUser(this.userTest);
	}

	@Test
	void testDelete() {	
		data.createUser(this.userTest);
		assertSame(data.deleteUser(this.userTest), this.userTest);
	}

	@Test
	void testUpdate() {
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testDeleteAll() {	
		data.deleteAllUsers();	
		// Predeterminados del sistema...
		assertEquals(data.findUser("00000000T").getId(), "00000000T");
		assertEquals(data.findUser("00000001R").getId(), "00000001R");
	}

	// Test's CON DATOS ANÓMALOS

	@Test
	void testFindUserNotExist() {
		assertNull(data.findUser(userTestNotExist));
	}

	@Test
	void testCreateUserRepeated () {	
		// TODO
		fail("Test sin implementar.");
	}

	@Test
	void testDeleteUserNotExist() {	
		try {
			data.deleteUser(userTestNotExist);
			fail("No debe llegar aquí...");		
		} 
		catch (DataAccessException e) {	
		}
	}

	@Test 
	void testUpdateUserNotExist() {
		// TODO
		fail("Test sin implementar.");
	}

}
