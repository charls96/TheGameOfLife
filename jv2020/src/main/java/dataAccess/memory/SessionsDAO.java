package dataAccess.memory;

import java.util.ArrayList;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entityes.Identifiable;
import entityes.User;

public class SessionsDAO extends IndexSortTemplate implements OperationsDAO {

	private ArrayList<Identifiable> sessionsData;
	
	private static SessionsDAO instance;
	
	private SessionsDAO() {		
		this.sessionsData = new ArrayList<Identifiable>();
	}
	
	public static SessionsDAO getInstance() {
		if (instance == null) {
			instance = new SessionsDAO();
		}
		return instance;
	}
	
	private List<Identifiable> sessionsUserFilter(int lastIndex) {	
	
		String userId = ((Session) this.sessionsData.get(lastIndex)).getUser().getId();
		int firstIndex = lastIndex;

		for (int i = lastIndex; i >= 0 && ((Session) this.sessionsData.get(i)).getUser().getId().equals(userId); i--) {
			firstIndex = i;
		}

		return this.sessionsData.subList(firstIndex, lastIndex + 1);
	}
	
	public List<Identifiable> findAllUser(String userId) {	
	
		String LastSessionId = userId + ":" + new EasyDate().toStringTimeStamp();
		int index = indexSort(this.sessionsData, LastSessionId);
		
		if (index > 0) {
			return sessionsUserFilter(indexSort(this.sessionsData, LastSessionId) - 1);
		}	
		
		return null;
		
	}

	
	@Override
	public Identifiable find(String id) {
		int pos = this.indexSort(this.sessionsData, id);
		if (pos >= 0) {
			return (User) this.sessionsData.get(pos);
		}
		return null;
	}

	public List<Identifiable> findAllUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Identifiable> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Identifiable session) {
		assert session != null;
		int insertionPos = indexSort(this.sessionsData, session.getId()); 
		if (insertionPos < 0) {
			this.sessionsData.add(Math.abs(insertionPos)-1, session);
			return;
		}
		throw new DataAccessException("SessionsDAO.create: "+ session.getId() + " ya existe");
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
	public Identifiable update(Identifiable session) throws DataAccessException {
		assert session !=null;
		int updatedIndex = indexSort(this.sessionsData, session.getId());
		
		if (updatedIndex > 0) {
			Session oldSession = (Session) this.sessionsData.get(updatedIndex - 1);
			this.sessionsData.set(updatedIndex - 1, session);
			this.dataStore();
			
			return oldSession;
		}
		
		throw new DataAccessException("SessionsDao.update: La simulación no existe...");
	}

	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable session: this.findAll()) {
			textData.append("\n" + session); 
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable session: this.findAll()) {
			textData.append("\n" + session.getId()); 
		}
		return textData.toString();
	}


	@Override
	public void deleteAll() {
		sessionsData.clear();
		
	}


}
