package gui;

import java.sql.SQLException;

import database.InsertOrDeleteMitarbeiter;
import database.SelectMitarbeiter;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

/**
 * Main menu interface of 'Filialleiter' with a summary of all actions the
 * 'Filialleiter' can do.
 * 
 * @author CM
 *
 */
public class MainFilialleiterInterface {

	/**
	 * Menu for 'Filialleiter'. They can chose what they want to do an navigate
	 * via console input. First screen after a successful authentication and
	 * always the user do 'Abbrechen'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void filialleiterMainMenu(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String mainMenuChoices = "Wie möchten Sie als nächstes vorgehen? \n1 - Alle Mitarbeiter auflisten \n2 Bestimmten Mitarbeiter anzeigen"
				+ "\n3 - Neuen Mitarbeiter hinzufügen\n4 - Mitarbeiter löschen\n5 - Abmelden \n0 - Beenden\n";

		int input = UserInputReader.requestInteger(mainMenuChoices, new int[] { 0, 1, 2, 3 });

		switch (input) {
		case 0:
			break;
		case 1:
			showAllMitarbeiter(cookie);
			break;
		case 2:
			showSpecificMitarbeiter(cookie);
			break;
		case 3:
			AddInterface.addNewMitarbeiter(cookie);
			break;
		case 4:
			break;
		case 5:
			LoginInterface.anmeldung();
			break;
		}
	}

	/**
	 * Interface to show all attributes of all 'Mitarbeiter' from the database.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void showAllMitarbeiter(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		SelectMitarbeiter sm = new SelectMitarbeiter();

		try {
			sm.selectAllMitarbeiter();
		} catch (SQLException e) {
			System.out.println("Es ist ein fehler aufgetretten.\n");
			filialleiterMainMenu(cookie);
		}

		System.out.println();
		filialleiterMainMenu(cookie);
	}

	/**
	 * Interface to chose the way to search for a specific 'Mitarbeiter' to show
	 * its profile.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void showSpecificMitarbeiter(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String choices = "Wie möchten Sie vorgehen?\n1 - Suche über Mitarbeiter-ID\n2 - Suche über Name\n3 - Abbrechen";
		int input = UserInputReader.requestInteger(choices, new int[] { 1, 2, 3, 4 });

		SelectMitarbeiter sm = new SelectMitarbeiter();

		switch (input) {
		case 1:
			String idChoices = "Bitte geben Sie die ID ein:";
			int angestelltId = UserInputReader.requestInteger(idChoices);
			try {
				sm.selectMitarbeiterViaId(angestelltId);
			} catch (SQLException e) {
				System.out.println("Es ist ein fehler aufgetretten.\n");
				filialleiterMainMenu(cookie);
			}
			break;
		case 2:
			String vornameChoices = "Bitte geben Sie den Vorname ein:";
			String vorname = UserInputReader.requestString(vornameChoices);
			String nachnameChoices = "Bitte geben Sie den Nachname ein:";
			String nachname = UserInputReader.requestString(nachnameChoices);
			try {
				sm.selectMitarbeiterViaName(vorname, nachname);
			} catch (SQLException e) {
				System.out.println("Es ist ein fehler aufgetretten.\n");
				filialleiterMainMenu(cookie);
			}
			break;
		case 3:
			filialleiterMainMenu(cookie);
			break;
		}

		System.out.println();
		filialleiterMainMenu(cookie);
	}

	/**
	 * The 'Filialleiter' can choose a 'Mitarbeiter' and delete everything of
	 * it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteMitarbeiter(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		InsertOrDeleteMitarbeiter fed = new InsertOrDeleteMitarbeiter();

		String idChoices = "Bitte geben Sie die ID des zu löschenden Mitarbeiter ein:";
		int angestelltId = UserInputReader.requestInteger(idChoices);

		try {
			fed.deletChosenMitarbeiter(angestelltId);
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht gelöscht werden.\n");
			MainFilialleiterInterface.filialleiterMainMenu(cookie);
		}

		System.out.println("Mitarbeiter wurde erfolgreich gelöscht.\n");

		MainFilialleiterInterface.filialleiterMainMenu(cookie);
	}
}
