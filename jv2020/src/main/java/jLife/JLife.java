package jLife;

import userAccess.jSwing.controllers.MainController;

public class JLife {

	public static void main(String[] args) {		
		if (args.length > 0) {
			String userIdCommandLine = args[0];
			new userAccess.console.controllers.MainController(userIdCommandLine);
			return;
		}
		new userAccess.jSwing.controllers.MainController();
	}

}