package tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Bindet MySQL Datenbank in das Programm ein
 * 
 * @author caro
 *
 */
public class DatenbankInitialisierer {

	public static final String DB = "bankdb";

	public static final String URL = "localhost";

	public static final String USR = "root";
	
	public static final String PW = "";

	/**
	 * Bereitet Verbindung zu einer MySQL Datenbank vor
	 * 
	 * @return gibt Verbindung zur Datenbank zurueck
	 * @throws SQLException
	 */
	public static Connection verbindZuDatenbank() throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB, USR, PW);
		return connection;

	}

	/**
	 * Erstellt die Tabellen der Datenbank
	 * 
	 * @param con
	 *            Connection zur Datenbank
	 * @throws IOException
	 *             wird geworfen, falls es Fehler bei dem einlesen gibt
	 * @throws SQLException 
	 */
	public static void erstelleTabellen(Connection con) throws IOException, SQLException {

		// loesche alle bisher bestehenden Tabellen
		loescheAlleTabellen(con);

		File f = new File("lib" + File.separator + "database" + File.separator + "datenbanktabellen.sql");
		ArrayList<String> list = SQLTabellenParser.liesSQLFile(f);
		
		for (String s : list) {
			con.createStatement().executeUpdate(s);
		}
	}

	/**
	 * Loescht eine Tabelle der Datenabank
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public static void loescheTabelle(Connection con, String tabellenName) throws SQLException {

		con.createStatement().executeUpdate("DROP TABLE IF EXISTS " + tabellenName);
	}

	public static void loescheAlleTabellen(Connection con) throws SQLException {

		String[] tabellen = { "Angestellter_Sparbuch", "Angestellter_Girokonto", "Angestellter_Kreditkarte",
				"Angestellter_Kredit", "Angestellter_Kunde", "Girokonto_Kredit", "Girokonto_Kreditkarte",
				"Filialleiter_Angestellter", "Kunde_Kredit", "Kunde_Kreditkarte", "Kunde_Girokonto", "Kunde_Sparbuch",
				"Kredit", "Kreditkartenkonto", "Girokonto", "Sparbuch", "Kunde", "Filialleiter", "Angestellter" };
	
		for(String tabelle : tabellen) {
			loescheTabelle(con, tabelle);
		};
	}
	
//	/**
//	 * Fuellt Datenbanke mit Daten aus CSV Dokumenten
//	 * @throws IOException 
//	 */
//	public static void fuelleTabellenMitCSV() throws IOException {
//		
//		String csvSplit = ":";
//		File csvFileMitarbeiter = new File(
//				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Mitarbeiter.csv");
//		File csvFileKunden = new File(
//				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden.csv");
//		
//		//String[] mitarbeiter = SQLTabellenParser.liesCSVDaten(csvFileMitarbeiter, csvSplit);
//		//String[] kunden = SQLTabellenParser.liesCSVDaten(csvFileKunden, csvSplit);
//		
//		//System.out.println("mitarbeiter: " + mitarbeiter[0] + " " + mitarbeiter[15]);
//	}
}
