package dataAccess.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import dataAccess.memory.IndexSortTemplate;
import entitys.Identifiable;
import entitys.Simulation;
import jLife.Configuration;
import utils.EasyDate;

public class SimulationsDAO extends IndexSortTemplate implements OperationsDAO, Persistent {

	private ArrayList<Identifiable> simulationsData;
	private File simulationsDataFile;

	// Singleton.
	private static SimulationsDAO instance;

	private SimulationsDAO() {	
		this.simulationsData = new ArrayList<Identifiable>();	
		new File(Configuration.get().getProperty("data.directoryName")).mkdirs();	
		this.simulationsDataFile = new File("." + File.separator
								 + Configuration.get().getProperty("data.directoryName")
								 + File.separator
								 + Configuration.get().getProperty("data.fileSimulationsName")
							);
		dataLoad();
	}
	
	public static SimulationsDAO getInstance() {	
		if (instance == null) {	
			instance = new SimulationsDAO();
		}
		return instance;
	}
	
	// OPERACIONES DE PERSISTENCIA
	
	@Override
	public void dataLoad() {
		try {	
			FileInputStream fisSimulations = new FileInputStream(this.simulationsDataFile);
			ObjectInputStream oisSimulations = new ObjectInputStream(fisSimulations);
			this.simulationsData = (ArrayList<Identifiable>) oisSimulations.readObject();
			oisSimulations.close();	
			return;		
		} 
		catch (ClassNotFoundException | IOException e) {			
			dataStore();
		}
	}
	
	@Override
	public void dataStore() {	
		try {		
			FileOutputStream fosSimulations = new FileOutputStream(this.simulationsDataFile);
			ObjectOutputStream oosSimulations = new ObjectOutputStream(fosSimulations);
			oosSimulations.writeObject(this.simulationsData);
			oosSimulations.flush();
			oosSimulations.close();	
		} 
		catch (IOException e) {	
			e.printStackTrace();
		}	
	}

	//OPERACIONES DAO
	
	@Override
	public Identifiable find(String id) {
		int index = indexSort(this.simulationsData, id);		// En base 1
		if (index >= 0) {
			return this.simulationsData.get(index - 1);    		// En base 0
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {	
		return this.simulationsData;
	}

	public List<Identifiable> findAllUser(String userId) {			
		String LastSimulationId = userId + ":" + new EasyDate().toStringTimeStamp();
		int index = indexSort(this.simulationsData, LastSimulationId);	
		if (index > 0) {
			return simulationsUserFilter(indexSort(this.simulationsData, LastSimulationId) - 1);
		}	
		return null;
	}

	private List<Identifiable> simulationsUserFilter(int lastIndex) {	
		String userId = ((Simulation) this.simulationsData.get(lastIndex)).getUser().getId();
		int firstIndex = lastIndex;
		
		// Localiza índice de la primera sesión del mismo usuario.
		for (int i = lastIndex; i >= 0 && ((Simulation) this.simulationsData.get(i)).getUser().getId().equals(userId); i--) {
			firstIndex = i;
		}
		// devuelve la sublista de sesiones buscadas.
		return this.simulationsData.subList(firstIndex, lastIndex + 1);
	}

	@Override
	public void create(Identifiable simulation) throws DataAccessException {
		assert simulation != null;
		int insertionIndex = indexSort(this.simulationsData, simulation.getId()); 
		if (insertionIndex < 0) {
			this.simulationsData.add(Math.abs(insertionIndex)-1, simulation);
			this.dataStore();
			return;
		}
		throw new DataAccessException("SimulationsDAO.create: "+ simulation.getId() + " ya existe");	
	}

	@Override
	public Identifiable delete(Identifiable simulation) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Identifiable delete(String id) throws DataAccessException  {
		// TODO Auto-generated method stub
		return null;				
	}

	@Override
	public Identifiable update(Identifiable simulation) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;		
	}

	@Override
	public String toStringData() {	
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toStringIds() {	
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}
	
} 
