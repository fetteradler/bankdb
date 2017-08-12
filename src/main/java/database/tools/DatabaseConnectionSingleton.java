package database.tools;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Creates only one connetion to database.
 * 
 * @author CM
 *
 */
public final class DatabaseConnectionSingleton {

	/**
	 * private class variable of type of the own class.
	 */
	private static DatabaseConnectionSingleton instance;
	/**
	 * private connection of the database.
	 */
	private Connection dbConnection;

	/**
	 * Private constructor to prevent that another method can create object.
	 * 
	 * @throws SQLException
	 *             Error while accessing database.
	 */
	private DatabaseConnectionSingleton() throws SQLException {

		dbConnection = DatabaseInitializer.connectToDatabase();
	}

	/**
	 * Creates only one object of 'DatabaseConnectionSingleton'.
	 * 
	 * @return Singleton object of database connection.
	 * @throws SQLException
	 *             Error while accessing database.
	 */
	public static DatabaseConnectionSingleton getInstance() throws SQLException {

		if (instance == null) {
			instance = new DatabaseConnectionSingleton();
		}
		return instance;
	}

	/**
	 * Getter of dbConnection.
	 * 
	 * @return Connection of the database.
	 */
	public Connection getDbConnection() {
		return dbConnection;
	}
}
