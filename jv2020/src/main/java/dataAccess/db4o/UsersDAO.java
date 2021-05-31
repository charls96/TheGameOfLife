package dataAccess.db4o;

import java.util.HashMap;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import dataAccess.DataAccessException;
import dataAccess.OperationsDAO;
import entitys.Address;
import entitys.Identifiable;
import entitys.Mail;
import entitys.ModelsException;
import entitys.Nif;
import entitys.Password;
import entitys.Session;
import entitys.User;
import entitys.User.RoleUser;
import jLife.Configuration;
import utils.EasyDate;

public class UsersDAO  implements OperationsDAO {

	private ObjectContainer db;

	// Singleton. 
	private static UsersDAO instance;

	public static UsersDAO getInstance() {
		if (instance == null) {
			instance = new UsersDAO();
		}
		return instance;
	}

	private UsersDAO() {
		this.db = DataContainer.getDB();
		this.db.store(new HashMap<String,String>());
		if (this.find(Configuration.get().getProperty("user.adminNif")) == null 
				|| this.find(Configuration.get().getProperty("nif.default")) == null) {
			this.createIntegratedUsers();
		}
	}

	private void createIntegratedUsers() {
		try {
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
		catch (ModelsException | DataAccessException e) {
		}
	}

	//OPERACIONES DAO

	@Override
	public User find(String id) {	
		id = this.getEquivalencesMap().get(id);
		if (id != null) {
			ObjectSet<User> result = this.db.query(new Predicate<User>() {
				public boolean match(User user) {
					return true;
				}
			});
			while (result.hasNext()) {
				User user = (User) result.next();
				if (user.getId().equals(id)) {
					return user;
				}
			}
		}
		return null;		
	}

	private HashMap<String,String> getEquivalencesMap() {
		Query query = db.query();
		query.constrain(HashMap.class);
		ObjectSet <HashMap<String,String>> result = query.execute();
		return result.get(0);	
	}

	@Override
	public List<Identifiable> findAll() {
		Query query = db.query();
		query.constrain(User.class);
		return query.execute();
	}

	@Override
	public void create(Identifiable user) throws DataAccessException {
		assert user != null;
		if (this.find(user.getId()) == null) {
			this.db.store(user);  		
			this.createEquivalencesId((User) user);
			return;
		}
		throw new DataAccessException(user.getId() + ", Ya existe.");
	}

	private void createEquivalencesId(User user) {
		HashMap<String,String> equivalencesMap = this.getEquivalencesMap();
		equivalencesMap.put(user.getNif().getText(), user.getId());
		equivalencesMap.put(user.getMail().getText(), user.getId());
		this.db.store(equivalencesMap);	
	}

	@Override
	public Identifiable delete(Identifiable user) throws DataAccessException {
		assert user != null;
		return this.delete(user.getId());
	}

	@Override
	public Identifiable delete(String id) throws DataAccessException {
		User deltedUser = (User) this.find(id);
		if (deltedUser != null) {
			this.deleteEquivalencesId(deltedUser);
			this.db.delete(deltedUser);
			return deltedUser;
		}
		throw new DataAccessException(id + ", no existe.");
	} 

	private void deleteEquivalencesId(User user) {
		HashMap<String,String> equivalencesMap = this.getEquivalencesMap();
		equivalencesMap.remove(user.getNif().getText());
		equivalencesMap.remove(user.getMail().getText());
		this.db.store(equivalencesMap);	
	}

	@Override
	public Identifiable update(Identifiable user) throws DataAccessException  {
		assert user != null;
		User oldUser = (User) this.find(user.getId());
		if (oldUser != null) {
			try {
				this.updateEquivalenceId(oldUser, user);
				oldUser.setNif(((User) user).getNif());
				oldUser.setName(((User) user).getName());
				oldUser.setSurnames(((User) user).getSurnames());
				oldUser.setAddress(((User) user).getAddress());
				oldUser.setMail(((User) user).getMail());
				oldUser.setBirthDate(((User) user).getBirthDate());
				oldUser.setRegisteredDate(((User) user).getRegisteredDate());
				oldUser.setRole(((User) user).getRole());
				this.db.store(oldUser);
				return oldUser;
			}
			catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		throw new DataAccessException(user.getId() + ", no existe.");
	} 

	private void updateEquivalenceId(Identifiable userOld, Identifiable updatedUser) {
		HashMap<String,String> equivalencesMap = this.getEquivalencesMap();
		equivalencesMap.replace(((User) userOld).getNif().getText(), updatedUser.getId().toUpperCase());
		equivalencesMap.replace(((User) userOld).getMail().getText(), updatedUser.getId().toUpperCase());
		this.db.store(equivalencesMap);
	}

	@Override
	public String toStringData() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable user : this.findAll()) {
			textData.append("\n" + user);
		}
		return textData.toString();
	}

	@Override
	public String toStringIds() {
		StringBuilder textData = new StringBuilder();
		for (Identifiable user : this.findAll()) {
			if (user != null) {
				textData.append("\n" + user.getId());
			}
		}
		return textData.toString();
	}

	@Override
	public void deleteAll() {
		for (Identifiable user : this.findAll()) {
			this.db.delete(user);
		}
		HashMap<String,String> equivalencesMap = this.getEquivalencesMap();
		equivalencesMap.clear();
		this.db.store(equivalencesMap);
		this.createIntegratedUsers();
	}

	public void close() {
		this.db.close();	
	}

} 