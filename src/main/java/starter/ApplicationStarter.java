package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import database.tools.DatabaseConnectionSingleton;
import database.tools.DatabaseInitializer;
import gui.LoginInterface;
import tools.AuthenticationCookie;
import tools.TestdataFiller;
import tools.UserInputReader;

/**
 * Main of the program. Starts the software.
 * 
 * @author caro
 *
 */
public class ApplicationStarter {

	/**
	 * Create new connection con and start 'DatabaseInitializer' to connect to
	 * database. Starts 'ConsoleInterface' so the user can by self coordinate
	 * what to do next. After the user finished, connection to close.
	 * 
	 * @param args
	 * @throws ParseException
	 *             If the parsing of the entry of 'ConsoleInterface' fails.
	 */
	public static void main(String[] args) {

		String myEnv = System.getenv("DELETE_DB_ON_STARTUP");

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		// Setze Datenbank auf Testzustand falls gewünscht.
		if (myEnv.equals("TRUE")) {
			System.out.println("Erstelle Datenbank...");
			try {
				DatabaseInitializer.createTables(con);
			} catch (IOException e) {
				System.err.println("DB Schema-Dateien konnte nicht gelesen werden!");
				System.exit(1);
			} catch (SQLException e) {
				System.err.println("Die Datenbanktabellen konnte nicht erstellt werden!");
				System.exit(1);
			}

			try {
				TestdataFiller.fillAllTestdata(con);
			} catch (SQLException | ParseException | IOException e) {
				System.err.println("Einfügen der Testdaten ist fehlgeschlagen!");
				System.exit(1);
			}
		}

		AuthenticationCookie cookie = LoginInterface.anmeldung();

		
		// TODO
		boolean wantToExit = false;

		while (!wantToExit) {
			// Überprüfe ob Cookie noch gültig.
			// wantToExit = ci.openMenuForCertainRole(auth);
		}

		try {
			con.close();
			UserInputReader.closeInputRead();
		} catch (SQLException e) {
			System.err.println("Datenbankverbindung konnte nicht geschlossen werden!");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("BufferedReader konnte nicht geschlossen werden!");
			System.exit(1);
		}

		System.out.println("Das Programm wurde erfolgreich beendet");
	}
}
