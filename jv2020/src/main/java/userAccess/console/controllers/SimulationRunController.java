package userAccess.console.controllers;

import entitys.Simulation;
import entitys.Simulation.SimulationState;
import entitys.World;
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
		World world = this.simulation.getWorld();
		int generation = 0; 
		do {	
			System.out.println("\nGeneración: " + generation);
			this.showGrid();
			this.simulation.getWorld().updateGrid();
			generation++;
		}
		while (generation < this.simulation.getSimulationCycles());
		
		if (generation >= this.simulation.getSimulationCycles()) {
			this.simulation.setState(SimulationState.FINISHED);
		}
	}
	
	/**
	 * Despliega en la consola el estado almacenado, corresponde
	 * a una generación del Juego de la vida.
	 */
	public void showGrid() {
		byte[][] worldGrid = this.simulation.getWorld().getGrid();
		for (int i = 0; i < worldGrid.length; i++) {
			for (int j = 0; j < worldGrid.length; j++) {		
				System.out.print((worldGrid[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}
	
	
	public SimulationRunView getSimulationRunView() {
		return this.simulationRunView;
	}

	public Simulation getSimulation() {	
		return this.simulation;
	}
	
}
