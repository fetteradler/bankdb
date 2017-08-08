package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import tools.ConsoleInterface;
import tools.TestdataFiller;
import tools.database.DatabaseInitializer;

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

		String myEnv = System.getenv("DELETE_DB_ON_STARTUP");
		System.out.println(myEnv);
		
		Connection con = null;
		
		try {
			con = DatabaseInitializer.connectToDatabase();
			if (myEnv.equals("TRUE")) {
				 DatabaseInitializer.createTables(con);
				 TestdataFiller.fillAllTestdata(con);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");

		
		// MitarbeiterUse mu = new MitarbeiterUse();
		try {
			
			System.out.println("----- Herzlich wilkommen bei Bankdb ----- \n");

			ConsoleInterface ci = new ConsoleInterface();
			//Authentifikation auth = ci.anmeldung();
			
			boolean wantToExit = false;
			
			while(!wantToExit) {
				//wantToExit = ci.openMenuForCertainRole(auth);
			}
			
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Programm beendet");
	}
}
