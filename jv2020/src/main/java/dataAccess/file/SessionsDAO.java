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
import entityes.Identifiable;
import entityes.Session;
import entityes.Simulation;
import jLife.Configuration;
import utils.EasyDate;

public class SessionsDAO extends IndexSortTemplate implements OperationsDAO, Persistent {

	// Singleton.
	private static SessionsDAO instance;

	private ArrayList<Identifiable> sessionsData;
	private File sessionsDataFile;

	private SessionsDAO() {		
		this.sessionsData = new ArrayList<Identifiable>();	
		new File(Configuration.get().getProperty("data.directoryName")).mkdirs();	
		this.sessionsDataFile = new File("." + File.separator
								 + Configuration.get().getProperty("data.directoryName")
								 + File.separator
								 + Configuration.get().getProperty("data.fileSessionsName")
							);
		dataLoad();
	}

	public static SessionsDAO getInstance() {	
		if (instance == null) {	
			instance = new SessionsDAO();
		}
		return instance;
	}
	
	// OPERACIONES DE PERSISTENCIA
	
	@Override
	public void dataLoad() {
		try {	
			FileInputStream fisSessions = new FileInputStream(sessionsDataFile);
			ObjectInputStream oisSessions = new ObjectInputStream(fisSessions);
			this.sessionsData = (ArrayList<Identifiable>) oisSessions.readObject();
			oisSessions.close();	
			return;		
		} 
		catch (ClassNotFoundException | IOException e) {			
			dataStore();
		}
	}
	
	@Override
	public void dataStore() {	
		try {		
			FileOutputStream fosSessions = new FileOutputStream(sessionsDataFile);
			ObjectOutputStream oosSessions = new ObjectOutputStream(fosSessions);
			oosSessions.writeObject(this.sessionsData);
			oosSessions.flush();
			oosSessions.close();	
		} 
		catch (IOException e) {	
			e.printStackTrace();
		}	
	}

	//OPERACIONES DAO
	
	@Override
	public Identifiable find(String id) {
		int index = indexSort(this.sessionsData, id);			// En base 1
		if (index >= 0) {
			return this.sessionsData.get(index - 1);    		// En base 0
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {	
		return this.sessionsData;
	}

	public List<Identifiable> findAllUser(String userId) {			
		String LastSessionId = userId + ":" + new EasyDate().toStringTimeStamp();
		int index = indexSort(this.sessionsData, LastSessionId);	
		if (index > 0) {
			return sessionsUserFilter(indexSort(this.sessionsData, LastSessionId) - 1);
		}	
		return null;
	}

	private List<Identifiable> sessionsUserFilter(int lastIndex) {	
		String userId = ((Session) this.sessionsData.get(lastIndex)).getUser().getId();
		int firstIndex = lastIndex;
		
		// Localiza ??ndice de la primera sesi??n del mismo usuario.
		for (int i = lastIndex; i >= 0 && ((Session) this.sessionsData.get(i)).getUser().getId().equals(userId); i--) {
			firstIndex = i;
		}
		// devuelve la sublista de sesiones buscadas.
		return this.sessionsData.subList(firstIndex, lastIndex + 1);
	}

	@Override
	public void create(Identifiable session) throws DataAccessException {
		assert session != null;
		int insertionIndex = indexSort(this.sessionsData, session.getId()); 
		if (insertionIndex < 0) {
			this.sessionsData.add(Math.abs(insertionIndex)-1, session);
			this.dataStore();
			return;
		}
		throw new DataAccessException("SessionsDAO.create: "+ session.getId() + " ya existe");	
	}

	@Override
	public Identifiable delete(Identifiable session) throws DataAccessException {
		return this.delete(session.getId());
	}
	
	@Override
	public Identifiable delete(String id) throws DataAccessException  {
		int index = indexSort(this.sessionsData, id); 										// En base 1
		if (index > 0) {	
			Session  oldSession = (Session) this.sessionsData.remove(index - 1); 	// En base 0
			this.dataStore();
			return oldSession;
		} 
		else {
			throw new DataAccessException("SessionsDAO.delete: "+ id + " no existe");
		}			
	}

	@Override
	public Identifiable update(Identifiable session) throws DataAccessException {
		
		assert session !=null;
		int updatedIndex = indexSort(this.sessionsData, session.getId());
		if (updatedIndex >0) {
			Session oldSession = (Session) this.sessionsData.get(updatedIndex - 1);
			this.sessionsData.set(updatedIndex - 1, session);
			this.dataStore();
			return oldSession;
		}
		
		throw new DataAccessException("SessionsDao.update: La simulaci??n no existe...");
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
