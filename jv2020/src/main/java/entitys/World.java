package entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World implements Identifiable, Serializable {

	public enum GridType {EDGES, CYCLIC, UNLIMITED};

	private String name;
	private byte[][] grid;
	private List<Coordinate> distribution;
	private GridType gridType;

	//Reglas simulación
	private Map<String,int[]> constants;

	public World() {
		this("Demo", distributionDemo());	
	}
	
	public World(String name, List<Coordinate> distribution) {
		this.setName(name);
		this.setDistribution(distribution);
		this.genetateGrid();
		this.applyStandardLaws();
		this.gridType = GridType.EDGES;
	}

	public World(World world) {
		this.name = world.name;
		this.grid =	cloneGrid(world.grid);
		this.genetateGrid();
		this.constants = cloneConstans(world);
		this.gridType = world.gridType;
	}
	
	private void genetateGrid() {
		assert distribution != null;
		Coordinate MaxCoordinate = getMaxCoordinate();
		grid = new byte[MaxCoordinate.getY()+10][MaxCoordinate.getX()+10];
		
		for (Coordinate coordinate : distribution) {
			grid[coordinate.getY()][coordinate.getX()] = 1;
		}
	}
	
	private Coordinate getMaxCoordinate() {
		Coordinate MaxCoordinate = new Coordinate(0, 0);
		for (Coordinate coordinate : distribution) {
			if (MaxCoordinate.compareTo(coordinate) < 0) {
				MaxCoordinate = coordinate;
			}
		}
		return MaxCoordinate;
	}
	
	private void applyStandardLaws() {
		this.constants = new HashMap<String,int[]>();
		this.constants.put("constantSurvive",new int[] {2, 3});
		this.constants.put("constantReborn",new int[] {3});
	}

	private HashMap<String,int[]> cloneConstans(World world) {
		HashMap<String,int[]> constants = new HashMap<String,int[]>();
		this.constants.put("constantSurvive", Arrays.copyOf(world.constants.get("constantSurvive"), 
				world.constants.get("constantSurvive").length));
		this.constants.put("constantReborn", Arrays.copyOf(world.constants.get("constantSurvive"), 
				world.constants.get("constantReborn").length));
		return constants;
	}

	private byte[][] cloneGrid(byte[][] grid) {
		byte[][] clon = new byte[grid.length][];
		for (int i = 0; i < grid.length; i++) {
			clon[i] = Arrays.copyOf(grid[i], grid[i].length);
		}
		return clon;
	}
	
	public String getName() {
		return name;
	}

	public byte[][] getGrid() {
		return this.grid;
	}
	
	public List<Coordinate> getDistribution() {
		return distribution;
	}

	public GridType getGridType() {
		return this.gridType;
	}
	
	public Map<String,int[]> getConstants() {
		return this.constants;
	}
	
	public void setName(String name) {
		assert name != null;
		this.name = name;
	}
	
	public void setDistribution(List<Coordinate> distribution) {
		this.distribution = distribution;
	}
	
	public void setGridType(GridType gridType) {
		assert gridType != null;
		this.gridType = gridType;
	}

	public void setConstants(Map<String, int[]> constants) {
		assert constants != null;
		this.constants = constants;	
	}

	@Override
	public String getId() {
		return this.name;
	}
	
	public int run(int simulationCicles) {
		int generation = 0; 
		do {
			System.out.println("\nGeneración: " + generation);
			this.showGrid();
			this.updateGrid();
			generation++;
		}
		while (generation < simulationCicles);
		return generation;
	}

	/**
	 * Distribución demo, como lista de celdas a 1. 
	 */
	static private List<Coordinate> distributionDemo() {
		List<Coordinate> distributionDemo = new ArrayList<Coordinate>();
		distributionDemo.add(new Coordinate(2,5));
		distributionDemo.add(new Coordinate(3,4));
		distributionDemo.add(new Coordinate(3,6));
		distributionDemo.add(new Coordinate(4,7));
		distributionDemo.add(new Coordinate(4,12));
		distributionDemo.add(new Coordinate(4,13));
		distributionDemo.add(new Coordinate(4,14));
		distributionDemo.add(new Coordinate(5,5));
		distributionDemo.add(new Coordinate(5,6));
		distributionDemo.add(new Coordinate(5,7));
		distributionDemo.add(new Coordinate(8,9));
		distributionDemo.add(new Coordinate(8,10));
		distributionDemo.add(new Coordinate(8,11));
		distributionDemo.add(new Coordinate(9,9));
		distributionDemo.add(new Coordinate(9,11));
		distributionDemo.add(new Coordinate(10,9));
		distributionDemo.add(new Coordinate(10,10));
		distributionDemo.add(new Coordinate(10,11));
		distributionDemo.add(new Coordinate(11,5));
		distributionDemo.add(new Coordinate(11,6));
		distributionDemo.add(new Coordinate(12,5));
		distributionDemo.add(new Coordinate(12,6));
		return distributionDemo;
	}
	

	/**
	 * Despliega en la consola el estado almacenado, corresponde
	 * a una generación del Juego de la vida.
	 */
	public void showGrid() {

		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {		
				System.out.print((grid[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}

	/**
	 * Actualiza el estado del Juego de la Vida.
	 * Actualiza según la configuración establecida para la forma del espacio.
	 */
	private void updateGrid() {
		if (gridType == GridType.EDGES) {
			this.updateGridEdges();
		}
		if (gridType == GridType.CYCLIC) {
			this.updateGridCyclic();
		}
	}

	/**
	 * Actualiza el estado almacenado del Juego de la Vida.
	 * Las celdas periféricas son adyacentes con las del lado opuesto.
	 * El mundo representado sería esférico cerrado sin límites para células de dos dimensiones.
	 */
	private void updateGridCyclic()  {     					
		// TO-DO pendiente de implementar.
	}

	/**
	 * Actualiza el estado de cada celda almacenada del Juego de la Vida.
	 * Las celdas de los bordes son los límites absolutos.
	 * El mundo representado sería plano, cerrado y con límites para células de dos dimensiones.
	 */
	private void updateGridEdges()  {     					
		byte[][] newGrid = new byte[this.grid.length][this.grid.length];

		for (int i = 0; i < this.grid.length; i++) {		
			for (int j = 0; j < this.grid.length; j++) {
				int neighborsCount = 0;							
				neighborsCount += this.obtainNorthwestCell(i, j);		
				neighborsCount += this.obtainNorthCell(i, j);			// 		NO | N | NE
				neighborsCount += this.obtainNortheastCell(i, j);		//    	-----------
				neighborsCount += this.obtainEastCell(i, j);			// 		 O | * | E
				neighborsCount += this.obtainSoutheastCell(i, j);		// 	  	----------- 
				neighborsCount += this.obtainSouthCell(i, j); 			// 		SO | S | SE
				neighborsCount += this.obtainSouthwestCell(i, j); 	  
				neighborsCount += this.obtaintWestCell(i, j);		          			                                     	

				updateCell(newGrid, i, j, neighborsCount);
			}
		}
		this.grid = newGrid;
	}

	/**
	 * Aplica las leyes del mundo a la celda indicada dada la cantidad de células adyacentes vivas.
	 * @param newGrid
	 * @param row
	 * @param column
	 * @param neighborsCount
	 */
	private void updateCell(byte[][] newGrid, int row, int column, int neighborsCount) {	

		for (int value : this.constants.get("constantReborn")) {
			if (neighborsCount == value) {									// Pasa a estar viva.
				newGrid[row][column] = 1;
				return;
			}
		}

		for (int value : this.constants.get("constantSurvive")) {
			if (neighborsCount == value && grid[row][column] == 1) {		// Permanece viva, si lo estaba.
				newGrid[row][column] = 1;
				return;
			}
		}
	}

	private byte obtaintWestCell(int row, int column) {
		if (column-1 >= 0) {
			return this.grid[row][column-1]; 		// Celda O.
		}
		return 0;
	}

	private byte obtainSouthwestCell(int row, int column) {
		if (row+1 < this.grid.length && column-1 >= 0) {
			return this.grid[row+1][column-1]; 		// Celda SO.
		}
		return 0;
	}

	private byte obtainSouthCell(int row, int column) {
		if (row+1 < this.grid.length) {
			return this.grid[row+1][column]; 		// Celda S.
		}
		return 0;
	}

	private byte obtainSoutheastCell(int row, int column) {
		if (row+1 < this.grid.length && column+1 < this.grid.length) {
			return this.grid[row+1][column+1]; 		// Celda SE.
		}
		return 0;
	}

	private byte obtainEastCell(int row, int column) {
		if (column+1 < this.grid.length) {
			return this.grid[row][column+1]; 		// Celda E.
		}
		return 0;
	}

	private byte obtainNortheastCell(int row, int column) {
		if (row-1 >= 0 && column+1 < this.grid.length) {
			return this.grid[row-1][column+1]; 		// Celda NE.
		}
		return 0;
	}

	private byte obtainNorthCell(int row, int column) {
		if (row-1 >= 0) {
			return this.grid[row-1][column]; 		// Celda N.
		}
		return 0;
	}

	private byte obtainNorthwestCell(int row, int column) {
		if (row-1 >= 0 && column-1 >= 0) {
			return this.grid[row-1][column-1]; 		// Celda NO.
		}
		return 0;
	}

	
}
