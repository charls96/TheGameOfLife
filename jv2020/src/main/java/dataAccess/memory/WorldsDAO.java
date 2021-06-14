package dataAccess.memory;

import java.util.ArrayList;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entitys.Identifiable;
import entitys.World;

public class WorldsDAO extends IndexSortTemplate implements OperationsDAO {

	private ArrayList<Identifiable> worldsData;
	
	private static WorldsDAO instance;
	
	private WorldsDAO() {		
		this.worldsData = new ArrayList<Identifiable>();
	}
	
	public static WorldsDAO getInstance() {
		if (instance == null) {
			instance = new WorldsDAO();
		}
		return instance;
	}
	
	@Override
	public Identifiable find(String id) {
		int position = this.indexSort(this.worldsData, id);
		if (position >= 0) {
			return (World) this.worldsData.get(position);
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Identifiable world) throws DataAccessException {
		assert world != null;
		int insertionPos = indexSort(this.worldsData, world.getId()); 
		if (insertionPos < 0) {
			this.worldsData.add(Math.abs(insertionPos)-1, world);
			return;
		}
		throw new DataAccessException("worldsData.create: "+ world.getId() + " ya existe");	
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		assert id != null;
		int insertionPos = indexSort(this.worldsData, id); 
		
		if (insertionPos > 0) {
			return (World) worldsData.remove(insertionPos -1);
		}
		
		throw new DataAccessException("worldsData.delete: "+ id + " no existe");	
	}

	@Override
	public Identifiable delete(Identifiable world) throws DataAccessException {
		return delete(world.getId());
	}

	@Override
	public Identifiable update(Identifiable world) throws DataAccessException {
		assert world != null;
		World updatedWorld = (World) world;
		int insertionPos = indexSort(this.worldsData, updatedWorld.getId());
		if (insertionPos > 0) {
			worldsData.set(insertionPos-1, updatedWorld);
		}
		else {
			throw new DataAccessException("worldsData.update: "+ updatedWorld.getId() + " no existe");
		}
		return updatedWorld;
	}

	@Override
    public String toStringData() {
        StringBuilder dataString = new StringBuilder();
        for (Identifiable world: worldsData) {
            dataString.append("\n" + world); 
        }
        return dataString.toString();
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
