package userAccess.console.views;

import java.io.Console;

import userAccess.OperationsView;
import userAccess.console.controllers.SimulationRunController;

public class SimulationRunView implements OperationsView {

	private Console console;
	
	public SimulationRunView() {	
		initSimulationRunView();
	}

	private void initSimulationRunView() {
		console = System.console();
	}
	
	/**
	 * Necesita conocer el presentador del que depende.
	 * (Inyección de dependencias)
	 * @param simulationRunControler
	 */
	public void showSimulation(SimulationRunController simulationRunControler) {	
		byte[][] grid = simulationRunControler.getSimulation().getWorld().getGrid();
		
		for (int index_x = 0; index_x < grid.length; index_x++) {
			for (int index_y = 0; index_y < grid.length; index_y++) {	
				this.showMessageSimple((grid[index_x][index_y] == 1) ? "|o" : "| ");
			}
			this.showMessage("|");
		}
	}
	
	private void showMessageSimple(String message) {	
		if (console != null) {
			console.writer().print(message);
			return;
		}		
		// Desde el entorno de Eclipse la consola falla.
		System.out.print(message);
	}
	
	@Override
	public void showMessage(String menssage) {	
		if (console != null) {		
			console.writer().println(menssage);
			return;
		}
		// Desde el entorno de Eclipse la consola falla.
		System.out.println(menssage);
	}

	public void showMessageOK() {
		showMessage("Simulación completada.");
	}

}
