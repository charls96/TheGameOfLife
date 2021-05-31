package dataAccess.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entitys.Identifiable;
import entitys.User;
import entitys.World;
import jLife.Configuration;

public class WorldsDAO implements OperationsDAO {

	private ObjectContainer db;

	//Singleton
	private static WorldsDAO instance;

	public static WorldsDAO getInstance() {
		if(instance == null) {
			instance = new WorldsDAO();
		}
		return instance;
	}

	private WorldsDAO() {
		this.db = DataContainer.getDB();
		if (find(Configuration.get().getProperty("world.defaultName")) == null) {
			this.createDemoWorld();
		}
	}

	private void createDemoWorld() {
		try {	
			this.create(new World());
			return;
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	//OPERACIONES DAO

	public Identifiable find(Identifiable world) {
		return this.find(world.getId());
	}

	@Override
	public Identifiable find(String id) {
		ObjectSet<World> result = db.query(new Predicate<World>() {
			public boolean match(World world) {
				return true;
			}
		});
		while (result.hasNext()) {
			World world = (World) result.next();
			if (world.getId().equals(id)) {
				return world;
			}
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		Query query = db.query();
		query.constrain(World.class);
		return query.execute();
	}	

	public List<Identifiable> findAllUser(String userId) {
		Query query = db.query();
		query.constrain(World.class);
		query.descend("user").descend("getId()").constrain(userId).equal();
		return query.execute();
	}

	@Override
	public void create(Identifiable world) throws DataAccessException {
		assert world != null;
		if (find(world.getId()) == null) {
			this.db.store(world);  		
			return;
		} 
		throw new DataAccessException(world.getId() + ", Ya existe.");
	}

	@Override
	public Identifiable delete(Identifiable world) throws DataAccessException {
		return this.delete(world.getId());
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		World deletedWorld = (World) this.find(id);
		if (deletedWorld != null) {
			this.db.delete(deletedWorld);
			return deletedWorld;
		}
		throw new DataAccessException(id + ", no existe.");
	}

	@Override
	public Identifiable update(Identifiable world) throws DataAccessException {
		assert world != null;
		World oldWorld = (World) find(world.getId());
		if (oldWorld != null) {
			try {
				oldWorld.setName(((World) world).getName());
				oldWorld.setGridType(((World) world).getGridType());
				oldWorld.setDistribution(((World) world).getDistribution());
				oldWorld.setConstants(((World) world).getConstants());
				this.db.store(oldWorld);
				return oldWorld;
			} 
			catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		throw new DataAccessException(world.getId() + ", no existe.");
	}

	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable world : findAll()) {
			textData.append("\n" + world);
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable world : findAll()) {
			if (world != null) {
				textData.append("\n" + world.getId());
			}
		}
		return textData.toString();
	}

	@Override
	public void deleteAll() {
		for (Identifiable world : findAll()) {
			this.db.delete(world);
		}
		this.createDemoWorld();
	}

	public void close() {
		this.db.close();	
	}

}