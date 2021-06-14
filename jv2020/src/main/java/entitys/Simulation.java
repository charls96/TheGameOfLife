package entitys;

import java.io.Serializable;

import entitys.Simulation.SimulationState;
import utils.EasyDate;

public class Simulation implements Identifiable, Serializable {
	
	public enum SimulationState {PREPARED, RUNNING, FINISHED};
	
	private User user;
	private EasyDate date;
	private World world;
	private SimulationState state;
	private int simulationCycles;

	public Simulation() {
		this(new User(), EasyDate.now(), new World());
	}
	
	public Simulation(User user, EasyDate date, World world) {
		this.setUser(user);
		this.setDate(date);
		this.setWorld(world);
		this.simulationCycles = Integer.MAX_VALUE;
		this.state = SimulationState.PREPARED;
	}

	public Simulation(Simulation simulation) {
		assert simulation != null;	
		this.user = simulation.user;
		this.date =	EasyDate.today();
		this.state = simulation.state;
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
	
	public int getSimulationCycles() {
		return this.simulationCycles;
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
		throw new EntitysException("Date: incorrecta.");
	}
	
	private boolean isValidDate(EasyDate date) {	
		return !date.isAfter(EasyDate.now());
	}

	public void setWorld(World world) {
		assert world != null;
		this.world = world;	
	}

	public void setSimulationCycles(int simulationCycles) {
		if (simulationCycles > 0) {
			this.simulationCycles = simulationCycles;
			return;
		}
		throw new EntitysException("Simulation: Ciclos fuera de rango...");
	}
	
	public void setState(SimulationState state) {
		assert state != null;
		this.state = state;	
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
