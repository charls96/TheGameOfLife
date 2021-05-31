package userAccess.console.controllers;

import dataAccess.DataFacade;
import entitys.Session;
import entitys.Simulation;
import userAccess.console.views.MainView;
import utils.EasyDate;

public class MainController {

	private MainView mainView;
	private Session session;
	private DataFacade data;
	
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
			this.mainView.showMessage("Opción incorrecta...");
			break;
		}	
	}

	private void showIdSessions() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void showDataSessions() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void deleteSession() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void modifySession() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void showDataUsers() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteUser() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void modifyUser() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void createNewUser() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void showDataWorlds() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void deleteWorld() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void modifyWorld() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void createNewWorld() {
		this.mainView.showMessage("Opción no disponible...");
	}

	private void RunSimulationDemo() {
		new SimulationRunController(new Simulation());
	}

	private void showIdsSimulations() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void showDataSimulations() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void deleteSimulation() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void modifySimulation() {
		this.mainView.showMessage("Opción no disponible...");	
	}

	private void createNewSimulation() {
		this.mainView.showMessage("Opción no disponible...");	
	}
	
}
