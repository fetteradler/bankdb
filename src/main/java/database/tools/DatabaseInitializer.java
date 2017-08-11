package database.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Connect MySQL database to programm. Delete and create tables.
 * 
 * @author caro
 *
 */
public abstract class DatabaseInitializer {

	/**
	 * Establish connection to database
	 * 
	 * @return Connection to database
	 * @throws SQLException
	 *             Error while accessing database.
	 * @throws IOException 
	 */
	public static Connection connectToDatabase() throws SQLException {
		
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("logindata.properties"));
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final String DB = properties.getProperty("DB");
		final String URL = properties.getProperty("URL");
		final String USR = properties.getProperty("USR");
		final String PW = properties.getProperty("PW");

		return DriverManager.getConnection("jdbc:mysql://" + URL + ":3306/" + DB, USR, PW);
	}

	/**
	 * Set up empty database. Create tables in database.
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 *             if reading fails
	 * @throws SQLException Dokumentation!
	 */
	public static void createTables(Connection con) throws IOException, SQLException {

		// deletes all existing tables
		deleteAllTables(con);

		File f = new File("lib" + File.separator + "database" + File.separator + "datenbanktabellen.sql");
		ArrayList<String> list = SQLTableParser.readSQLFile(f);

		for (String s : list) {
			con.createStatement().executeUpdate(s);
		}
	}

	/**
	 * Delete a table from database.
	 * 
	 * @param con
	 *            Connection to database.
	 * @throws SQLException
	 *             if SQL statement is invalid.
	 */
	private static void deleteTable(Connection con, String tableName) throws SQLException {

		con.createStatement().executeUpdate("DROP TABLE IF EXISTS " + tableName);
	}

	/**
	 * Delete all tables from database.
	 * 
	 * @param con
	 *            Connection to database.
	 * @throws SQLException
	 *             If table name is invalid.
	 */

	private static void deleteAllTables(Connection con) throws SQLException {

		Statement stmt1 = con.createStatement();
		stmt1.execute("SET FOREIGN_KEY_CHECKS=0");
		stmt1.close();
		
		ResultSet tableNames = con.getMetaData().getTables(con.getCatalog(), null, null, null);
		
		while(tableNames.next()) {
			deleteTable(con, tableNames.getString(3));
		}
		
		Statement stmt2 = con.createStatement();
		stmt2.execute("SET FOREIGN_KEY_CHECKS=1");
		stmt2.close();
	}

}
