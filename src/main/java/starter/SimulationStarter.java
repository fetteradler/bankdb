package starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import application.MitarbeiterAllow;
import tools.ConsoleInterface;
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
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");
		
		DatabaseInitializer di = new DatabaseInitializer();
		TestdataFiller tdb = new TestdataFiller();
		Connection con = null;
		MitarbeiterAllow ma = new MitarbeiterAllow();
		try {
			con = di.conectToDatabase();
			di.createTables(con);
			ConsoleInterface ci = new ConsoleInterface();
			ci.anmeldung();
			tdb.insertAngestellten(con);
			tdb.insertFilialleiter(con);
			tdb.insertKunden(con);
			tdb.insertGirokonto(con);
			tdb.insertSparbuch(con);
			tdb.insertKreditkarte(con);
			tdb.insertKredit(con);
			tdb.insertKundeSparbuch(con);
			tdb.insertKundeGirokonto(con);
			tdb.insertKundeKreditkarte(con);
			tdb.insertKundeKredit(con);
			tdb.insertAngestellterKunde(con);
			System.out.println("Bewillige Kredit: kundeId, betrag, zinsen, raten, laufzeit(bsp.: 21.07.2019)");
			String kundeId = scanner.next();
			String betrag = scanner.next();
			String zinsen = scanner.next();
			String raten = scanner.next();
			String laufzeit = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(betrag);
			int input3 = Integer.parseInt(zinsen);
			int input4 = Integer.parseInt(raten);
			ma.allowKredit(con, input1, input2, input3, input4, laufzeit);
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
