package dataAccess.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entitys.Identifiable;
import entitys.Session;
import entitys.Simulation;

public class SimulationsDAO implements OperationsDAO {

	private ObjectContainer db;
	
	// Singleton.
	private static SimulationsDAO instance;

	public static SimulationsDAO getInstance() {
		if (instance == null) {
			instance = new SimulationsDAO();
		}
		return instance;
	}

	private SimulationsDAO() {
		this.db = DataContainer.getDB();
	}

	//OPERACIONES DAO

	@Override
	public Identifiable find(String id) {
		ObjectSet<Simulation> result = db.query(new Predicate<Simulation>() {
		    public boolean match(Simulation simulation) {
		        return true;
		    }
		});
		while (result.hasNext()) {
			Simulation simulation = (Simulation) result.next();
		    if (simulation.getId().equals(id)) {
		    	return simulation;
		    }
		}
		return null;
	}

	public Identifiable find(Identifiable simulation) {
		assert simulation != null;
		return this.find(simulation.getId());
	}	

	@Override
	public List<Identifiable> findAll() {
		Query query = this.db.query();
		query.constrain(Simulation.class);
		return query.execute();
	}

	public List<Identifiable> findAllUser(String userId) {
		Query query = db.query();
		query.constrain(Simulation.class);
		query.descend("user").descend("getId()").constrain(userId);
		return query.execute();
	}

	@Override
	public void create(Identifiable simulation) throws DataAccessException {
		assert simulation != null;
		if (this.find(simulation.getId()) == null) {
			this.db.store(simulation);  		
			return;
		}
		throw new DataAccessException(simulation.getId() + ", Ya existe.");
	}

	@Override
	public Identifiable delete(Identifiable simulation) throws DataAccessException {
		assert simulation != null;
		return this.delete(simulation.getId());
	}
	
	@Override
	public Identifiable delete(String id) throws DataAccessException {
		Simulation deletedSimulation = (Simulation) find(id);
		if (deletedSimulation != null) {
			this.db.delete(deletedSimulation);
			return deletedSimulation;
		}
		throw new DataAccessException(id + " no existe.");
	}

	@Override
	public Identifiable update(Identifiable simulation) throws DataAccessException {
		assert simulation != null;
		Simulation oldSimulation = (Simulation) find(simulation.getId());
		if (oldSimulation != null) {
			oldSimulation.setUser(((Simulation) simulation).getUser());
			oldSimulation.setWorld(((Simulation) simulation).getWorld());
			this.db.store(oldSimulation);
			return oldSimulation;
		}
		throw new DataAccessException(simulation.getId() + " no existe.");
	}
	
	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable simulation : this.findAll()) {
				textData.append("\n" + simulation);
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable simulation : this.findAll()) {
				textData.append("\n" + simulation.getId());
		}
		return textData.toString();
	}

	@Override
	public void deleteAll() {
		for (Identifiable simulation : this.findAll()) {
			this.db.delete(simulation);
		}	
	}

	public void close() {
		this.db.close();	
	}

} 
