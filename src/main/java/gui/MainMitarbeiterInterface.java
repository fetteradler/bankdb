package gui;

import java.sql.SQLException;
import java.text.ParseException;

import database.MitarbeiterAllow;
import database.SelectKonto;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public class MainMitarbeiterInterface {

	/**
	 * Main menu for 'Mitarbeiter'. They can chose what they want to do an
	 * navigate via console input.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void mitarbeiterMainMenu(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String mainMenuChoices = "Wie möchten Sie als nächstes vorgehen? \n1 - Kredit für einen bestehenden Kunden hinzufügen \n"
				+ "2 - Konto für bestehenden Kunden hinzufügen \n3 - Anzeigen eines Kunden \n"
				+ "4 - Neuen Kunden hinzufügen \n5 - Löschen oder bearbeiten eines Kunden \n"
				+ "6 - Anzeigen eines Kontos \n7 - Löschen eines bestehenden Kontos \n" + "8 - Abmelden \n"
				+ "0 - Beenden";

		int input = UserInputReader.requestInteger(mainMenuChoices, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });

		switch (input) {
		case 0:
			break;
		case 1:
			addKredit(cookie);
			break;
		case 2:
			addKonto(cookie);
			break;
		case 3:
			findKunde(cookie);
			break;
		case 4:
			// addNewKunde(cookie);
			MitarbeiterAddKundeInterface.addNewKunde(cookie);
			break;
		case 5:
			editOrDeleteKunde(cookie);
			break;
		case 6:
			showKonto(cookie);
			break;
		case 7:
			deleteKonto(cookie);
			break;
		case 9:
			LoginInterface.anmeldung();
			break;
		}
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Kredit' to a 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addKredit(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		MitarbeiterAllow ma = new MitarbeiterAllow();
		String id = "Bewillige Kredit\nKunden-Id:";
		String sum = "Betrag";
		String zinsen = "Zinsen";
		String guess = "Raten";
		String time = "Laufzeit (bsp.: 21.07.2019)";

		int kundeId = UserInputReader.requestInteger(id);
		int betrag = UserInputReader.requestInteger(sum);
		int zinsenProMonat = UserInputReader.requestInteger(zinsen);
		int ratenProMonat = UserInputReader.requestInteger(guess);
		String laufzeit = UserInputReader.requestString(time);

		try {
			ma.allowKredit(kundeId, betrag, zinsenProMonat, ratenProMonat, laufzeit);
		} catch (SQLException | ParseException e) {
			System.out.println("Falsche Eingabe!\nBitte versuchen Sie es erneut: ");
			addKredit(cookie);
		}

		System.out.println("Kredit wurde erfolgreich hinzugefügt.");
		mitarbeiterMainMenu(cookie);
	}

	/**
	 * Choose kind of 'Konto' which want to add.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addKonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kontoChoices = "Wie möchten Sie als nächstes vorgehen? \n1 - Hinzufügen Girokonto\n"
				+ "2 - Hinzufügen Kreditkartenkonto\n3 - Hinzufügen Sparbuch\n4 - Abbrechen\n";

		int input = UserInputReader.requestInteger(kontoChoices, new int[] { 1, 2, 3, 4 });
		switch (input) {
		case 1:
			MitarbeiterAddKontoInterface.addGirokonto(cookie);
			break;
		case 2:
			MitarbeiterAddKontoInterface.addKreditkartenkonto(cookie);
			break;
		case 3:
			MitarbeiterAddKontoInterface.addSparbuch(cookie);
			break;
		case 4:
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
			break;
		}
		
		System.out.println();
		mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Mitarbeiter' can choose a several 'Kunde' and show the details of
	 * its account. It is possible to search for the 'Kunde' via 'kundeId' or
	 * 'vorname' & 'nachname'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void findKunde(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kundeChoices = "Bitte wählen Sie: \n"
				+ "1 - Suche über Kunden ID \n2 - Suche über Name des Kunden\n3 - Abbrechen";

		int input = UserInputReader.requestInteger(kundeChoices, new int[] { 1, 2, 3, 4 });
		switch (input) {
		case 1:
			MitarbeiterSelectKundeInterface.searchKundeId(cookie);
			break;
		case 2:
			MitarbeiterSelectKundeInterface.searchKundeName(cookie);
			break;
		case 3:
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
			break;
		}
		
		System.out.println();
		mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Kunde' to edit or delete the profile of
	 * it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void editOrDeleteKunde(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kundenAccountChoices = "1 - Löschen eines Kunden \n2 - Bearbeiten eine Kunden \n3 - Abbrechen";

		int input5 = UserInputReader.requestInteger(kundenAccountChoices, new int[] { 1, 2, 3 });

		switch (input5) {
		case 1:
			MitarbeiterEditInterface.deleteKunde(cookie);
			break;
		case 2:
			String idChoices = "Bitte geben Sie die ID des zu bearbeitenden Kunden ein:";
			int kundeId = UserInputReader.requestInteger(idChoices);
			AllUserInterface.editKunde(cookie, kundeId);
			break;
		case 3:
			mitarbeiterMainMenu(cookie);
			break;
		}
		
		System.out.println();
		mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Kunde' and a type of 'Konto' and show all
	 * entries of this type.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void showKonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kontoChoices = "Welche Art von Konto möchten Sie anzeigen?\n1 - Kredit\n2 - Girokonto\n3 - Kreditkartenkonto\n4 - Sparbuch\n5 - Abbrechen";
		int input = UserInputReader.requestInteger(kontoChoices, new int[] { 1, 2, 3, 4, 5 });
		String idChoices = "Bitte geben Sie die Kunden-ID ein: ";
		int kundeId = UserInputReader.requestInteger(idChoices);

		SelectKonto sk = new SelectKonto();

		try {
			switch (input) {
			case 1:
				sk.kontoType(kundeId, 1);
				break;
			case 2:
				sk.kontoType(kundeId, 2);
				break;
			case 3:
				sk.kontoType(kundeId, 3);
				break;
			case 4:
				sk.kontoType(kundeId, 4);
				break;
			case 5:
				mitarbeiterMainMenu(cookie);
				break;
			}
		} catch (SQLException e) {
			System.out.println("Fehler in der Datenbank!" + e);
			mitarbeiterMainMenu(cookie);
		}
		
		System.out.println();
		mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Konto' an can delete it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteKonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kundenAccountChoices = "Welche Art Konto möchten Sie entfernen? \n1 - Girokonto \n2 - Kredit \n"
				+ "3 - Kreditkartenkonto \n4 - Sparbuch \n5 - Abbrechen";

		int input = UserInputReader.requestInteger(kundenAccountChoices, new int[] { 1, 2, 3, 4, 5 });

		switch (input) {
		case 1:
			MitarbeiterEditInterface.deleteGirokonto(cookie);
			break;
		case 2:
			MitarbeiterEditInterface.deleteKredit(cookie);
			break;
		case 3:
			MitarbeiterEditInterface.deleteKreditkartenkonto(cookie);
			break;
		case 4:
			MitarbeiterEditInterface.deleteSparbuch(cookie);
			break;
		case 5:
			mitarbeiterMainMenu(cookie);
			break;
		}
		
		System.out.println();
		mitarbeiterMainMenu(cookie);
	}
}
