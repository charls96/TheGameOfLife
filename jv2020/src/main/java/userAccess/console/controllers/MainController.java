package userAccess.console.controllers;

import java.util.List;

import dataAccess.DataFacade;
import entityes.Identifiable;
import entityes.Session;
import entityes.Simulation;
import jLife.Configuration;
import userAccess.console.views.MainView;
import utils.EasyDate;

public class MainController {

	private MainView mainView;
	private Session session;
	private DataFacade data;
	private Simulation simulation;
	
	public MainController() {
		this(null);
	}
	
	public MainController(String userIdCommandLine) {
		this.initMainController(userIdCommandLine);	
		if (this.session != null) {
			this.mainLoop();
			return;
		}
		this.exit();
	}
	
	private void exit() {	
		this.session.setEndTime(EasyDate.now());
		this.data.updateSession(this.session);
		this.data.close();
		this.mainView.showMessage("\nFin de programa...");
		System.exit(1); 
	}
	
	private void initMainController(String userIdCommandLine) {
		this.data = new DataFacade();
		this.mainView = new MainView();
		this.session = new LoginController(userIdCommandLine).getSession();
	}

	private void mainLoop() {
		do {
			this.mainView.showMainMenu();
			this.mainView.requestOption();
			this.processOption();	
		} while (true);
	}

	private void processOption() {
		switch (mainView.getActiveOption()) {
		case 0:
			this.exit();

			// Simulaciones
		case 1:
			this.createNewSimulation();
			break;
		case 2:
			this.modifySimulation();
			break;
		case 3:
			this.deleteSimulation();
			break;
		case 4:
			this.showDataSimulations();
			break;
		case 5:
			this.showIdsSimulations();
			break;
		case 6:
			this.RunSimulationDemo();
			break;

			// Mundos
		case 7:
			this.createNewWorld();
			break;
		case 8:
			this.modifyWorld();
			break;
		case 9:
			this.deleteWorld();
			break;
		case 10:
			this.showDataWorlds();
			break;

			// Usuarios
		case 11:
			this.createNewUser();
			break;
		case 12:
			this.modifyUser();
			break;
		case 13:
			this.deleteUser();
			break;
		case 14:
			this.showDataUsers();
			break;

			// Sesiones
		case 15:
			this.modifySession();
			break;
		case 16:
			this.deleteSession();
			break;
		case 17:
			this.showDataSessions();
			break;
		case 18:
			this.showIdSessions();
			break;
		default:
			this.mainView.showMessage("Opci??n incorrecta...");
			break;
		}	
	}

	private void showIdSessions() {
		List<Identifiable> sesiones = data.findAllSessionsUser(session.getUser().getId());
		for (Identifiable i:sesiones) mainView.showMessage(i.getId());
	}

	private void showDataSessions() {
		mainView.showMessage(data.toStringDataSessions());	
	}

	private void deleteSession() {
		data.deleteSession(session);
		mainView.showMessage("Eliminada la sesion " + session.getId());
	}

	private void modifySession() {
		data.updateSession(session);
		mainView.showMessage("Modificada la sesion " + session.getId());
	}

	private void showDataUsers() {
		mainView.showMessage(data.toStringDataUsers());
	}

	private void deleteUser() {
		data.deleteUser(session.getUser().getId());
	}

	private void modifyUser() {
		data.updateUser(session.getUser());
		mainView.showMessage("El usuario ha sido modificado correctamente");
	}

	private void createNewUser() {
		this.mainView.showMessage("Opci??n no disponible...");	
	}

	private void showDataWorlds() {
		mainView.showMessage(data.toStringDataWorlds());
	}

	private void deleteWorld() {
		this.mainView.showMessage("Opci??n no disponible...");	
	}

	private void modifyWorld() {
		this.mainView.showMessage("Opci??n no disponible...");
	}

	private void createNewWorld() {
		this.mainView.showMessage("Opci??n no disponible...");
	}

	private void RunSimulationDemo() {
		Simulation simulationDemo = new Simulation();
		simulationDemo.setSimulationCycles(
				Integer.parseInt(Configuration.get().getProperty("simulation.defaultCicles")));
		new SimulationRunController(new Simulation());
	}

	private void showIdsSimulations() {
		List<Identifiable> simulations = data.findAllSimulations();
		for (Identifiable i:simulations) mainView.showMessage(i.getId());
	}

	private void showDataSimulations() {
		mainView.showMessage(data.toStringDataSimulations());	
	}

	private void deleteSimulation() {
		data.deleteSimulation(session.getId());
		
	}

	private void modifySimulation() {
		data.updateSimulation(data.findSimulation(simulation.getId()));
		mainView.showMessage("La simulacion ha sido modificada correctamente");	
		
	}

	private void createNewSimulation() {
		this.mainView.showMessage("Opci??n no disponible...");
	}
	
}
