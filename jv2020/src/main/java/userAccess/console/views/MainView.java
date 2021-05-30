package userAccess.console.views;

import java.io.Console;
import java.util.Scanner;

import jLife.Configuration;
import userAccess.OperationsView;

public class MainView implements OperationsView {

	private Console console;
	private int activeOption;
	
	public MainView() {
		initMainView();
	}

	private void initMainView() {
		console = System.console();
		activeOption = 0;
	}
	
	public int getActiveOption() {
		return activeOption;
	}
	
	public void showMainMenu() {
		this.showMessage("\n-- " 
							+ Configuration.get().getProperty("aplication.title")
							+ " GESTIÓN PRINCIPAL --");
		this.showMessage(
				"  \nSIMULACIONES\n" +
						"    1. Crear nueva simulación\n" +
						"    2. Modificar simulación existente\n" +
						"    3. Eliminar simulación exitente\n" +
						"    4. Mostrar datos de simulaciones\n" +
						"    5. Mostrar identificadores de simulaciones\n" +
						"    6. Ejecutar simulación de demostración\n" +
						"  \nMUNDOS\n" +
						"    7. Crear nuevo mundo\n" +
						"    8. Modificar mundo existente\n" +
						"    9. Eliminar mundo existente\n" +
						"    10. Mostrar datos de mundos\n" +
						"  \nUSUARIOS\n" +
						"    11. Crear nuevo usuario\n" +
						"    12. Modificar usuario existente\n" +
						"    13. Eliminar usuario existente\n" +
						"    14. Mostrar datos de usuarios\n" +
						"  \nSESIONES\n" +
						"    15. Modificar sesión existente\n" +
						"    16. Eliminar sesión existente\n" +
						"    17. Mostrar datos de sesiones\n" +
						"    18. Mostrar identificadores de sesiones\n\n" +
						"     0. SALIR\n " +
						"\nElige una opción: \n" 
				);
	}

	public void requestOption() {
		if (console != null) {
			activeOption = Integer.parseInt(console.readLine());
			return;
		}
		// Desde el entorno Eclipse la consola falla.
		activeOption = new Scanner(System.in).nextInt();
	}

	@Override
	public void showMessage(String menssage) {
		if (console != null) {
			console.writer().println(menssage);
			return;
		}
		// Desde el entorno Eclipse la consola falla.
		System.out.println(menssage);
		
	}

}
