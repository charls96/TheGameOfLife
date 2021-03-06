package entitys;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import entityes.Address;
import entityes.Mail;
import entityes.Nif;
import entityes.Password;
import entityes.Person;
import entityes.User;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import utils.EasyDate;

public class PersonTest {

    private static Person personTest1;
    private Person personTest2;

    /**
     * Método que se ejecuta al principio de las pruebas
     */
    @BeforeAll
    private void initializePerson() {
        Nif nif = new Nif("31405204S");
        Address address = new Address("Calle Java", "Nº 1", "30001", "Murcia");
        Mail mail = new Mail("luisgarcia@gmail.com");
        EasyDate birthDate = new EasyDate(1990, 1, 1);
        EasyDate registeredDate = new EasyDate(2021, 1, 1);
        Password password = new Password("abcdefgh");
        personTest1 = new User(nif, "Luis", "García Rodríguez", address, mail, birthDate,
                registeredDate, password, User.RoleUser.REGISTERED);
    } 
     /**
     * Método que se ejecuta antes de cada test.
     */
    @BeforeEach
    void clearTestData() {
        personTest2 = new User();
    }
    
    //Datos válidos
       @Test
     private void testSetNif() {
        Nif nif = new Nif("31701632L");
        personTest2.setNif(nif);
        assertEquals(personTest2.getNif(), nif);
    }

    @Test
    void testSetName() {
        this.personTest2.setName("Luis");
        assertEquals(this.personTest2.getName(), "Luis");
    }
   
   @Test
	void testSetSurnames() {	
		this.personTest2.setSurnames("García Rodríguez");
		assertEquals(this.personTest2.getSurnames(), "García Rodríguez");
	}
         @Test
    void testSetAddress() {
        Address address = new Address("Calle Java", "Nº 1", "30001", "Murcia");
        personTest2.setAddress(address);
        assertEquals(this.personTest2.getAddress(), address);
    }

	@Test
	void testSetMail() {
		Mail mail = new Mail("luisgarcia@gmail.com");
		personTest2.setMail(mail);
		assertEquals(personTest2.getMail(), mail);
	}

	@Test
	void testSetBirthDate() {
		EasyDate birthDate = new EasyDate(1990, 1, 1);
		personTest2.setBirthDate(birthDate);
		assertEquals(personTest2.getBirthDate(), birthDate);
	}

}
   
