package entitys;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	PasswordTest.class,
	MailTest.class,
	AddressTest.class,
	NifTest.class,
	SessionTest.class,
	SimulationTest.class,
	UserTest.class
})

public class AllTests {

}
