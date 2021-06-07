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
        EasyDate birthDate = new EasyDate(1990, 1, 1);
        EasyDate registeredDate = new EasyDate(2021, 1, 1);
        Password password = new Password("abcdefgh");
        personTest1 = new User(nif, "Luis", "García Rodríguez", address, mail, birthDate,
                registeredDate, password, User.RoleUser.REGISTERED);
    }
}
