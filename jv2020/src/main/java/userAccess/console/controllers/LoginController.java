package userAccess.console.controllers;

import dataAccess.DataFacade;
import entitys.Session;
import entitys.User;
import jLife.Configuration;
import userAccess.console.views.LoginView;
import utils.Cryptography;

public class LoginController {

	private LoginView loginView;
	private DataFacade data;
	private Session session;
	private String userId;

	public LoginController() {
		this(null);
	}

	public LoginController(String userIdKnowledge) {	
		this.initLoginController(userIdKnowledge);
		this.loginView.showMessage(Configuration.get().getProperty("aplication.title"));
		this.login();
	}

	private void initLoginController(String userIdKnowledge) {
		this.data = new DataFacade();
		this.loginView = new LoginView();
		this.session = null;
		this.userId = userIdKnowledge;
	}

	private void login() {
		int attempts = Integer.parseInt(Configuration.get().getProperty("session.maxAttempts"));
		do {
			if (this.userId == null) {
				this.userId = this.loginView.requestUserId();	
			}
			String password = this.loginView.requestPassword();		
			if (this.validLogin(password)) {
				loginView.showMessage("Sesión iniciada por: " + this.session.getUser().getName());
				return;
			}
			attempts--;
			this.userId = null;
			this.loginView.showMessage("Credenciales incorrectas... ");
			this.loginView.showMessage("Quedan " + attempts + " intentos... ");
		}
		while (attempts > 0);
	}

	private boolean validLogin(String password) {	
		User user = this.data.findUser(this.userId);
		if (user != null && user.getPassword().getText().equals(Cryptography.cesar(password))) {
			this.session = new Session(user);
			this.data.createSession(session);
			return true;
		} 
		return false;
	}

	public Session getSession() {
		return this.session;
	}
}
