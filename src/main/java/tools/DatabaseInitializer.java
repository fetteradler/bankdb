package tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Connect MySQL database to programm. Dokumentation muss viel ausführlicher werden! Der Satz spiegelt lediglich deine erste
 * Funktion in dieser Klasse wieder.
 * 
 * @author caro
 *
 */
public abstract class DatabaseInitializer {

	private static final String DB = "bankdb";
	private static final String URL = "localhost"; // wird nicht verwendet?
	private static final String USR = "root";
	private static final String PW = "";

	/**
	 * Prepare (!? Establish?) connection to database
	 * 
	 * @return Connection to database
	 * @throws SQLException Dokumentation!
	 */
	public static Connection connectToDatabase() throws SQLException {

		return DriverManager.getConnection("jdbc:mysql://" + URL + ":3306/" + DB, USR, PW);
	}

	/**
	 * Create tables in database. Was für Tabellen? / Woher kommt das Schema?
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 *             if reading fails
	 * @throws SQLException Dokumentation!
	 */
	public static void createTables(Connection con) throws IOException, SQLException {

		// loesche alle bisher bestehenden Tabellen - Wieso hier deutsch!?
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
	 * @param con Dokumentation!
	 * @throws SQLException Dokumentation!
	 */
	private static void deleteTable(Connection con, String tableName) throws SQLException {

		con.createStatement().executeUpdate("DROP TABLE IF EXISTS " + tableName);
	}

	/**
	 * Delete all tables from database.
	 * @param con Dokumentation!
	 * @throws SQLException Dokumentation!
	 */
	private static void deleteAllTables(Connection con) throws SQLException {

		// Unschön, da du im Quellcode Aussagen über die Struktur deiner DB triffst aber gleichzeitig der Funktion
		// den Namen deleteALLLLLLLTables. Versuche ein allgemeines SQL Statement zu schreiben, dass sich anschaut, welche Tabellen
		// in der DB existieren und diese löscht.
		String[] tables = { "Angestellter_Sparbuch", "Angestellter_Girokonto", "Angestellter_Kreditkarte",
				"Angestellter_Kredit", "Angestellter_Kunde", "Girokonto_Kredit", "Girokonto_Kreditkarte",
				"Filialleiter_Angestellter", "Kunde_Kredit", "Kunde_Kreditkarte", "Kunde_Girokonto", "Kunde_Sparbuch",
				"Kredit", "Kreditkartenkonto", "Girokonto", "Sparbuch", "Kunde", "Filialleiter", "Angestellter" };
	
		for(String table : tables) {
			deleteTable(con, table);
		}
	}
	
}
