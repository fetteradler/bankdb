package starter;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import tools.ConsoleInterface;
import tools.DatabaseInitializer;

/**
 * Main of the program. Starts the software.
 * 
 * @author caro
 *
 */
public class SimulationStarter {

	/**
	 * Create new connection con and start 'DatabaseInitializer' to connect to
	 * database. Starts 'ConsoleInterface' so the user can by self coordinate
	 * what to do next.
	 * After the user finished, connection to close.
	 * 
	 * @param args
	 * @throws ParseException
	 *             If the parsing of the entry of 'ConsoleInterface' fails.
	 */
	public static void main(String[] args) throws ParseException {

		// Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");

		Connection con = null;
		// MitarbeiterUse mu = new MitarbeiterUse();
		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("----- Herzlich wilkommen bei Bankdb ----- \n");
			// DatabaseInitializer.createTables(con);
			// TestdataFiller.fillAllTestdata(con);
			ConsoleInterface ci = new ConsoleInterface();
			ci.anmeldung();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Program beendet");
	}
}
