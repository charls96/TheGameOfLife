package userAccess.views.console;
import java.io.Console;

public class MainView implements OperationsView {
	
	private Console consola;
	private int opcionActivada;

	public MainView() {
		consola = System.console();
	}
	public void showMenu() {
		consola.printf("1. Crear usuario");
		consola.printf("2. Acualizar usuario");
		consola.printf("3. Eliminar usuario");
		consola.printf("4. Imprimir usuarios");
	
	}
}
