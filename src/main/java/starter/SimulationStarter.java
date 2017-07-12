package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import tools.DatenbankInitialisierer;
import tools.TestDatensatzBefueller;

/**
 * Startet das Programm
 * @author caro
 *
 */
public class SimulationStarter {
	
	@SuppressWarnings("static-access")
	public static void main (String [] args) throws ParseException {
		
		//Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");
		
		DatenbankInitialisierer di = new DatenbankInitialisierer();
		TestDatensatzBefueller tdb = new TestDatensatzBefueller();
		Connection con = null;
		try {
			con = di.verbindZuDatenbank();
			di.erstelleTabellen(con);
			//di.fuelleTabellenMitCSV();
			tdb.einfuegenAngestellten(con);
			tdb.einfuegenFilialleiter(con);
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
