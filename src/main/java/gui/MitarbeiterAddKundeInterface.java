package gui;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.MitarbeiterEditDatabase;
import database.tools.SQLTableParser;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public class MitarbeiterAddKundeInterface {

	/**
	 * The 'Mitarbeiter' can create a new account for 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addNewKunde(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		MitarbeiterEditDatabase med = new MitarbeiterEditDatabase();

		try {
			med.includeNewKunde(addVorname(cookie), addNachname(cookie), addGeburtsdatum(cookie), addTelephone(cookie),
					addMail(cookie), addWohnort(cookie), addStrasse(cookie), addPLZ(cookie), addLand(cookie),
					addTitel(cookie), addAufnahmedatum(cookie), addKontostatus(cookie), addKreditberechtigung(cookie));
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}

		System.out.println("Kunde wurde erfolgreich hinzugefügt.");
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}

	/**
	 * Checks if entry for 'Vorname' starts with an upper case letter.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addVorname(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String vorname = "Vorname:";
		String vornameEntry = UserInputReader.requestString(vorname);
		char[] vornameArray = vornameEntry.toCharArray();

		if (!Character.isUpperCase(vornameArray[0])) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addVorname(cookie);
		}
		return vornameEntry;
	}

	/**
	 * Checks if entry for 'Nachname' starts with an upper case letter.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addNachname(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String nachname = "Nachname:";
		String nachnameEntry = UserInputReader.requestString(nachname);
		char[] nachnameArray = nachnameEntry.toCharArray();

		if (!Character.isUpperCase(nachnameArray[0])) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addNachname(cookie);
		}

		return nachnameEntry;
	}

	/**
	 * Checks if entry for 'Geburtsdatum' has valid form and is in the past.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static Date addGeburtsdatum(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String geb = "Geburtsdatum:";
		Date gebEntry = UserInputReader.requestDate(geb);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		java.util.Date dt = new java.util.Date();
		Date currentTimeSQL = null;
		try {
			currentTimeSQL = SQLTableParser.convertingDateFormat(sdf.format(dt));
		} catch (ParseException e) {
			System.out.println("Fehler");
		}

		int check = gebEntry.compareTo(currentTimeSQL);

		if (check >= 0) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addGeburtsdatum(cookie);
		}

		return gebEntry;
	}

	/**
	 * Checks if the entry for 'TelNr' only contains digits
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addTelephone(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String telNr = "Telefon:";
		String telNrEntry = UserInputReader.requestString(telNr);
		char[] telNrArray = telNrEntry.toCharArray();

		for (int i = 0; i < telNrArray.length; i++) {
			if (!Character.isDigit(telNrArray[i])) {
				System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
				addTelephone(cookie);
			}
		}

		return telNrEntry;
	}

	/**
	 * Checks if the entry for 'Mail' has an '@' in it.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addMail(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String mail = "Mail:";
		String mailEntry = UserInputReader.requestString(mail);

		boolean check = mailEntry.contains("@");
		if (!check) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addMail(cookie);
		}

		return mailEntry;
	}

	/**
	 * Checks if entry for 'Wohnort' starts with an upper case letter.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addWohnort(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String wohnort = "Wohnort:";
		String wohnortEntry = UserInputReader.requestString(wohnort);
		char[] wohnortArray = wohnortEntry.toCharArray();

		if (!Character.isUpperCase(wohnortArray[0])) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addVorname(cookie);
		}
		return wohnortEntry;
	}

	/**
	 * Checks if entry for 'Strasse' starts with an upper case letter.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addStrasse(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String strasse = "Straße:";
		String strasseEntry = UserInputReader.requestString(strasse);
		char[] strasseArray = strasseEntry.toCharArray();

		if (!Character.isUpperCase(strasseArray[0])) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addVorname(cookie);
		}
		return strasseEntry;
	}

	/**
	 * Checks if the entry for 'TelNr' only contains digits
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addPLZ(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String plz = "PLZ:";
		String plzEntry = UserInputReader.requestString(plz);
		char[] plzArray = plzEntry.toCharArray();

		for (int i = 0; i < plzArray.length; i++) {
			if (!Character.isDigit(plzArray[i])) {
				System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
				addPLZ(cookie);
			}
		}
		return plzEntry;
	}

	/**
	 * Checks if entry for 'Land' starts with an upper case letter.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addLand(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String land = "Land:";
		String landEntry = UserInputReader.requestString(land);
		char[] landArray = landEntry.toCharArray();

		if (!Character.isUpperCase(landArray[0])) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addVorname(cookie);
		}
		return landEntry;
	}

	/**
	 * Checks if entry for 'Aufnahmedatum' has valid form and is in the past.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static Date addAufnahmedatum(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String aufnahme = "Aufnahmedatum:";
		Date aufnahmeEntry = UserInputReader.requestDate(aufnahme);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		java.util.Date dt = new java.util.Date();
		Date currentTimeSQL = null;
		try {
			currentTimeSQL = SQLTableParser.convertingDateFormat(sdf.format(dt));
		} catch (ParseException e) {
		}

		int check = aufnahmeEntry.compareTo(currentTimeSQL);

		if (check > 0) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addGeburtsdatum(cookie);
		}

		return aufnahmeEntry;
	}

	/**
	 * Check if entry for 'Titel' is vaild enum.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addTitel(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String titel = "Titel (Herr, Frau):";
		String titelEntry = UserInputReader.requestString(titel).toUpperCase();
		
		if(!(titelEntry.trim().toUpperCase().equals("HERR") || titelEntry.trim().toUpperCase().equals("FRAU"))) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addTitel(cookie);
		}
		return titelEntry;
	}

	/**
	 * Check if entry for 'Kontostatus' is vaild enum.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static String addKontostatus(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String status = "Kontostatus (Standartkonto, Jugendkonto, Studentenkonto):";
		String statusEntry = UserInputReader.requestString(status).toUpperCase();
		
		if(!(statusEntry.trim().equals("STANDARTKONTO") || statusEntry.trim().equals("JUGENDKONTO")
				|| statusEntry.trim().equals("STUDENTENKONTO"))) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addKontostatus(cookie);
		}
		return statusEntry;
	}

	/**
	 * Checks if the entry of 'Kreditberechtigung' is valid.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static int addKreditberechtigung(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String kreditBerecht = "Kreditberechtigung (0, 1):";
		int kreditBerechtEntry = UserInputReader.requestInteger(kreditBerecht);

		if (!(kreditBerechtEntry == 0 || kreditBerechtEntry == 1)) {
			System.out.println("Ihre Eingabe ist ungültig! Bitte versuchen Sie es erneut: ");
			addKreditberechtigung(cookie);
		}
		return kreditBerechtEntry;
	}
}
