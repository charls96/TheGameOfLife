package dataAccess;

import java.util.List;

import entitys.Identifiable;

public interface OperationsDAO {

	public Identifiable find(String id);
	public List<Identifiable> findAll();
	public void create(Identifiable obj) throws DataAccessException;
	public Identifiable delete(String id) throws DataAccessException;
	public Identifiable delete(Identifiable obj) throws DataAccessException;
	public Identifiable update(Identifiable obj) throws DataAccessException;
	public String toStringData();
	public String toStringIds();
	public void deleteAll();
	
}
