package userAccess.console.controllers;

import entitys.Simulation;
import userAccess.console.views.SimulationRunView;
import dataAccess.Data;
import entitys.World;
import entitys.Simulation;
import jLife.Configuration;


public class SimulationRunController {
	private final Integer simulationCicles = Integer
			.parseInt(Configuration.get().getProperty("simulation.defaultCicles"));
	private SimulationRunView simulationRunView;
	private Simulation simulation;
	private World world;
	private Data data;
	
	public SimulationRunController(Simulation simulation) {	
		this.initSimulationRunControler(simulation);
		this.runSimulation();
		this.simulationRunView.showMessageOK();
	}
	
	private void initSimulationRunControler(Simulation simulation) {	
		this.simulationRunView = new SimulationRunView();
		this.simulation = simulation;
	}
	
	private void runSimulation() {
		this.simulation.getWorld().run(simulation.getSimulationCicles());
	}
	public Integer getsimulationCicles() {
		return this.simulationCicles;
	}
	
	public SimulationRunView getSimulationRunView() {
		return this.simulationRunView;
	}

	public Simulation getSimulation() {	
		return this.simulation;
	}
	public World getWorld() {
		return this.world;
	}
	public Data getData() {
		return this.data;
	}
	
}
