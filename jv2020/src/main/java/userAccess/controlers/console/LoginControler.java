package userAccess.controllers.console;

import models.Session;
import userAccess.UserAccessException;
import userAccess.views.console.LoginView;

public class LoginControler {
	
	private LoginView loginview;
	private Session session;
	private DataFacade fachada;
	
	public LoginControler() {	
		initSessionControler();
	}

	public LoginView getLoginview() {
		return loginview;
	}

	public Session getSession() {
		return session;
	}
	
	private void initSessionControler() {
		loginview = new LoginView();
		fachada = new DataFacade();
		try {
			initLogin();
		}
		catch(UserAccessException e) {
			loginView.showMessage(e.getMessage);
		}
	}
	
	private void initLogin() throws UserAccessException {
		String userName = loginview.getUserId();
		String password = loginview.getPassword();
		if (userName == null || password == null) throw new UserAccessException("Nombre de usuario y constraseña invalidos");
		User usuario = fachada.findUser(userName);
		if (usuario == null) throw new UserAccessException("Ese usuario no está registrado"); 
		session = new Session(usuario);
		UserAccess useraccess = new UserAccess();
		useraccess.showDemo();
	}
	
	
	
}
