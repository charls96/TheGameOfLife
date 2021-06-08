package dataAccess.mySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jLife.Configuration;

public class DataConnection {

	
	private String url;
	private String user;
	private String password;
	
	// Singleton
	private static Connection db;
	
	public static Connection getDB() {
		if (db == null) 
			new DataConnection();
		return db;
	}
	
	private DataConnection() {
		//this.url = Configuration.get().getProperty("mySql.url");
		this.url = Configuration.get().getProperty("mySql.localhost");
		this.user = Configuration.get().getProperty("mySql.user");
		this.password = Configuration.get().getProperty("mySql.passwd");
		initDataConnection();
	}
	
	private void initDataConnection()  {
		try {		
			// Activa el driver.
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			// Establece la conexión con la BD utilizando el driver.
			String zonaHoraria = "useTimezone=true&serverTimezone=UTC";
			db = DriverManager.getConnection(this.url+zonaHoraria, this.user, this.password);
		} 
		catch (SQLException  e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnetion() {
		if (this.db != null) {
			try {
				this.db.close();
			} 
			catch (SQLException e) { 
			}
		}
	}
	
}
