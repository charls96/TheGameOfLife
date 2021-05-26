package jLife;

import dataAccess.DataFacade;
import userAccess.UserAccess;

public class JLife {

	private UserAccess userAccess;
	private DataFacade data;
	
	public JLife() {
		this.userAccess = new UserAccess();
		this.data = new DataFacade();
	}

	public static void main(String[] args) {				
		JLife jLife = new JLife();		
		
		if (jLife.userAccess.isLoginOK(jLife.data)) {
			jLife.userAccess.welcome();
			jLife.userAccess.showDemo();
			jLife.userAccess.closeSession(jLife.data);
		}
		else {
			jLife.userAccess.simpleMessage("\nDemasiados intentos fallidos...");
		}
		jLife.userAccess.simpleMessage("Fin del programa.");

	}

	
}