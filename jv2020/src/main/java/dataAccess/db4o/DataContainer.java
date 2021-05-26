package dataAccess.db4o;

import java.io.File;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import jLife.Configuration;

public class DataContainer {
	
	// Singleton
	private static ObjectContainer db;
	
	public static ObjectContainer getDB() {
		if (db == null) {
			new DataContainer();
		}
		return db;
	}
	
	private DataContainer() {
		initDataContainer();
	}

	private void initDataContainer() {
		new File(Configuration.get().getProperty("data.directoryName")).mkdirs();
		final String PATH = "." 
				+ File.separator + Configuration.get().getProperty("data.directoryName") 
				+ File.separator + Configuration.get().getProperty("db4o.DBNameFile");
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		db = Db4oEmbedded.openFile(config, PATH);
	}
	
	public static void closeDataContainer() {
		if (db != null) {
			db.close();
		}
	}

}