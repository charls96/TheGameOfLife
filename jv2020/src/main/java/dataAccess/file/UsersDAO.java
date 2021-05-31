package dataAccess.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import dataAccess.memory.IndexSortTemplate;
import entitys.Address;
import entitys.Identifiable;
import entitys.Mail;
import entitys.ModelsException;
import entitys.Nif;
import entitys.Password;
import entitys.User;
import entitys.User.RoleUser;
import jLife.Configuration;
import utils.EasyDate;

public class UsersDAO extends IndexSortTemplate implements OperationsDAO, Persistent {

	private List<Identifiable> usersData;
	private Map <String,String> idEquivalences;

	private File userDataFile;
	private File idEquivalencesDataFile;

	// Singleton. 
	private static UsersDAO instance;

	public static UsersDAO getInstance() {	
		if (instance == null) {		
			instance = new UsersDAO();
		}	
		return instance;
	}

	private UsersDAO()  {
		this.usersData = new ArrayList <Identifiable>();
		this.idEquivalences = new Hashtable <String, String>();
		new File(Configuration.get().getProperty("data.directoryName")).mkdirs();

		this.userDataFile = new File("." + File.separator
				+ Configuration.get().getProperty("data.directoryName")
				+ File.separator
				+ Configuration.get().getProperty("data.fileUsersName")
				);

		this.idEquivalencesDataFile = new File("." + File.separator
				+ Configuration.get().getProperty("data.directoryName")
				+ File.separator
				+ Configuration.get().getProperty("data.FileIdEquivalencesName")
				);
		dataLoad();
	}

	// OPERACIONES DE PERSISTENCIA

	@Override
	public void dataLoad() {	
		try {	
			FileInputStream fisUsers = new FileInputStream(userDataFile);
			ObjectInputStream oisUsers = new ObjectInputStream(fisUsers);
			this.usersData = (ArrayList<Identifiable>) oisUsers.readObject();
			oisUsers.close();

			FileInputStream fisIdEquivalences = new FileInputStream(idEquivalencesDataFile);
			ObjectInputStream oisIdEquivalences = new ObjectInputStream(fisIdEquivalences);
			this.idEquivalences = (Map<String, String>) oisIdEquivalences.readObject();
			oisIdEquivalences.close();
			return;	
		} 
		catch (ClassNotFoundException | IOException e) {	
			this.loadIntegratedUsers();
			this.dataStore();
		}
	}

	private void loadIntegratedUsers() {		
		try {
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
		} 
		catch (ModelsException e) {
			e.printStackTrace();
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dataStore() {	
		try {		
			FileOutputStream fosUsers = new FileOutputStream(userDataFile);
			ObjectOutputStream oosUsers = new ObjectOutputStream(fosUsers);
			oosUsers.writeObject(usersData);
			oosUsers.flush();
			oosUsers.close();

			FileOutputStream fosIdEquivalences = new FileOutputStream(idEquivalencesDataFile);
			ObjectOutputStream oosIdEquivalences = new ObjectOutputStream(fosIdEquivalences);
			oosIdEquivalences.writeObject(idEquivalences);
			oosIdEquivalences.flush();
			oosIdEquivalences.close();	
		} 
		catch (IOException e) {	
			e.printStackTrace();
		}
	}

	// OPERACIONES DAO

	@Override
	public Identifiable find(String id) {
		id = this.idEquivalences.get(id);
		if (id != null) {
			int index = this.indexSort(this.usersData, id);
			if (index > 0) {
				return (User) this.usersData.get(index - 1);
			}
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		return this.usersData;
	}

	@Override
	public void create(Identifiable user) throws DataAccessException {	
		assert user != null;
		int index = this.indexSort(this.usersData, user.getId());	
		if (index < 0) {		
			this.usersData.add(-(index + 1), user);
			this.idEquivalences.put(((User) user).getNif().getText(), user.getId());
			this.idEquivalences.put(((User) user).getMail().getText(), user.getId());
			this.dataStore();
			return;
		}
		throw new DataAccessException("UsersDAO.create:" + user.getId() +" Ya existe.");
	}

	@Override
	public Identifiable delete(Identifiable user) throws DataAccessException {
		return this.delete(user.getId());
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		assert (id != null);
		
		int position = indexSort(usersData, id);
		
		if(position > 0) {
			User deltedUser = (User) usersData.remove(position-1);
			idEquivalences.remove(deltedUser.getId());
			idEquivalences.remove(deltedUser.getNif().getText());
			idEquivalences.remove(deltedUser.getMail().getText());
			return deltedUser;
		}
		else {
			throw new DataAccessException("UsersDAO.delete: el usuario " + id + " no existe...");
		}	
	}

	@Override
	public Identifiable update(Identifiable user) throws DataAccessException {
		assert user != null;
		int updatedIndex = indexSort(this.usersData, user.getId());
		if (updatedIndex > 0) {
			User userOld = (User) this.usersData.get(updatedIndex - 1);
			this.usersData.set(updatedIndex - 1, user);
			this.idEquivalences.remove(userOld.getNif().getText());
			this.idEquivalences.remove(userOld.getMail().getText());
			this.idEquivalences.put(((User) user).getNif().getText(), user.getId());
			this.idEquivalences.put(((User) user).getMail().getText(), user.getId());
			this.dataStore();
			return userOld;
		}
		throw new DataAccessException("UsersDAO.update: el usuario no existe...");
	}

	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable user: usersData) {
			textData.append("\n" + user); 
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder result = new StringBuilder();
		for (Identifiable user: this.usersData) {
			result.append("\n" + user.getId()); 
		}
		return result.toString();
	}

	@Override
	public void deleteAll() {
		usersData.clear();
		idEquivalences.clear();
		loadIntegratedUsers();	
		}
}