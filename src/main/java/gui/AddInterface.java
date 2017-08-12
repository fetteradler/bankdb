package gui;

import java.sql.SQLException;

import database.InsertOrDeleteMitarbeiter;
import database.InsertOrDeleteKunde;
import tools.AuthenticationCookie;
import tools.LoginChecker;

/**
 * Interface to navigate the 'Mitarbeiter' to add a new 'Kunde' or to navigate
 * the 'Filialleiter' to add a new 'Mitarbeiter'.
 * 
 * @author CM
 *
 */
public class AddInterface {

	/**
	 * The 'Mitarbeiter' can create a new account for 'Kunde'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addNewKunde(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		InsertOrDeleteKunde med = new InsertOrDeleteKunde();

		try {
			med.includeNewKunde(CheckAddAttributeInterface.addVorname(cookie),
					CheckAddAttributeInterface.addNachname(cookie), CheckAddAttributeInterface.addGeburtsdatum(cookie),
					CheckAddAttributeInterface.addTelephone(cookie), CheckAddAttributeInterface.addMail(cookie),
					CheckAddAttributeInterface.addWohnort(cookie), CheckAddAttributeInterface.addStrasse(cookie),
					CheckAddAttributeInterface.addPLZ(cookie), CheckAddAttributeInterface.addLand(cookie),
					CheckAddAttributeInterface.addTitel(cookie), CheckAddAttributeInterface.addAufnahmedatum(cookie),
					CheckAddAttributeInterface.addKontostatus(cookie),
					CheckAddAttributeInterface.addKreditberechtigung(cookie));
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Kunde konnte nicht hinzugefügt werden.");
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}

		System.out.println("Kunde wurde erfolgreich hinzugefügt.");
		MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
	}

	/**
	 * The 'Filialleiter' can create a new account for 'Mitarbeiter'.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void addNewMitarbeiter(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		InsertOrDeleteMitarbeiter fed = new InsertOrDeleteMitarbeiter();

		try {
			fed.includeNewMitarbeiter(CheckAddAttributeInterface.addVorname(cookie),
					CheckAddAttributeInterface.addNachname(cookie), CheckAddAttributeInterface.addGeburtsdatum(cookie),
					CheckAddAttributeInterface.addTelephone(cookie), CheckAddAttributeInterface.addMail(cookie),
					CheckAddAttributeInterface.addWohnort(cookie), CheckAddAttributeInterface.addStrasse(cookie),
					CheckAddAttributeInterface.addPLZ(cookie), CheckAddAttributeInterface.addLand(cookie),
					CheckAddAttributeInterface.addTitel(cookie), CheckAddAttributeInterface.addEinstelldatum(cookie),
					CheckAddAttributeInterface.addMonatslohn(cookie), CheckAddAttributeInterface.addFristdatum(cookie),
					CheckAddAttributeInterface.addAngestelltenStatus(cookie),
					CheckAddAttributeInterface.addAnstellung(cookie));
		} catch (SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten. Mitarbeiter konnte nicht hinzugefügt werden.");
			MainFilialleiterInterface.filialleiterMainMenu(cookie);
		}

		System.out.println("Mitarbeiter wurde erfolgreich hinzugefügt.");
		MainFilialleiterInterface.filialleiterMainMenu(cookie);
	}
}
