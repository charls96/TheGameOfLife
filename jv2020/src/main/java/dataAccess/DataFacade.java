package dataAccess;

import java.util.List;

import dataAccess.db4o.*;
import entitys.Identifiable;
import entitys.Session;
import entitys.Simulation;
import entitys.User;
import entitys.World;

public class DataFacade {

	private UsersDAO usersDAO; 
	private SessionsDAO sessionsDAO;
	private SimulationsDAO simulationsDAO;
	private WorldsDAO worldsDAO;

	public DataFacade() {
		this.usersDAO = UsersDAO.getInstance();
		this.sessionsDAO = SessionsDAO.getInstance();
		this.simulationsDAO = SimulationsDAO.getInstance();
		this.worldsDAO = WorldsDAO.getInstance();
	}

	public void close( ) {
		this.usersDAO.close();
		this.sessionsDAO.close();
		this.simulationsDAO.close();
		this.worldsDAO.close();	
	}
	
	// FACADE usersDAO

	public User findUser(String id) {
		return (User) this.usersDAO.find(id);
	}

	public List<Identifiable> findAllUsers() {
		return this.usersDAO.findAll();
	}

	public void altaUser(User user) throws DataAccessException  {
		this.usersDAO.create(user);
	}

	public User deleteUser(String id) throws DataAccessException {
		deleteSessionsUser(id);
		deleteSimulationsUser(id);
		return (User) this.usersDAO.delete(id);
	}
	
	private List<Identifiable> deleteSimulationsUser(String userId) {
		List<Identifiable> deletedSessiones = this.sessionsDAO.findAllUser(userId);
		if (deletedSessiones != null) {
			for (Identifiable sesion : deletedSessiones) {
				this.sessionsDAO.delete(sesion.getId());
			}
		}
		return deletedSessiones;
	}
	
	private List<Identifiable> deleteSessionsUser(String userId) {
		List<Identifiable> deletedSimulations = this.simulationsDAO.findAllUser(userId);
		if (deletedSimulations != null) {
			for (Identifiable simulation : deletedSimulations) {
				this.simulationsDAO.delete(simulation.getId());
			}	
		}
		return deletedSimulations;
	}

	public User deleteUser(User user) throws DataAccessException {
		assert user != null;
		return deleteUser(user.getId());
	}

	public User updateUser(User user) throws DataAccessException  {
		assert user != null;
		return (User) this.usersDAO.update(user);
	}

	public String toStringDataUsers() {
		return this.usersDAO.toStringData();
	}

	public String toStringIdsUsers() {
		return this.usersDAO.toStringIds();
	}

	public void deleteAllUsers() {
		this.usersDAO.deleteAll();
		this.sessionsDAO.deleteAll();
		this.simulationsDAO.deleteAll();
	}

	// FACADE SessionsDAO

	public Session findSession(String id) {
		return (Session) this.sessionsDAO.find(id);
	}
	
	public List<Identifiable> findAllSessions() {
		return this.sessionsDAO.findAll();
	}

	public List<Identifiable> findAllSessionsUser(String userId) {
		return this.sessionsDAO.findAllUser(userId);
	}

	public void createSession(Session session) throws DataAccessException  {
		assert session != null;
		this.sessionsDAO.create(session);
	}

	public Session deleteSession(String id) throws DataAccessException  {
		return (Session) this.sessionsDAO.delete(id);
	}

	public Session deleteSession(Session session) throws DataAccessException  {
		assert session != null;
		return (Session) this.sessionsDAO.delete(session.getId());
	}

	public Session updateSession(Session session) throws DataAccessException  {
		assert session != null;
		return (Session) this.sessionsDAO.update(session);
	}

	public String toStringDataSessions() {
		return this.sessionsDAO.toStringData();
	}

	public String toStringIdsSessions() {
		return this.sessionsDAO.toStringIds();
	}

	public void deleteAllSessions() {
		this.sessionsDAO.deleteAll();
	}

	// FACADE SimulationsDAO

	public Simulation findSimulation(String id) {
		return (Simulation) this.simulationsDAO.find(id);
	}

	public List<Identifiable> findAllSimulations() {
		return this.simulationsDAO.findAll();
	}
	
	public List<Identifiable> findAllSimulationsUser(String userId) {
		return this.simulationsDAO.findAllUser(userId);
	}

	public void createSimulation(Simulation simulation) throws DataAccessException  {
		assert simulation != null;
		this.simulationsDAO.create(simulation);
	}

	public Simulation deleteSimulation(String id) throws DataAccessException  {
		return (Simulation) this.simulationsDAO.delete(id);
	}

	public Simulation deleteSimulation(Simulation simulation) throws DataAccessException  {
		assert simulation != null;
		return (Simulation) this.simulationsDAO.delete(simulation.getId());
	}

	public Simulation updateSimulation(Simulation simulation) throws DataAccessException  {
		assert simulation != null;
		return (Simulation) this.simulationsDAO.update(simulation);
	}

	public String toStringDataSimulations() {
		return this.simulationsDAO.toStringData();
	}

	public String toStringIdsSimulations() {
		return this.simulationsDAO.toStringIds();
	}

	public void deleteAllSimulations() {
		this.simulationsDAO.deleteAll();
	}

	// FACADE WorldsDAO

	public World findWorld(String id) {
		return (World) this.worldsDAO.find(id);
	}

	public List<Identifiable> findAllWorlds() {
		return this.worldsDAO.findAll();
	}

	public void createWorld(World world) throws DataAccessException  {
		assert world != null;
		this.worldsDAO.create(world);
	}

	public World deleteWorld(String id) throws DataAccessException  {
		return (World) this.worldsDAO.delete(id);
	}

	public World deleteWorld(World world) throws DataAccessException  {
		assert world != null;
		return (World) this.worldsDAO.delete(world.getId());
	}

	public World updateWorld(World world) throws DataAccessException   {
		assert world != null;
		return (World) this.worldsDAO.update(world);
	}

	public String toStringDataWorlds() {
		return this.worldsDAO.toStringData();
	}

	public String toStringIdsWorlds() {
		return this.worldsDAO.toStringIds();
	}

	public void deleteAllWorlds() {
		this.worldsDAO.deleteAll();
	}

} 