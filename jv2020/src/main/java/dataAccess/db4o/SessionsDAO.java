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
import entitys.User;

public class SessionsDAO implements OperationsDAO {

	private ObjectContainer db;
	
	// Singleton.
	private static SessionsDAO instance;

	public static SessionsDAO getInstance() {
		if (instance == null) {
			instance = new SessionsDAO();
		}
		return instance;
	}

	private SessionsDAO() {
		this.db = DataContainer.getDB();
	}

	//OPERACIONES DAO

	@Override
	public Identifiable find(String id) {
		ObjectSet<Session> result = db.query(new Predicate<Session>() {
		    public boolean match(Session session) {
		        return true;
		    }
		});
		while (result.hasNext()) {
		    Session session = (Session) result.next();
		    if (session.getId().equals(id)) {
		    	return session;
		    }
		}
		return null;
	}

	public Identifiable find(Identifiable session) {
		assert session != null;
		return this.find(session.getId());
	}	

	@Override
	public List<Identifiable> findAll() {
		Query query = this.db.query();
		query.constrain(Session.class);
		return query.execute();
	}

	public List<Identifiable> findAllUser(String userId) {
		Query query = db.query();
		query.constrain(Session.class);
		query.descend("user").descend("getId()").equals(userId);
		return query.execute();
	}

	@Override
	public void create(Identifiable session) throws DataAccessException {
		assert session != null;
		if (this.find(session.getId()) == null) {
			this.db.store(session);  		
			return;
		}
		throw new DataAccessException(session.getId() + ", Ya existe.");
	}

	@Override
	public Identifiable delete(Identifiable session) throws DataAccessException {
		assert session != null;
		return this.delete(session.getId());
	}
	
	@Override
	public Identifiable delete(String id) throws DataAccessException {
		Session deletedSession = (Session) find(id);
		if (deletedSession != null) {
			this.db.delete(deletedSession);
			return deletedSession;
		}
		throw new DataAccessException(id + " no existe.");
	}

	@Override
	public Identifiable update(Identifiable session) throws DataAccessException {
		assert session != null;
		Session oldSesion = (Session) find(session.getId());
		if (oldSesion != null) {
			oldSesion.setUser(((Session) session).getUser());
			oldSesion.setEndTime(((Session) session).getEndTime());
			this.db.store(oldSesion);
			return oldSesion;
		}
		throw new DataAccessException(session.getId() + " no existe.");
	}
	
	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable session : this.findAll()) {
				textData.append("\n" + session);
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable session : this.findAll()) {
				textData.append("\n" + session.getId());
		}
		return textData.toString();
	}

	@Override
	public void deleteAll() {
		for (Identifiable session : this.findAll()) {
			this.db.delete(session);
		}	
	}

	public void close() {
		this.db.close();	
	}

} 
