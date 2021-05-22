package jLife;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {

	// Singleton -variante-
	private static Properties configuration; 		// Hashtable <String, String> con persistencia en fichero.
	private File configurationFile;							

	public static Properties get() {
		if (configuration == null) {
			new Configuration();
		}
		return configuration;
	}

	private Configuration() {
		configuration = new Properties();
		configurationFile = getFilePath();	
		try {
			if (configurationFile.exists()) {
				InputStream is = new FileInputStream(configurationFile);
				configuration.load(is);    			// Carga configuración desde el fichero.
				is.close();
				return;
			}
			loadDefaultConfiguration();   			// La primera ejecución genera fichero.
			saveConfiguration();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	private File getFilePath() {
		new File("data").mkdirs();         			// Crea directorio en la raiz del proyecto.
		return new File("." + File.separator + "data" + File.separator + "jlife2020.cfg");
	}

	private void loadDefaultConfiguration() {
		configuration.put("aplication.title", "JLife 2020");

		configuration.put("world.defaultName", "Demo");
		configuration.put("world.defaultSize", "1");
		configuration.put("world.defaultType", "EDGES");
		configuration.put("simulation.defaultCicles", "35");

		configuration.put("user.admin", "admin");
		configuration.put("user.adminNif", "00000000T");
		configuration.put("user.adminPassword", "Miau#0");
		configuration.put("user.guest", "guest");
		configuration.put("user.minimalAge", "16"); 

		configuration.put("nif.default", "00000001R");
		configuration.put("password.default", "Miau#0");	
		configuration.put("session.maxAttempts", "3");

		configuration.put("data.directoryName", "data");
		configuration.put("data.fileUsersName", "users.dat");
		configuration.put("data.FileIdEquivalencesName", "idEquivalences.dat");
		configuration.put("data.fileSessionsName", "sessions.dat");
		configuration.put("data.fileSimulationsName", "simulations.dat");
		configuration.put("data.fileWorldsdName", "worlds.dat");

		configuration.put("db4o.DBNameFile", "jlife.db4o");
		configuration.put("mySql.url", "jdbc:mysql://172.20.254.134/jlife?");
		configuration.put("mySql.localhost", "jdbc:mysql://localhost/jlife?");
		configuration.put("mySql.user", "admin");
		configuration.put("mySql.passwd", "Miau#000");
	}

	public void saveConfiguration() {
		try {
			OutputStream os = new FileOutputStream(configurationFile);
			configuration.store(os, "Updated configuration.");
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
} 