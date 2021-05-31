package jLife;

import userAccess.console.controllers.MainController;

public class JLife {

	public static void main(String[] args) {		
		if (args.length > 0) {
			String userIdCommandLine = args[0];
			new MainController(userIdCommandLine);
			return;
		}
		new MainController();
	}

}