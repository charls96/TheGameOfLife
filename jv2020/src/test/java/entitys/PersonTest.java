package entitys;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EasyDate;

public class PersonTest {

    private Person personTest1;
    private Person personTest2;

    /**
     * Método que se ejecuta al principio de las pruebas
     */
    @BeforeAll
    private void initializePerson() {
        Nif nif = new Nif("31405204S");
        Address address = new Address("Calle Java", "Nº 1", "30001", "Murcia");
        Mail mail = new Mail("luisgarcia@gmail.com");
        EasyDate dob = new EasyDate(1990, 1, 1);
        personTest1 = new Person(nif, "Luis", "García Rodríguez", address, mail, dob);
    }
} 
