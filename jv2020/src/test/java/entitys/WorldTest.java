package entitys;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.Assert.assertEquals;

public class WorldTest {

	private static World world1;
	private World world2;

	@BeforeAll

	public static void iniciarlizeGlobalData() {
		// Objetos globales a todos los test, no modicados en las pruebas.
		try {
			world1 = new World("Demo", new LinkedList<Coordinate>());
		}
		catch (EntitysException e){

		}

	}
	
	@BeforeEach
    public void initializeTestData() {
		world2 = new World();
    }
	
	@AfterEach
	void clearTestData() {
	    this.world2 = null;
	}
	
	@Test
    public void testSetName() {
        world2.setName("World");
        assertEquals(world2.getName(), "World");
    }
}
