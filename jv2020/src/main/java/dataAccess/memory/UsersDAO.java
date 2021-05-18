package dataAccess.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import jLife.Configuration;
import models.Address;
import models.Identifiable;
import models.Mail;
import models.Nif;
import models.Password;
import models.User;
import models.User.RoleUser;
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

			this.create(new User(new User(new Nif(Configuration.get().getProperty("nif.default")),
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
		int pos = this.indexSort(this.usersData, id);
		if (pos >= 0) {
			return (User) this.usersData.get(pos);
		}
		return null;
	}

	@Override
	public List<Identifiable> findAll() {
		// TODO Auto-generated method stub
		return null;
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
