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
import entitys.World;
import jLife.Configuration;

public class WorldsDAO extends IndexSortTemplate implements OperationsDAO, Persistent {

	private ArrayList<Identifiable> worldsData;
	private File worldsDataFile;

	// Singleton.
	private static WorldsDAO instance;
	
	public static WorldsDAO getInstance() {
		if (instance == null) {	
			instance = new WorldsDAO();
		}
		return instance;
	}

	private WorldsDAO() {	
		this.worldsData = new ArrayList <Identifiable>();	
		new File(Configuration.get().getProperty("data.directoryName")).mkdirs();
		this.worldsDataFile = new File("." + File.separator
								 + Configuration.get().getProperty("data.directoryName")
								 + File.separator
								 + Configuration.get().getProperty("data.fileWorldsdName")
							);
		dataLoad();	
	}

	// OPERACIONES DE PERSISTENCIA
	
	@Override
	public void dataLoad() {
		try {
			FileInputStream fisWorld = new FileInputStream(worldsDataFile);
			ObjectInputStream oisWorld = new ObjectInputStream(fisWorld);
			worldsData = (ArrayList<Identifiable>) oisWorld.readObject();
			oisWorld.close();
			return;
		} 
		catch (ClassNotFoundException | IOException e) {
			this.loadWorldDemo();
			this.dataStore();
		}
	}
	
	private void loadWorldDemo() {
		this.create(new World());
	}
	
	@Override
	public void dataStore() {	
		try {	
			FileOutputStream fosWorld = new FileOutputStream(worldsDataFile);
			ObjectOutputStream oosWorld = new ObjectOutputStream(fosWorld);
			oosWorld.writeObject(worldsData);
			oosWorld.flush();
			oosWorld.close();	
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//OPERACIONES DAO
	
	@Override
	public World find(String id) {	
		int index = indexSort(worldsData, id);			// En base 1	
		if (index >= 0) {	
			return (World) worldsData.get(index - 1);   // En base 0
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		return worldsData;
	}

	@Override
	public void create(Identifiable world) throws DataAccessException  {	
		assert world != null;
		int index = indexSort(this.worldsData, world.getId()); 
		if (index < 0) {
			this.worldsData.add(Math.abs(index)-1, world);
			this.dataStore();
			return;
		}
		throw new DataAccessException("WorldsDAO.create: "+ world.getId() + " ya existe");
	}

	@Override
	public Identifiable delete(Identifiable world) throws DataAccessException  {
		return this.delete(world.getId());
	}
	
	@Override
	public Identifiable delete(String id) throws DataAccessException  {		
		int index = indexSort(this.worldsData, id); 							// En base 1
		if (index > 0) {	
			World  erasedWorld = (World) this.worldsData.remove(index - 1); 	// En base 0
			this.dataStore();
			return erasedWorld;
		} 
		else {
			throw new DataAccessException("WorldsDAO.delete: "+ id + " no existe");
		}	
	}

	@Override
	public Identifiable update(Identifiable world) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;	
	}

	@Override
	public String toStringData() {	
		StringBuilder result = new StringBuilder();
		for (Identifiable world: this.worldsData) {
			result.append("\n" + world);
		}
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