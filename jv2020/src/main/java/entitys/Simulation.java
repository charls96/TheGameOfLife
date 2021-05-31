package entitys;

import java.io.Serializable;

import utils.EasyDate;

public class Simulation implements Identifiable, Serializable {
	
	public final int MIN_SIMULATION_CICLES = 20;
	public enum SimulationState {PREPARED, RUNNING, FINISHED};
	
	private User user;
	private EasyDate date;
	private World world;
	private SimulationState state;
	private int simulationCicles;

	public Simulation() {
		this(new User(), EasyDate.now(), new World());
	}
	
	public Simulation(User user, EasyDate date, World world) {
		this.setUser(user);
		this.setDate(date);
		this.setWorld(world);
		this.simulationCicles = Integer.MAX_VALUE;
		this.state = SimulationState.PREPARED;
	}

	public Simulation(Simulation simulation) {
		assert simulation != null;	
		this.user = simulation.user;
		this.date =	EasyDate.today();
		this.state = simulation.state;
	}

	public void run() {
		this.state = SimulationState.RUNNING;
		if (this.world.run(this.simulationCicles) >= simulationCicles) {
			this.state = SimulationState.FINISHED;
		}
	}
	
	@Override
	public String getId() { 
		return this.user.getId() + ":" + this.date.toStringTimeStamp();
	}
	
	public User getUser() {
		return this.user;
	}

	public EasyDate getDate() {
		return this.date;
	}

	public World getWorld() {	
		return world;
	}
	
	public int getSimulationCicles() {
		return this.simulationCicles;
	}

	public SimulationState getState() {
		return this.state;
	}

	public void setUser(User user) {
		assert user != null;
		this.user = user;
	}

	public void setDate(EasyDate date) {
		assert date != null;	
		if (isValidDate(date)) {
			this.date = date;
			return;
		}
		throw new ModelsException("Date: incorrecta.");
	}
	
	private boolean isValidDate(EasyDate date) {	
		return !date.isAfter(EasyDate.now());
	}

	public void setWorld(World world) {
		assert world != null;
		this.world = world;	
	}

	public void setSimulationCicles(int simulationCicles) {
		if (simulationCicles > 0) {
			this.simulationCicles = simulationCicles;
			return;
		}
		throw new ModelsException("Simulation: Ciclos fuera de rango...");
	}
	
	
	
	@Override
	public String toString() {
		return String.format(			
				"%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n"
						+ "%15s %-15s\n",
						"user:", this.user.getName(), 
						"date:", this.date, 
						"world:", this.world, 
						"state:", this.state
				);
	}

	@Override
	public Simulation clone() {
		return new Simulation(this);
	}
	
	
} 
