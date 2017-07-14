package tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Connect MySQL database to programm
 * 
 * @author caro
 *
 */
public class DatabaseInitializer {

	public static final String DB = "bankdb";

	public static final String URL = "localhost";

	public static final String USR = "root";
	
	public static final String PW = "";

	/**
	 * Prepare connection to database
	 * 
	 * @return Connection to database
	 * @throws SQLException
	 */
	public static Connection conectToDatabase() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB, USR, PW);
		return connection;

	}

	/**
	 * Create tables in database
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 *             if reading fails
	 * @throws SQLException 
	 */
	public static void createTables(Connection con) throws IOException, SQLException {

		// loesche alle bisher bestehenden Tabellen
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
	 * @throws SQLException
	 */
	public static void deleteTable(Connection con, String tableName) throws SQLException {

		con.createStatement().executeUpdate("DROP TABLE IF EXISTS " + tableName);
	}

	/**
	 * Delete all tables from database.
	 * @param con
	 * @throws SQLException
	 */
	public static void deleteAllTables(Connection con) throws SQLException {

		String[] tables = { "Angestellter_Sparbuch", "Angestellter_Girokonto", "Angestellter_Kreditkarte",
				"Angestellter_Kredit", "Angestellter_Kunde", "Girokonto_Kredit", "Girokonto_Kreditkarte",
				"Filialleiter_Angestellter", "Kunde_Kredit", "Kunde_Kreditkarte", "Kunde_Girokonto", "Kunde_Sparbuch",
				"Kredit", "Kreditkartenkonto", "Girokonto", "Sparbuch", "Kunde", "Filialleiter", "Angestellter" };
	
		for(String table : tables) {
			deleteTable(con, table);
		};
	}
	
}
