package userAccess.console.views;

import java.io.Console;
import java.util.Scanner;

import userAccess.LoginOperationsView;

public class LoginView implements LoginOperationsView {

private Console console;

	public LoginView() {
		initLoginView();
	}

	private void initLoginView() {
		console = System.console();
	}
	
	@Override
	public void showMessage(String menssage) {
		if (console != null) {
			console.writer().print(menssage);
			return;
		}
		System.out.println(menssage);
	}
	
	@Override
	public String requestUserId() {
		showMessage("Introduce usuario: ");
		if (console != null) {
			return new String(console.readLine()).toUpperCase();
		}
		// Desde entorno de Eclipse la consola falla.
		return new Scanner(System.in).nextLine().toUpperCase();
	}
	
	@Override
	public String requestPassword() {
		showMessage("Introduce clave acceso: ");
		if (console != null) {
			return new String(console.readPassword());
		}
		// Desde entorno de Eclipse la consola falla.
		return new Scanner(System.in).nextLine();
	}
}
