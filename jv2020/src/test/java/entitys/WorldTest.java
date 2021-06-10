package entitys;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeAll;

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
}
