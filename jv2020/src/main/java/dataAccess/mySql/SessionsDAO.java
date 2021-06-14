package dataAccess.mySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.db4o.User;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import dataAccess.memory.UsersDAO;
import entityes.Address;
import entityes.EntitysException;
import entityes.Identifiable;
import entityes.Mail;
import entityes.Nif;
import entityes.Password;
import entityes.Person;
import entityes.Session;
import entityes.Session.StateSession;
import jLife.Configuration;
import utils.EasyDate;
import utils.Regex;

public class SessionsDAO implements OperationsDAO {

	private Connection db;
	private Statement sessionsStatement;					// Para la ejecución de consultas SQL.
	private ResultSet sessionsResulSet;						// Para manejar el resutado de la consulta.
	private DefaultTableModel sessionsTableModel; 			// Tabla-modelo del resultado de la consulta.
	private ArrayList<Identifiable> sessionsBuffer; 		// Versión en forma de objetos del TableModel.

	
	// Singleton
	private static SessionsDAO instance = null;

	public static SessionsDAO getInstance() {
		if (instance == null) {
			instance = new SessionsDAO();
		}
		return instance;
	}

	private SessionsDAO() {
		try {
			initSessionsDAO();
			if (find(Configuration.get().getProperty("session.adminUser")) == null 
					|| find(Configuration.get().getProperty("session.default")) == null) {
				createIntegratedSessions();
			}
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void initSessionsDAO() {
		this.db = DataConnection.getDB();
		try {
			this.createTableSessions();
			this.sessionsStatement = this.db.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.sessionsTableModel = new DefaultTableModel();
		this.sessionsBuffer = new ArrayList<Identifiable>(); 
	}
	
	
	
	private void createTableSessions() throws SQLException {
		Statement s = db.createStatement();
		s.executeUpdate("CREATE TABLE IF NOT EXISTS sessions ("
				+ "user VARCHAR(14) PRIMARY KEY,"
				+ "startTime DATETIME NOT NULL,"
				+ "endTime DATETIME NOT NULL," 
				+ "State VARCHAR(14) NOT NULL," 
				+ ");"
				);
	}

	
	
	private void createIntegratedSessions() {
		
	}
	
	

	@Override
	public Session find(String sessionId) {		
		assert sessionId != null;

		queryRun(sessionId);

		// Establece columnas y etiquetas.
		setColumnTableModel();

		// Borrado previo de filas.
		deleteRowTableModel();

		// Volcado desde el resulSet.
		synchronizeTableModel();

		// Actualiza buffer de objetos.
		updateSessionsBuffer();

		if (sessionsBuffer.size() > 0) {
			return (Session) sessionsBuffer.get(0);
		}
		return null;
	}

	

	private void queryRun(String sessionId) {
		String id = "";
		if (sessionId.matches(Regex.NIF)) {
			id = "nif";
		}
		if (sessionId.matches(Regex.MAIL)) {
			id = "mail";
		}	
		try {
			String queryText = "SELECT * FROM jlife.sessions WHERE " + id + " = '" + sessionId.toUpperCase() + "'";
			this.sessionsResulSet = this.sessionsStatement.executeQuery(queryText);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	private void setColumnTableModel() {
		try {
			// Obtiene metadatos.
			ResultSetMetaData metaData = this.sessionsResulSet.getMetaData();

			// Número total de columnas.
			int numCol = metaData.getColumnCount();

			// Etiqueta de cada columna.
			Object[] etiquetas = new Object[numCol];
			for (int i = 0; i < numCol; i++) {
				etiquetas[i] = metaData.getColumnLabel(i + 1);
			}

			// Incorpora array de etiquetas en el TableModel.
			((DefaultTableModel) this.sessionsTableModel).setColumnIdentifiers(etiquetas);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	private void deleteRowTableModel() {
		while (this.sessionsTableModel.getRowCount() > 0)
			((DefaultTableModel) this.sessionsTableModel).removeRow(0);
	}
	
	
	
	private void synchronizeTableModel() {
		Object[] rowData = new Object[this.sessionsTableModel.getColumnCount()];
		// Para cada fila en el ResultSet de la consulta.
		try {
			while (sessionsResulSet.next()) {
				// Se replica y añade la fila en el TableModel.
				for (int i = 0; i < this.sessionsTableModel.getColumnCount(); i++) {
					rowData[i] = this.sessionsResulSet.getObject(i + 1);
				}
				((DefaultTableModel) this.sessionsTableModel).addRow(rowData);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void updateSessionsBuffer() {
		this.sessionsBuffer.clear();
		for (int i = 0; i < this.sessionsTableModel.getRowCount(); i++) {
			try {
				Nif nif = new Nif(((String) sessionsTableModel.getValueAt(i, 0)));
				Mail mail = new Mail(((String) sessionsTableModel.getValueAt(i, 1)).toLowerCase());
				EasyDate startTime = new EasyDate(((String) sessionsTableModel.getValueAt(i, 2)));
				EasyDate endTime = new EasyDate(((String) sessionsTableModel.getValueAt(i, 3)));
				StateSession state = null;
				switch ((String) sessionsTableModel.getValueAt(i, 11)) {
				case "ACTIVE":  
					state = StateSession.ACTIVE;
					break;
				case "CLOSED":
					state = StateSession.CLOSED;
					break;
				}	
				// Genera y guarda objeto
				sessionsBuffer.add(new Session());
			} 
			catch (EntitysException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	public List<Identifiable> findAll() {
		try {
			// Se realiza la consulta y los resultados quedan en el ResultSet
			this.sessionsResulSet = sessionsStatement.executeQuery("SELECT * FROM jlife.sessions");

			// Establece columnas y etiquetas
			this.setColumnTableModel();

			// Borrado previo de filas
			this.deleteRowTableModel();

			// Volcado desde el resulSet
			this.synchronizeTableModel();

			// Actualiza buffer de objetos.
			this.updateSessionsBuffer();	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<Identifiable>) this.sessionsBuffer;
	}
	
	
	
	@Override
	public void create(Identifiable session) throws DataAccessException {	
		assert session != null;
		if (find(session.getId()) == null) {
			try {
				store(session);
				return;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}  		
		}
		throw new DataAccessException("create:" + session.getId() +" Ya existe.");
	}
	
	
	private void store(Identifiable session) throws SQLException {
		assert session != null;
		// Se realiza la consulta y los resultados quedan en el ResultSet actualizable.
		this.sessionsResulSet = this.sessionsStatement.executeQuery("SELECT * FROM jlife.sessions");
		this.sessionsResulSet.moveToInsertRow();
		this.sessionsResulSet.updateString(((Session) session).getUser().toString(), "user");
		this.sessionsResulSet.updateString(((Session) session).getStartTime().toString(), "startTime");
		this.sessionsResulSet.updateString(((Session) session).getEndTime().toString(), "endTime");
		this.sessionsResulSet.updateString(((Session) session).getState().toString(), "state");
		this.sessionsResulSet.beforeFirst();	
	}
	
	
	
	@Override
	public Session delete(Identifiable session) throws DataAccessException {
		return this.delete(session.getId());
	}
	
	
	
	@Override
	public Session delete(String sessionId) throws DataAccessException  {
		assert sessionId != null;

		Session session = find(sessionId);
		if (session != null) {
			try {
				String queryText = "DELETE FROM jlife.sessions WHERE nif = '" + session.getId() + "'";			
				this.sessionsStatement.executeQuery(queryText);
				this.sessionsBuffer.remove(session);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return session;
		}
		throw new DataAccessException("delete: " + sessionId + " no existe...");
	}
	
	
	
	@Override
	public Session update(Identifiable session) throws DataAccessException {
		assert session != null;
		Session previousSession = find(session.getId());
		if (previousSession != null) {
			try {
				this.sessionsBuffer.remove(previousSession);
				this.sessionsBuffer.add(session);
				this.sessionsStatement.executeQuery("UPDATE jlife.sessions SET "
						+ " user = " + ((Session) session).getUser() + ","
						+ " startTime = " + ((Session) session).getStartTime() + ","
						+ " endTime = " + ((Session) session).getEndTime() + ","
						+ " state = " + ((Session) session).getState() + ","
						);
				return previousSession;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		throw new DataAccessException("Update: "+ session.getId() + " no existe.");
	}
	
	
	@Override
	public String toStringData() {
		return findAll().toString();
	}
	
	
	
	@Override
	public void deleteAll() {
		sessionsBuffer.clear();
		try {
			this.sessionsStatement.executeQuery("DELETE * FROM jlife.sessions");
			createIntegratedSessions();
		} 
		catch (SQLException | DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public String toStringIds() {
		try {
			this.sessionsResulSet = sessionsStatement.executeQuery("SELECT nif FROM jlife.sessions");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.setColumnTableModel();
		this.deleteRowTableModel();
		this.synchronizeTableModel();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < sessionsTableModel.getRowCount(); i++) {
			result.append((String) sessionsTableModel.getValueAt(i, 0));
		}
		return result.toString();
	}
	
	
	
	public void close() {
		try {
			sessionsStatement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
} 
