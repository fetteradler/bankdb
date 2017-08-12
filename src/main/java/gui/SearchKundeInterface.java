package gui;

import java.sql.SQLException;

import database.SelectKunde;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

/**
 * Interface to search for a selected 'Kunde' via 'kundeId' or 'vorname' &
 * 'nachname'.
 * 
 * @author CM
 *
 */
public abstract class SearchKundeInterface {

	/**
	 * 'Mitarbeiter' can search for a chosen 'Kunde' via the 'kundenId'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void searchKundeId(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		SelectKunde skd = new SelectKunde();

		String id = "Bitte geben Sie die Kunden-ID ein:";
		int kundeId = UserInputReader.requestInteger(id);

		try {
			skd.selectKundeViaId(kundeId);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe!\nBitte versuchen Sie es erneut: ");
			searchKundeId(cookie);
		}

		System.out.println();
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}

	/**
	 * 'Mitarbeiter' can search for a chosen 'Kunde' via the 'vorname' and
	 * 'nachname'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void searchKundeName(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		SelectKunde skd = new SelectKunde();

		String kundeVorname = "Bitte geben Sie den Vornamen ein:";
		String kundeNachname = "Bitte geben Sie den Nachnamen ein:";
		String vorname = UserInputReader.requestString(kundeVorname);
		String nachname = UserInputReader.requestString(kundeNachname);

		try {
			skd.selectKundeViaName(vorname, nachname);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe!\nBitte versuchen Sie es erneut: ");
			searchKundeId(cookie);
		}

		System.out.println();
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}
}
