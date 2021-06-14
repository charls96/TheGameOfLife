package dataAccess.mySql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entityes.Address;
import entityes.EntitysException;
import entityes.Identifiable;
import entityes.Mail;
import entityes.Nif;
import entityes.Password;
import entityes.Person;
import entityes.User;
import entityes.User.RoleUser;
import jLife.Configuration;
import utils.EasyDate;
import utils.Regex;

public class UsersDAO implements OperationsDAO {

	private Connection db;
	private Statement usersStatement;					// Para la ejecución de consultas SQL.
	private ResultSet usersResulSet;					// Para manejar el resutado de la consulta.
	private DefaultTableModel usersTableModel; 			// Tabla-modelo del resultado de la consulta.
	private ArrayList<Identifiable> usersBuffer; 		// Versión en forma de objetos del TableModel.

	// Singleton
	private static UsersDAO instance = null;

	public static UsersDAO getInstance() {
		if (instance == null) {
			instance = new UsersDAO();
		}
		return instance;
	}

	private UsersDAO() {
		try {
			initUsersDAO();
			if (find(Configuration.get().getProperty("user.adminNif")) == null 
					|| find(Configuration.get().getProperty("nif.default")) == null) {
				createIntegratedUsers();
			}
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa el DAO, detecta si existen las tablas de datos capturando la
	 * excepción SQLException.
	 */
	private void initUsersDAO() {
		this.db = DataConnection.getDB();
		try {
			this.createTableUsers();
			this.usersStatement = this.db.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.usersTableModel = new DefaultTableModel();
		this.usersBuffer = new ArrayList<Identifiable>(); 
	}

	private void createTableUsers() throws SQLException {
		Statement s = db.createStatement();
		s.executeUpdate("CREATE TABLE IF NOT EXISTS users (" 
				+ "nif VARCHAR(9) PRIMARY KEY,"
				+ "mail VARCHAR(45) NOT NULL,"
				+ "name VARCHAR(45) NOT NULL," 
				+ "surnames VARCHAR(45) NOT NULL," 
				+ "street VARCHAR(45) NOT NULL," 
				+ "number VARCHAR(5) NOT NULL," 
				+ "postalCode VARCHAR(5) NOT NULL," 
				+ "location VARCHAR(45) NOT NULL,"  
				+ "birthDate DATE," 
				+ "registeredDate DATE," 
				+ "password VARCHAR(16) NOT NULL," 	
				+ "role VARCHAR(20) NOT NULL," 
				+ "UNIQUE (mail)"
				+ ");"
				);
	}

	private void createIntegratedUsers() {
		this.create(new User(new Nif(Configuration.get().getProperty("nif.default")),
				Configuration.get().getProperty("user.guest"),
				Configuration.get().getProperty("user.guest") + " " + Configuration.get().getProperty("user.guest"),
				new Address("La Iglesia", "0", "30012", "Patiño"),
				new Mail("guest@gmail.com"),
				new EasyDate(2000, 1, 14),
				new EasyDate(2021, 1, 14),
				new Password(Configuration.get().getProperty("password.default")), 
				RoleUser.REGISTERED
				));
		this.create(new User(new Nif(Configuration.get().getProperty("user.adminNif")),
				Configuration.get().getProperty("user.admin"),
				Configuration.get().getProperty("user.admin") + " " + Configuration.get().getProperty("user.admin"),
				new Address("La Iglesia", "0", "30012", "Patiño"),
				new Mail("admin@gmail.com"),
				new EasyDate(2000, 1, 14),
				new EasyDate(2021, 1, 14),
				new Password(Configuration.get().getProperty("user.adminPassword")), 
				RoleUser.REGISTERED
				));
	}

	// Operaciones DAO

	@Override
	public User find(String userId) {		
		assert userId != null;

		queryRun(userId);

		// Establece columnas y etiquetas.
		setColumnTableModel();

		// Borrado previo de filas.
		deleteRowTableModel();

		// Volcado desde el resulSet.
		synchronizeTableModel();

		// Actualiza buffer de objetos.
		updateUsersBuffer();

		if (usersBuffer.size() > 0) {
			return (User) usersBuffer.get(0);
		}
		return null;
	}

	private void queryRun(String userId) {
		String id = "";
		if (userId.matches(Regex.NIF)) {
			id = "nif";
		}
		if (userId.matches(Regex.MAIL)) {
			id = "mail";
		}	
		try {
			String queryText = "SELECT * FROM jlife.users WHERE " + id + " = '" + userId.toUpperCase() + "'";
			this.usersResulSet = this.usersStatement.executeQuery(queryText);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setColumnTableModel() {
		try {
			// Obtiene metadatos.
			ResultSetMetaData metaData = this.usersResulSet.getMetaData();

			// Número total de columnas.
			int numCol = metaData.getColumnCount();

			// Etiqueta de cada columna.
			Object[] etiquetas = new Object[numCol];
			for (int i = 0; i < numCol; i++) {
				etiquetas[i] = metaData.getColumnLabel(i + 1);
			}

			// Incorpora array de etiquetas en el TableModel.
			((DefaultTableModel) this.usersTableModel).setColumnIdentifiers(etiquetas);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteRowTableModel() {
		while (this.usersTableModel.getRowCount() > 0)
			((DefaultTableModel) this.usersTableModel).removeRow(0);
	}

	private void synchronizeTableModel() {
		Object[] rowData = new Object[this.usersTableModel.getColumnCount()];
		// Para cada fila en el ResultSet de la consulta.
		try {
			while (usersResulSet.next()) {
				// Se replica y añade la fila en el TableModel.
				for (int i = 0; i < this.usersTableModel.getColumnCount(); i++) {
					rowData[i] = this.usersResulSet.getObject(i + 1);
				}
				((DefaultTableModel) this.usersTableModel).addRow(rowData);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateUsersBuffer() {
		this.usersBuffer.clear();
		for (int i = 0; i < this.usersTableModel.getRowCount(); i++) {
			try {
				Nif nif = new Nif(((String) usersTableModel.getValueAt(i, 0)));
				Mail mail = new Mail(((String) usersTableModel.getValueAt(i, 1)).toLowerCase());
				String name = (String) usersTableModel.getValueAt(i, 2);
				String surnames = (String) usersTableModel.getValueAt(i, 3);
				Address address = new Address((String) usersTableModel.getValueAt(i, 4), 
						(String) usersTableModel.getValueAt(i, 5),
						(String) usersTableModel.getValueAt(i, 6),
						(String) usersTableModel.getValueAt(i, 7));
				EasyDate birthDate = new EasyDate((java.sql.Date) usersTableModel.getValueAt(i, 8));
				EasyDate registeredDate = new EasyDate((java.sql.Date) usersTableModel.getValueAt(i, 9));
				Password password = new Password();
				password.setEncriptedText((String)usersTableModel.getValueAt(i, 10));
				RoleUser role = null;
				switch ((String) usersTableModel.getValueAt(i, 11)) {
				case "GUEST":  
					role = RoleUser.GUEST;
					break;
				case "REGISTERED":
					role = RoleUser.REGISTERED;
					break;
				case "ADMIN":
					role = RoleUser.ADMIN;
					break;
				}	
				// Genera y guarda objeto
				usersBuffer.add(new User(nif, name, surnames, address, mail, 
						birthDate, registeredDate, password, role));
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
			this.usersResulSet = usersStatement.executeQuery("SELECT * FROM jlife.users");

			// Establece columnas y etiquetas
			this.setColumnTableModel();

			// Borrado previo de filas
			this.deleteRowTableModel();

			// Volcado desde el resulSet
			this.synchronizeTableModel();

			// Actualiza buffer de objetos.
			this.updateUsersBuffer();	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<Identifiable>) this.usersBuffer;
	}

	@Override
	public void create(Identifiable user) throws DataAccessException {	
		assert user != null;
		if (find(user.getId()) == null) {
			try {
				store(user);
				return;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}  		
		}
		throw new DataAccessException("create:" + user.getId() +" Ya existe.");
	}

	private void store(Identifiable user) throws SQLException {
		assert user != null;
		// Se realiza la consulta y los resultados quedan en el ResultSet actualizable.
		this.usersResulSet = this.usersStatement.executeQuery("SELECT * FROM jlife.users");
		this.usersResulSet.moveToInsertRow();
		this.usersResulSet.updateString("nif", ((User) user).getNif().getText());
		this.usersResulSet.updateString("mail", ((User) user).getMail().getText().toUpperCase());
		this.usersResulSet.updateString("name", ((User) user).getName());
		this.usersResulSet.updateString("surnames", ((User) user).getSurnames());
		this.usersResulSet.updateString("street", ((User) user).getAddress().getStreet());
		this.usersResulSet.updateString("number", ((User) user).getAddress().getNumber());
		this.usersResulSet.updateString("postalCode", ((User) user).getAddress().getPostalCode());
		this.usersResulSet.updateString("location", ((User) user).getAddress().getLocation());
		this.usersResulSet.updateDate("birthDate", new java.sql.Date(((User) user).getBirthDate().timeStampSQL()));
		this.usersResulSet.updateDate("registeredDate", new java.sql.Date(((User) user).getRegisteredDate().timeStampSQL()));
		this.usersResulSet.updateString("password", ((User) user).getPassword().getText());
		this.usersResulSet.updateString("role", ((User) user).getRole().toString());
		this.usersResulSet.insertRow();
		this.usersResulSet.beforeFirst();	
	}

	@Override
	public User delete(Identifiable user) throws DataAccessException {
		return this.delete(user.getId());
	}

	@Override
	public User delete(String userId) throws DataAccessException  {
		assert userId != null;

		User user = find(userId);
		if (user != null) {
			try {
				String queryText = "DELETE FROM jlife.users WHERE nif = '" + user.getId() + "'";			
				this.usersStatement.executeQuery(queryText);
				this.usersBuffer.remove(user);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
		throw new DataAccessException("delete: " + userId + " no existe...");
	}

	@Override
	public User update(Identifiable user) throws DataAccessException {
		assert user != null;
		User previousUser = find(user.getId());
		if (previousUser != null) {
			try {
				this.usersBuffer.remove(previousUser);
				this.usersBuffer.add(user);
				this.usersStatement.executeQuery("UPDATE jlife.users SET "
					//	+ " nif = " + ((User) user).getNif() + ","
						+ " mail = " + ((User) user).getMail().getText() + ","
						+ " name = " + ((User) user).getName() + ","
						+ " surnames = " + ((User) user).getSurnames() + ","
						+ " street = " + ((User) user).getAddress().getStreet() + ","
						+ " number = " + ((User) user).getAddress().getNumber() + ","
						+ " postalCode = " + ((User) user).getAddress().getPostalCode() + ","
						+ " localtion = " + ((User) user).getAddress().getLocation() + ","
						+ " birthDate = " + new java.sql.Date(((Person) user).getBirthDate().timeStampSQL()) + ","
						+ " registeredDate = " + new java.sql.Date(((User) user).getRegisteredDate().timeStampSQL()) + ","
						+ " password = " + ((User) user).getPassword().getText() + ","
						+ " role = " + ((User) user).getRole().toString() 
						+ " WHERE nif = '" + previousUser.getId() + "';"
						);
				return previousUser;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		throw new DataAccessException("Update: "+ user.getId() + " no existe.");
	}

	@Override
	public String toStringData() {
		return findAll().toString();
	}

	@Override
	public void deleteAll() {
		usersBuffer.clear();
		try {
			this.usersStatement.executeQuery("DELETE * FROM jlife.users");
			createIntegratedUsers();
		} 
		catch (SQLException | DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toStringIds() {
		try {
			this.usersResulSet = usersStatement.executeQuery("SELECT nif FROM jlife.user");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.setColumnTableModel();
		this.deleteRowTableModel();
		this.synchronizeTableModel();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < usersTableModel.getRowCount(); i++) {
			result.append((String) usersTableModel.getValueAt(i, 0));
		}
		return result.toString();
	}

	public void close() {
		try {
			usersStatement.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

} 
