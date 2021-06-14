package userAccess.jSwing.controllers;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dataAccess.DataAccessException;
import dataAccess.DataFacade;
import entityes.Password;
import entityes.Session;
import entityes.User;
import jLife.Configuration;
import userAccess.jSwing.views.LoginView;
import utils.Cryptography;
import utils.EasyDate;

public class LoginController implements ActionListener, MouseListener {

	private MainController mainController;
	private LoginView loginView;
	private Session session;
	private DataFacade data;
	private int attempts;

	public LoginController(MainController mainController) {
		this.mainController = mainController;
		this.initLoginController();
	}

	private void initLoginController() {
		this.data = new DataFacade();
		this.loginView = new LoginView();
		this.loginView.setModalityType(ModalityType.MODELESS);
		this.configListener();
		this.loginView.pack();
		this.loginView.setVisible(true);
		this.attempts = Integer.parseInt(Configuration.get().getProperty("session.maxAttempts"));
	}


	/**
	 * Configura los componentes con interacción de la vista asociándoles un ActionListener
	 */
	private void configListener() {
		// Registra la clase que controla eventos de cada componente.
		this.loginView.getButtonOk().addActionListener(this);
		this.loginView.getButtonCancel().addActionListener(this);
		this.loginView.getUserField().addActionListener(this);
		this.loginView.getPasswordField().addActionListener(this);
		this.loginView.getLblHelp().addMouseListener(this);
	}

	//Manejador de eventos de componentes... ActionListener
	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource() == this.loginView.getButtonOk()) {
			// Procesa campos.
			this.login();
		}

		if(evento.getSource() == this.loginView.getButtonCancel()) {
			this.data.close();
			System.exit(0);
		}

		if(evento.getSource() == this.loginView.getUserField()) {
			// Intro en campo usuario pasa foco a campo claveAcceso.
			this.loginView.getPasswordField().requestFocus();
		}

		if(evento.getSource() == this.loginView.getPasswordField()) {
			// Procesa campos.
			this.login();
		}
	}

	//Manejador de evento de raton...
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.loginView.getLblHelp()) {
			this.loginView.showMessage("Sólo pueden iniciar sesión los usuarios registrados...");
		}
	}

	private void login() {				
		if (this.validLogin()) {
			this.loginView.dispose();
			this.mainController.getMainView().setEnabled(true);
			this.mainController.getMainView().requestFocus();
			this.mainController.setSesion(this.session);
			this.loginView.showMessage("Sesión: " + this.session.getId()
			+ '\n' + "Iniciada por: " + this.session.getUser().getName());
			return;
		} 
		this.attempts--;
		if (this.attempts < 1)	{
			this.loginView.showMessage("Fin del programa.");
			this.data.close();
			System.exit(0);
		}
		this.loginView.showMessage("Credenciales incorrectas...\n"
				+ "Quedan " + attempts + " intentos. ");
		this.loginView.getUserField().setText("");
		this.loginView.getUserField().requestFocus();
		this.loginView.getPasswordField().setText("");
	}

	private boolean validLogin() {	
		String userId = this.loginView.getUserField().getText().toUpperCase();
		String password = new String(this.loginView.getPasswordField().getPassword());
		User user = this.data.findUser(userId);
		if (user != null && user.getPassword().getText().equals(Cryptography.cesar(password))) {
			this.session = new Session(user);
			this.data.createSession(session);
			return true;
		} 
		return false;
	}

	// Manejadores de eventos de ratón no usados.
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

} //class
