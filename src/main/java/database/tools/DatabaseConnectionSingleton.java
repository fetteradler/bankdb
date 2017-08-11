package database.tools;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseConnectionSingleton {

	private static DatabaseConnectionSingleton instance;
	private Connection dbConnection;

	private DatabaseConnectionSingleton() throws SQLException {
		
		dbConnection = DatabaseInitializer.connectToDatabase();
	}

	public static DatabaseConnectionSingleton getInstance() throws SQLException {

		if (instance == null) {
			instance = new DatabaseConnectionSingleton();
		}
		return instance;
	}
	
	public Connection getDbConnection() {
		return dbConnection;
	}
}
