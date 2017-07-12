package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import tools.DatabaseInitializer;
import tools.TestdataFiller;

/**
 * Main of the program
 * @author caro
 *
 */
public class SimulationStarter {
	
	@SuppressWarnings("static-access")
	public static void main (String [] args) throws ParseException {
		
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");
		
		DatabaseInitializer di = new DatabaseInitializer();
		TestdataFiller tdb = new TestdataFiller();
		Connection con = null;
		try {
			con = di.conectToDatabase();
			di.createTables(con);
			//di.fuelleTabellenMitCSV();
			tdb.insertAngestellten(con);
			tdb.insertFilialleiter(con);
			tdb.insertKunden(con);
			con.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
