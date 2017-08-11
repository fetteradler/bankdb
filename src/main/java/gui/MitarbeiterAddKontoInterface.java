package gui;

import java.sql.SQLException;

import database.MitarbeiterAllow;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public abstract class MitarbeiterAddKontoInterface {

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Girokonto' to a 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addGirokonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		MitarbeiterAllow ma = new MitarbeiterAllow();

		String id = "Füge Girokonto hinzu.\nKunden-ID:";
		String guthaben = "Guthaben:";
		String gebuehren = "Kontoführungsgebühren pro Monat:";
		int kundeId = UserInputReader.requestInteger(id);
		int betrag = UserInputReader.requestInteger(guthaben);
		int gebuehrenProMonat = UserInputReader.requestInteger(gebuehren);

		try {
			ma.allowGirokonto(kundeId, betrag, gebuehrenProMonat);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addGirokonto(cookie);
		}

		System.out.println("Girokonto wurde erfolgreich hinzugefügt.");
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Kreditkartenkonto' to a
	 * 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addKreditkartenkonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		MitarbeiterAllow ma = new MitarbeiterAllow();

		String id = "Füge Kreditkartenkonto hinzu.\nKunden-ID:";
		String guthaben = "Betrag:";
		String zinsen = "Zinsen pro Monat:";
		int kundeId = UserInputReader.requestInteger(id);
		int betrag = UserInputReader.requestInteger(guthaben);
		int zinsenProMonat = UserInputReader.requestInteger(zinsen);

		try {
			ma.allowGirokonto(kundeId, betrag, zinsenProMonat);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKreditkartenkonto(cookie);
		}

		System.out.println("Kreditkartenkonto wurde erfolgreich hinzugefügt.");
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);

	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Sparbuch' to a 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addSparbuch(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		MitarbeiterAllow ma = new MitarbeiterAllow();

		String id = "Füge Sparbuch hinzu.\nKunden-ID:";
		String guthaben = "Guthaben:";
		String zinsen = "Zinsen pro Monat:";
		int kundeId = UserInputReader.requestInteger(id);
		int betrag = UserInputReader.requestInteger(guthaben);
		int zinsenProMonat = UserInputReader.requestInteger(zinsen);

		try {
			ma.allowGirokonto(kundeId, betrag, zinsenProMonat);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addSparbuch(cookie);
		}

		System.out.println("Sparbuch wurde erfolgreich hinzugefügt.");
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}
}
