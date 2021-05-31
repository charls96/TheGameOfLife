package dataAccess.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
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

public class UsersDAO extends IndexSortTemplate implements OperationsDAO {

	private ArrayList<Identifiable> usersData;
	private HashMap<String,String> idEquivalence;

	private static UsersDAO instance;

	private UsersDAO() {		
		this.usersData = new ArrayList<Identifiable>();
		this.idEquivalence = new HashMap<String,String>();
		loadIntegratedUsers();
	}

	public static UsersDAO getInstance() {
		if (instance == null) {
			instance = new UsersDAO();
		}
		return instance;
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
	public Identifiable find(String id) {
		id = this.idEquivalence.get(id);
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
		return usersData;
	}

	@Override
	public void create(Identifiable user) throws DataAccessException {
		assert user != null;
		int index = this.indexSort(this.usersData, user.getId());	
		if (index < 0) {		
			this.usersData.add(-(index + 1), user);
			this.idEquivalence.put(((User) user).getNif().getText(), user.getId());
			this.idEquivalence.put(((User) user).getMail().getText(), user.getId());
			return;
		}
		throw new DataAccessException("UsuariosDAO.create:" + user.getId() +" Ya existe.");
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		assert id != null;
		
		User user = (User) find(id);
		if(user != null) {
			this.usersData.remove(this.indexSort(usersData, user.getId()));
			this.idEquivalence.remove(user.getNif().getText());
			this.idEquivalence.remove(user.getMail().getText());
			return user;
		}
		throw new DataAccessException("El usuario"  + id + "introducido no existe");
	}

	@Override
	public Identifiable delete(Identifiable user) throws DataAccessException {
		
		//le pasamos user al m�todo creado anteriormente para eliminar el usuario mediante id
		return delete(user.getId());
	}

	@Override
	public Identifiable update(Identifiable user) throws DataAccessException {
		assert user != null;
		
		User userOld = (User) find(user.getId());
		if(user != null) {
			this.usersData.set(this.indexSort(usersData, user.getId()), user);
			this.idEquivalence.replace(userOld.getNif().getText(), user.getId());
			this.idEquivalence.replace(userOld.getMail().getText(), user.getId());
		}
		return userOld;
		
	}


	@Override
	public String toStringData() {
		StringBuilder dataString = new StringBuilder();
		for (Identifiable user: usersData) {
			dataString.append("\n" + user); 
		}
		return dataString.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder idString = new StringBuilder();
		for (Identifiable user: usersData) {
			idString.append("\n" + user.getId()); 
		}
		return idString.toString();
	}

	@Override
	public void deleteAll() {
		usersData.clear();
		idEquivalence.clear();
		loadIntegratedUsers();

	}


}
