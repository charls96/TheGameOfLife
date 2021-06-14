package userAccess.jSwing.controllers;

import entityes.Simulation;
import entityes.World;
import jLife.Configuration;
import userAccess.jSwing.views.SimulationRunView;

public class SimulationRunController {
	private SimulationRunView simulationView;
	private Simulation simulation;
	private World world;
	
	public SimulationRunController(Simulation simulation) {
		assert simulation != null;
		this.simulation = simulation;
		this.initSimulationRunController();
	}
	
	private void initSimulationRunController() {	
		this.world = simulation.getWorld();	
		this.simulationView = new SimulationRunView();
		this.simulationView.pack();
		this.simulationView.setVisible(true);
		this.runSimulation();
	}
	
	public void runSimulation() {
		int generation = 0; 
		do {
			this.simulationView.getTextViewingArea().append("\nGeneración: " + generation + "\n");
			this.simulationView.showSimulation(this);
			this.world.updateGrid();
			generation++;
		}
		while (generation <= simulation.getSimulationCycles());
	}
	
	public Simulation getSimulation() {
		return this.simulation;
	}
	
	public World getWorld() {
		return this.world;
	}
	
} 
