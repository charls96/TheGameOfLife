package entitys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entitys.User.RoleUser;
import utils.EasyDate;

class UserTest {
	private static User userTest1;
	private User userTest2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto de pruebas.
	 */
	@BeforeAll
	static void iniciarlizeGlobalData() {
		userTest1 = new User(new Nif("00000008P"), "Pepe", "Márquez Alón", new Address("Alta", "10", "30012", "Murcia"),
				new Mail("pepe@gmail.com"), new EasyDate(1990, 11, 12), new EasyDate(2020, 02, 05),
				new Password("Miau#32"), RoleUser.ADMIN);
	}

	/**
	 * Método que se ejecuta antes de cada @test.
	 */
	@BeforeEach
	void initializeTestData() {
		this.userTest2 = new User();
	}

	/**
	 * Método que se ejecuta al terminar cada @test.
	 */
	@AfterEach
	void clearTestData() {
		this.userTest2 = null;
	}

	// TEST CON DATOS VÁLIDOS

	@Test
	public void testUser() {
		try {
			assertEquals(userTest1.getNif(), new Nif("00000008P"));
			assertEquals(userTest1.getName(), "Pepe");
			assertEquals(userTest1.getSurnames(), "Márquez Alón");
			assertEquals(userTest1.getAddress(), new Address("Alta", "10", "30012", "Murcia"));
			assertEquals(userTest1.getMail(), new Mail("pepe@gmail.com"));
			assertEquals(userTest1.getBirthDate(), new EasyDate(1990, 11, 12));
			assertEquals(userTest1.getRegisteredDate(), new EasyDate(2020, 02, 05));
			assertEquals(userTest1.getPassword(), new Password("Miau#32"));
			assertEquals(userTest1.getRole(), RoleUser.GUEST);

		} catch (EntitysException e) {
		}
	}

	@Test
	public void testUserDefault() {
		try {
			assertEquals(userTest2.getNif(), new Nif());
			assertEquals(userTest2.getName(), "Guest");
			assertEquals(userTest2.getSurnames(), "Guest");
			assertEquals(userTest2.getAddress(), new Address());
			assertEquals(userTest2.getMail(), new Mail());
			assertEquals(userTest2.getBirthDate(), new User().getBirthDate());
			assertEquals(userTest2.getRegisteredDate().getYear(), new EasyDate().getYear());
			assertEquals(userTest2.getRegisteredDate().getMonth(), new EasyDate().getMonth());
			assertEquals(userTest2.getRegisteredDate().getDay(), new EasyDate().getDay());
			assertEquals(userTest2.getPassword(), new Password());
			assertEquals(userTest2.getRole(), RoleUser.GUEST);

		} catch (EntitysException e) {
		}
	}

	@Test
	public void testUserCopy() {
		User user = new User(userTest1);
		assertNotSame(user, userTest1);
		assertNotSame(user.getNif(), userTest1.getNif());
		assertNotSame(user.getName(), userTest1.getName());
		assertNotSame(user.getSurnames(), userTest1.getSurnames());
		assertNotSame(user.getAddress(), userTest1.getAddress());
		assertNotSame(user.getMail(), userTest1.getMail());
		assertNotSame(user.getBirthDate(), userTest1.getBirthDate());
		assertNotSame(user.getRegisteredDate(), userTest1.getRegisteredDate());
		assertNotSame(user.getPassword(), userTest1.getPassword());
		assertNotSame(user.getRole(), userTest1.getRole());
	}

	@Test
	void testSetRegisteredDate() {
		EasyDate dateTest = new EasyDate(2020, 10, 12);
		userTest2.setRegisteredDate(dateTest);
		assertEquals(userTest2.getRegisteredDate(), dateTest);
	}

	@Test
	void testSetClaveAcceso() {

		Password localPassword;
		try {
			localPassword = new Password("Miau#32");
			userTest2.setPassword(localPassword);
			assertEquals(userTest2.getPassword(), localPassword);
		} catch (EntitysException e) {
		}
	}

	@Test
	void testSetRole() {
		userTest2.setRole(User.RoleUser.REGISTERED);
		assertEquals(userTest1.getRole(), RoleUser.REGISTERED);
	}

	@Test
	void testEqualsObject() {
		// TODO Auto-generated method stub
	}

	@Test
	void testClone() {
		assertNotSame(userTest1, userTest1.clone());
	}

	// TEST CON DATOS NO VÁLIDOS
	// *************************

	@Test
	void testSetFechaAltaNull() {
		try {
			userTest2.setRegisteredDate(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) {
		}
	}

	@Test
	void testSetFechaAltaMayor() {
		try {
			userTest2.setBirthDate(new EasyDate(4050, 2, 10));
			fail("No debe llegar aquí...");
		} catch (EntitysException e) {
		}
	}

	@Test
	void testSetPasswordNull() {
		try {
			userTest2.setPassword(null);
			fail("No debe llegar aquí....");
		} catch (AssertionError e) {
			assertTrue(userTest2.getPassword() != null);
		}
	}

	@Test
	void testSetRoleNull() {
		try {
			userTest2.setRole(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) {
			assertTrue(userTest2.getRole() != null);
		}
	}

}
