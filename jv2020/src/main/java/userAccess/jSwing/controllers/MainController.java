package userAccess.jSwing.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import dataAccess.DataFacade;
import entityes.Session;
import entityes.Simulation;
import jLife.Configuration;
import userAccess.jSwing.controllers.SimulationRunController;
import userAccess.jSwing.views.MainView;

public class MainController implements ActionListener, WindowListener {

	private MainView mainView;
	private Session session;
	private DataFacade data;

	public MainController() {
		initMainController();	
	}

	private void initMainController() {
		this.data = new DataFacade();
		this.mainView = new MainView();
		this.configListener();
		this.mainView.pack();
		this.mainView.setVisible(true);
		this.mainView.setEnabled(false);
		new LoginController(this);	
	}
	public void setSesion(Session sesion) {
		assert sesion != null;
		this.session = sesion;
	}
	
	public MainView getMainView() {
		return mainView;
	}
	
	private void configListener() {
		// Hay que escuchar todos los componentes que tengan interacción de la vista
		// registrándoles la clase control que los escucha.
		this.mainView.addWindowListener(this);
		this.mainView.getMntnSave().addActionListener(this);
		this.mainView.getMntnExit().addActionListener(this);

		this.mainView.getMntnNewSimulation().addActionListener(this);
		this.mainView.getMntnDeleteSimulation().addActionListener(this);
		this.mainView.getMntnUpdateSimulacion().addActionListener(this);
		this.mainView.getMntnShowSimulationData().addActionListener(this);
		this.mainView.getMntnSimulationDemo().addActionListener(this);

		this.mainView.getMntnNewWorld().addActionListener(this);
		this.mainView.getMntnDeleteWorld().addActionListener(this);
		this.mainView.getMntnUpdateWorld().addActionListener(this);
		this.mainView.getMntnShowWorldsData().addActionListener(this);

		this.mainView.getMntnNewUser().addActionListener(this);
		this.mainView.getMntnDeleteUser().addActionListener(this);
		this.mainView.getMntnUpdateUser().addActionListener(this);
		this.mainView.getMntnShowUsersData().addActionListener(this);

		this.mainView.getMntnDeleteSession().addActionListener(this);
		this.mainView.getMntnUpdateSession().addActionListener(this);
		this.mainView.getMntnShowSessionsData().addActionListener(this);

		this.mainView.getMntnAbot().addActionListener(this);

		//...
	}

	//Manejador de eventos de componentes... ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.mainView.getMntnSave()) {
			// Ejecutar método asociado
		}

		if(e.getSource() == this.mainView.getMntnExit()) {
			this.exit();
		}

		if(e.getSource() == this.mainView.getMntnNewSimulation()) {
			this.newSimulation();
		}

		if(e.getSource() == this.mainView.getMntnDeleteSimulation()) {
			this.deleteSimulation();
		}

		if(e.getSource() == this.mainView.getMntnUpdateSimulacion()) {
			this.updateSimulation();
		}

		if(e.getSource() == this.mainView.getMntnShowSimulationData()) {
			this.showSimulationData();
		}

		if(e.getSource() == this.mainView.getMntnSimulationDemo()) {
			this.runSimulationDemo();
		}

		if(e.getSource() == this.mainView.getMntnNewWorld()) {
			this.newWorld();
		}

		if(e.getSource() == this.mainView.getMntnDeleteWorld()) {
			this.deleteWorld();
		}

		if(e.getSource() == this.mainView.getMntnUpdateWorld()) {
			this.updateWorld();
		}

		if(e.getSource() == this.mainView.getMntnShowWorldsData()) {
			this.showWorldData();
		}

		if(e.getSource() == this.mainView.getMntnNewUser()) {
			this.newUser();
		}

		if(e.getSource() == this.mainView.getMntnDeleteUser()) {
			this.deleteUser();
		}

		if(e.getSource() == this.mainView.getMntnUpdateUser()) {
			this.updateUser();
		}

		if(e.getSource() == this.mainView.getMntnShowUsersData()) {
			this.showUserData();
		}

		if(e.getSource() == this.mainView.getMntnDeleteSession()) {
			this.deleteSession();
		}

		if(e.getSource() == this.mainView.getMntnUpdateSession()) {
			this.updateSession();
		}

		if(e.getSource() == this.mainView.getMntnShowSessionsData()) {
			this.showSessionData();
		}

		if(e.getSource() == this.mainView.getMntnAbot()) {
			this.mainView.showAbout();
		}
	}

	private void exit() {
		if (this.mainView.confirmMessage("Confirma que quieres salir...")) {
			this.data.close();
			System.exit(0);
		}
	}

	private void newSimulation() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void updateSimulation() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteSimulation() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void showSimulationData() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void runSimulationDemo() {
		Simulation simulationDemo = new Simulation();
		simulationDemo.setSimulationCycles(Integer.parseInt((Configuration.get().getProperty("simulation.defaultCycles"))));
		new SimulationRunController(simulationDemo);
	}

	private void newWorld() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void updateWorld() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteWorld() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void showWorldData() {
		this.mainView.showMessage("Opción no disponible...");
	}

	// Usuarios	
	private void newUser() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void updateUser() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteUser() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void showUserData() {
		this.mainView.showMessage("Opción no disponible...");
	}

	// Sesiones	
	private void updateSession() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteSession() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void showSessionData() {
		this.mainView.showMessage("Opción no disponible...");

	}

	// Manejadores de eventos de ventana... Wnidowslistener
	@Override
	public void windowClosing(WindowEvent arg0) {
		exit();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// No usado
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// No usado
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// No usado
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// No usado
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// No usado
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// No usado
	}

} 
