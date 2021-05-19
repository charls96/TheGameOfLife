package userAccess;

import java.util.Scanner;

import dataAccess.DataFacade;
import jLife.Configuration;
import models.Password;
import models.Session;
import models.Simulation;
import models.User;
import utils.EasyDate;

public class UserAccess {
	
	private final int MAX_ATTEMPTS = Integer.parseInt(Configuration.get().getProperty("session.maxAttempts"));
	private Session session;
	private Simulation simulation;


	public Session getSession() {
		return this.session;
	}
	
	public Simulation getSimulation() {
		return this.simulation;
	}
	
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation; 
	}
	
	// Session
	
	public boolean isLoginOK(DataFacade data) {
		Scanner keyboard = new Scanner(System.in);
		int attempts = MAX_ATTEMPTS;
		System.out.println(Configuration.get().getProperty("aplication.title"));
		do {
			System.out.print("Usuario: ");
			String id = keyboard.nextLine();
			System.out.print("Clave de acceso: ");
			String password = keyboard.nextLine();

			User user = data.findUser(id);
			if (user != null 
					&& user.getPassword().equals(new Password(password))) {
				
				this.session = new Session(user);
				data.createSession(this.session);
				return true;
			} 
			else {
				attempts--;
				System.out.print("Credenciales incorrectas: ");
				System.out.println("Quedan " + attempts + " intentos... ");
			} 
		} while (attempts > 0);
		
		return false;
	}
	
	public void closeSession(DataFacade data) {
		this.session.setEndTime(EasyDate.now());
		data.updateSession(this.session);
		
	}

	public void welcome() {
		System.out.println("Sesión: " + "Iniciada por: " 
				+ 	this.session.getUser().getName() 
				+ " "
				+ this.session.getUser().getSurnames());	
	}

	public void simpleMessage(String text) {
		System.out.println(text);	
	}

	// Simulation
	
	public void showDemo() {
		this.simulation = new Simulation();
		this.simulation.setSimulationCicles(this.simulation.MIN_SIMULATION_CICLES);
		this.simulation.run();	
	}
	
}
