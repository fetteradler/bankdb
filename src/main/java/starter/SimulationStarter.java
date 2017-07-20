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
 * Dokumentation! Aussagekraft.
 * Main of the program
 * @author caro
 *
 */
public class SimulationStarter {

	// Dokumentation, was soll diese Methode machen?
	// Warnings haben in 99% der Fälle einen Sinn. Deine Klasse DatabaseInitializer enthält ausschließlich statische Methoden.
	// Das heißt, du kannst die Klasse abstract machen (es können also keine Instanzen gebildet werden) und die Methoden direkt
	// über den Klassennamen DatabaseInitializer aufrufen. s. Verbesserungen unten.
	public static void main (String [] args) throws ParseException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Erstelle Datenbank...");

		// Wenn eine Klasse keine eigenen Attribute enthält, macht es selten Sinn, eine Instanz davon zu bilden und nicht auch diese
		// abstract zu machen.
		Connection con;
		MitarbeiterAllow ma = new MitarbeiterAllow();
		try {
			con = DatabaseInitializer.connectToDatabase();
			DatabaseInitializer.createTables(con);
			ConsoleInterface ci = new ConsoleInterface();
			ci.anmeldung();

			// Bis hierhin ist alles leserlich, das danach sollte in eine Methode ausgelagert werden, am besten in den TestdataFiller.
			// Stell dir vor du liest das main Programm zum ersten mal. Dann würdest du verstehen wollen, was die ganze Software eigentlich macht.
			// Ich lese: Datenbankverbidung aufbauen > Datenbankstruktur erstellen > Anmeldung des Benutzers über das User Interface > Testdaten
			// > Testdaten > Testdaten > Testdaten > Testdaten > ... > scann > scann > ... > parse > parse > ... > Kredit wird ausgestellt
			// Sinnvoll wäre das Testdaten in den TestdataFiller ausgelagert werden, die ganzen Scanner und das Parsing in das ConsoleInterface.
			// Immer dran denken: Du musst wissen, wofür deine Klassen verantwortlich sind und ihnen dann auch die entsprechenden Aufgaben übergeben.
			TestdataFiller.insertAngestellten(con);
			TestdataFiller.insertFilialleiter(con);
			TestdataFiller.insertKunden(con);
			TestdataFiller.insertGirokonto(con);
			TestdataFiller.insertSparbuch(con);
			TestdataFiller.insertKreditkarte(con);
			TestdataFiller.insertKredit(con);
			TestdataFiller.insertKundeSparbuch(con);
			TestdataFiller.insertKundeGirokonto(con);
			TestdataFiller.insertKundeKreditkarte(con);
			TestdataFiller.insertKundeKredit(con);
			TestdataFiller.insertAngestellterKunde(con);

			// Ab hier ConsoleInterface Methode.
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

			// Ab hier wieder main.
			con.close();
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
