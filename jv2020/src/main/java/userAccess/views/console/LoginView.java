package userAccess.views.console;

public class LoginView implements OperationsLoginView {
	
	private Console consola;
	
	public LoginView() {
		consola = System.console();
	}
	
	public void showMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	public String getUserId() {
		System.out.println("Introduzca su usuario");
		String userName = consola.nextLine();
		return userName;
	}
	
	public String getPassword() {
		System.out.println("Introduzca su contraseña");
		String password = consola.nextLine();
		return password;
	}
		
}
