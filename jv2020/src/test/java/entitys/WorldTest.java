package entitys;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entityes.Coordinate;
import entityes.EntitysException;
import entityes.World;
import entityes.World.GridType;

import org.junit.jupiter.api.AfterEach;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


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
	
	@Test
	public void testSetGridType() {
		world2.setGridType(World.GridType.EDGES);
		assertEquals(world2.getGridType(), GridType.EDGES);
	}
	
	@Test
	public void testSetNameblank() {
		try {
			world2.setName("  ");
			fail("No debe llegar aqui...");
		}
		catch (EntitysException e) {
			assertTrue(world2.getName() != null);			
		}
	}
	
	@Test
    public void testSetNameNull() {
        try {
            world2.setName(null);
            fail("No debe llegar aqui...");
        } 
        catch (AssertionError | EntitysException e) { 
            assertTrue(world2.getName() != null);
        }
    }
	
}
