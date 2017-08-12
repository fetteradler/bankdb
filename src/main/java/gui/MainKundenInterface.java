package gui;

import java.sql.SQLException;

import database.SelectKonto;
import database.SelectKunde;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

/**
 * Main menu interface of 'Kunde' with a summary of all actions the 'Kunde' can
 * do.
 * 
 * @author CM
 *
 */
public class MainKundenInterface {

	/**
	 * Menu for 'Kunde'. They can chose what they want to do an navigate via
	 * console input. First screen after a successful authentication and always
	 * the user do 'Abbrechen'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void kundeMainMenu(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String mainMenuChoices = "Was möchten Sie als nächstes tun? \n1 - Profil anzeigen \n"
				+ "2 - Profil bearbeiten \n3 - Kontoübersicht \n4 - Abmelden \n" + "0 - Beenden \n";

		int input = UserInputReader.requestInteger(mainMenuChoices, new int[] { 0, 1, 2, 3, 4 });

		switch (input) {
		case 0:
			break;
		case 1:
			showProfile(cookie);
			break;
		case 2:
			editProfile(cookie);
			break;
		case 3:
			showKonto(cookie);
			break;
		case 4:
			LoginInterface.anmeldung();
			break;
		}
	}

	/**
	 * Interface to show all the data of the logged in 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void showProfile(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		SelectKunde skd = new SelectKunde();
		int kundeId = cookie.getUserId();

		try {
			skd.selectKundeViaId(kundeId);
		} catch (SQLException e) {
			System.out.println("Es ist ein fehler aufgetretten.\n");
			kundeMainMenu(cookie);
		}

		System.out.println();
		kundeMainMenu(cookie);
	}

	/**
	 * Open interface to edit attributes of the logged in 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void editProfile(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		int kundeId = cookie.getUserId();

		AllUserInterface.editKunde(cookie, kundeId);

		System.out.println();
		kundeMainMenu(cookie);
	}

	/**
	 * Interface to select a type of 'Konto' and show all of them for the logged
	 * in 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void showKonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		int kundeId = cookie.getUserId();

		String kontoChoices = "Welche Art von Konto möchten Sie anzeigen?\n1 - Kredit\n2 - Girokonto\n3 - Kreditkartenkonto\n4 - Sparbuch\n5 - Abbrechen";
		int input = UserInputReader.requestInteger(kontoChoices, new int[] { 1, 2, 3, 4, 5 });

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
				kundeMainMenu(cookie);
				break;
			}
		} catch (SQLException e) {
			System.out.println("Fehler in der Datenbank!" + e);
			kundeMainMenu(cookie);
		}

		System.out.println();
		kundeMainMenu(cookie);
	}

}
