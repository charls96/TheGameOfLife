package userAccess.console.controllers;

import entitys.Simulation;
import userAccess.console.views.SimulationRunView;

public class SimulationRunController {
	
	private SimulationRunView simulationRunView;
	private Simulation simulation;
	
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
	
	public SimulationRunView getSimulationRunView() {
		return this.simulationRunView;
	}

	public Simulation getSimulation() {	
		return this.simulation;
	}
	
}
