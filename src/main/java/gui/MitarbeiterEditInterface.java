package gui;

import java.sql.SQLException;

import database.MitarbeiterEditDatabase;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public abstract class MitarbeiterEditInterface {

	/**
	 * The 'Mitarbeiter' can choose a 'Kunde' and delete everything of it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteKunde(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		MitarbeiterEditDatabase md = new MitarbeiterEditDatabase();

		String idChoices = "Bitte geben Sie die ID des zu löschenden Kunden ein:";

		int kundeId = UserInputReader.requestInteger(idChoices);
		md.deletChosenKunde(kundeId, cookie);

		System.out.println("Kunde wurde erfolgreich gelöscht.");

		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Girokonto' and delete everything of it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteGirokonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		MitarbeiterEditDatabase md = new MitarbeiterEditDatabase();

		String idChoices = "Bitte geben Sie die Kontonummer des zu löschenden Girokontos ein:";

		int giroId = UserInputReader.requestInteger(idChoices);

		try {
			md.deleteChosenKonto(giroId, 1);
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}
	}
	
	/**
	 * The 'Mitarbeiter' can choose a 'Kreditkatenkonto' and delete everything of it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteKreditkartenkonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		MitarbeiterEditDatabase md = new MitarbeiterEditDatabase();

		String idChoices = "Bitte geben Sie die Kontonummer des zu löschenden Kreditkartenkontos ein:";

		int kreditkarteId = UserInputReader.requestInteger(idChoices);

		try {
			md.deleteChosenKonto(kreditkarteId, 3);
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}
	}
	
	/**
	 * The 'Mitarbeiter' can choose a 'Sparbuch' and delete everything of it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteSparbuch(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		MitarbeiterEditDatabase md = new MitarbeiterEditDatabase();

		String idChoices = "Bitte geben Sie die Kontonummer des zu löschenden Sparbuchs ein:";

		int sparId = UserInputReader.requestInteger(idChoices);

		try {
			md.deleteChosenKonto(sparId, 4);
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}
	}
	
	/**
	 * The 'Mitarbeiter' can choose a 'Kredit' and delete everything of it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void deleteKredit(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		MitarbeiterEditDatabase md = new MitarbeiterEditDatabase();

		String idChoices = "Bitte geben Sie die Kreditnummer des zu löschenden Kredits ein:";

		int kreditId = UserInputReader.requestInteger(idChoices);

		try {
			md.deleteChosenKonto(kreditId, 2);
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}
	}
}
