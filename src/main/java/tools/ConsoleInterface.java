package tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import application.MitarbeiterUse;

/**
 * User navigation through the program. Input via console.
 * 
 * @author caro
 *
 */
public class ConsoleInterface {

	Scanner scanner = new Scanner(System.in);

	/**
	 * Menu to chose what to do next. Login as 'Mitarbeiter', login as 'Kunde'
	 * or to quit the program.
	 */
	public void anmeldung() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("----- Herzlich wilkommen bei Bankdb ----- \n" + "Was möchten Sie als nächstes tun?");
		System.out.println("1 - Anmeldung Mitarbeiter \n" + "2 - Anmeldung Kunde \n" + "3 - Beenden");

		int i = 0;
		while (i == 0) {
			int input = Integer.parseInt(scanner.next());
			switch (input) {
			case 1:
				System.out.println("Anmeldung Mitarbeiter...");
				i = 1;
				mitarbeiterScreen();
				break;
			case 2:
				System.out.println("Anmeldung Kunde...");
				i = 1;
				System.out.println("Wie möchten Sie als nächstes vorgehen?");
				break;
			case 3:
				System.out.println("Das Programm wird beendet...");
				i = 1;
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
		scanner.close();
	}

	/**
	 * Menu for 'Mitarbeiter'. They can chose what they want to do an navigate
	 * via console input.
	 */
	public void mitarbeiterScreen() {

		System.out.println(
				"Wie möchten Sie als nächstes vorgehen? \n" + "1 - Kredit für einen bestehenden Kunden hinzufügen \n"
						+ "2 - Girokonto für bestehenden Kunden hinzufügen \n" + "3 - Abmelden \n" + "4 - Beenden");

		int i = 0;
		while (i == 0) {
			int auswahl = Integer.parseInt(scanner.next());
			switch (auswahl) {
			case 1:

				addKredit();
				i = 1;
				break;
			case 2:
				addGirokonto();
				i = 1;
				break;
			case 3:
				System.out.println("Abmeldung erfolgt...");
				anmeldung();
				i = 1;
				break;
			case 4:
				System.out.println("Das Programm wird beendet...");
				i = 1;
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Girokonto' to a 'Kunde'.
	 */
	public void addGirokonto() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Girokonto: kundeId, guthaben, gebuehrenProMonat");

		try {
			con = DatabaseInitializer.connectToDatabase();
			String kundeId = scanner.next();
			String guthaben = scanner.next();
			String gebuehrenProMonat = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(guthaben);
			int input3 = Integer.parseInt(gebuehrenProMonat);
			mu.allowGirokonto(con, input1, input2, input3);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addGirokonto();
		}
		System.out.println("Girokonto erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Kredit' to a 'Kunde'.
	 */
	public void addKredit() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Kredit: kundeId, betrag, zinsen, raten, laufzeit(bsp.: 21.07.2019)");

		try {
			con = DatabaseInitializer.connectToDatabase();
			String kundeId = scanner.next();
			String betrag = scanner.next();
			String zinsen = scanner.next();
			String raten = scanner.next();
			String laufzeit = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(betrag);
			int input3 = Integer.parseInt(zinsen);
			int input4 = Integer.parseInt(raten);
			mu.allowKredit(con, input1, input2, input3, input4, laufzeit);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		} catch (ParseException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		}
		System.out.println("Kredit erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}
}
