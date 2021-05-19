package dataAccess.memory;

import java.util.ArrayList;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import dataAccess.memory.IndexSortTemplate;
import models.Identifiable;
import models.User;

public class SimulationsDAO extends IndexSortTemplate implements OperationsDAO {

	private ArrayList<Identifiable> simulationsData;
	
	private static SimulationsDAO instance;
	
	private SimulationsDAO() {		
		this.simulationsData = new ArrayList<Identifiable>();
	}
	
	public static SimulationsDAO getInstance() {
		if (instance == null) {
			instance = new SimulationsDAO();
		}
		return instance;
	}

	@Override
	public Identifiable find(String id) {
		int pos = this.indexSort(this.simulationsData, id);
		if (pos >= 0) {
			return (User) this.simulationsData.get(pos);
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		
		return simulationsData;
	}

	public List<Identifiable> findAllUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void create(Identifiable simulation) throws DataAccessException {
		assert simulation != null;
		int insertionPos = indexSort(this.simulationsData, simulation.getId()); 
		if (insertionPos < 0) {
			this.simulationsData.add(Math.abs(insertionPos)-1, simulation);
			return;
		}
		throw new DataAccessException("simulationsDAO.create: "+ simulation.getId() + " ya existe");	
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifiable delete(Identifiable obj) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifiable update(Identifiable obj) throws DataAccessException {
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
