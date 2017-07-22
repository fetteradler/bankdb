package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import tools.ConsoleInterface;
import tools.DatabaseInitializer;
import tools.TestdataFiller;

/**
 * Main of the program.
 * Starts the software.
 * @author caro
 *
 */
public class SimulationStarter {
	
	public static void main (String [] args) throws ParseException {
		
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");

		Connection con = null;
		//MitarbeiterUse mu = new MitarbeiterUse();
		try {
			con = DatabaseInitializer.connectToDatabase();
			DatabaseInitializer.createTables(con);
			TestdataFiller.fillAllTestdata(con);
			ConsoleInterface ci = new ConsoleInterface();
			ci.anmeldung();
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Program beendet");
	}
}
