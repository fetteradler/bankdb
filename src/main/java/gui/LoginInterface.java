package gui;

import database.tools.ValidInputFinder;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.Role;
import tools.UserInputReader;

/**
 * First interface after starting the application. User can chose the role it
 * wants to log in. After it chose role, user need to authentication session via
 * a valid Id.
 * 
 * @author CM
 *
 */
public abstract class LoginInterface {

	/**
	 * String to show user what are its next options.
	 */
	private static String anmeldungChoices = "Was möchten Sie als nächstes tun?\n1 - Anmeldung Mitarbeiter \n2 - Anmeldung Kunde \n"
			+ "3 - Anmeldung Filialleiter \n0 - Beenden";

	/**
	 * Menu to chose what to do next. Login as 'Mitarbeiter', login as 'Kunde',
	 * login as 'Filialleiter' or to quit the program.
	 */
	public static AuthenticationCookie anmeldung() {

		System.out.println("----- Herzlich wilkommen bei Bankdb ----- \n");

		AuthenticationCookie cookie = null;

		boolean requestingValidUserInput = true;
		while (requestingValidUserInput) {
			int input = -1;
			try {
				input = UserInputReader.requestInteger(anmeldungChoices, new int[] { 0, 1, 2, 3 });
			} catch (NumberFormatException e) {
				// Catches invalid user input and leads to default case.
			}
			requestingValidUserInput = false;
			switch (input) {
			case 0:
				break;
			case 1:
				int mitarbeiterId = UserInputReader
						.requestInteger("Anmeldung Mitarbeiter... \n" + "Bitte geben Sie ihre ID ein: ");
				if (LoginChecker.checkValidId(mitarbeiterId, ValidInputFinder.findAllMitarbeiterId())) {
					cookie = new AuthenticationCookie(Role.MITARBEITER, mitarbeiterId);
					MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
				} else {
					System.out.println("Eingegebene ID ist ungültig! \n");
					requestingValidUserInput = true;
				}
				break;
			case 2:
				int kundenId = UserInputReader
						.requestInteger("Anmeldung Kunde... \n" + "Bitte geben Sie ihre ID ein: ");
				if (LoginChecker.checkValidId(kundenId, ValidInputFinder.findAllKundeId())) {
					cookie = new AuthenticationCookie(Role.KUNDE, kundenId);
					MainKundenInterface.kundeMainMenu(cookie);
				} else {
					System.out.println("Eingegebene ID ist ungültig! \n");
					requestingValidUserInput = true;
				}
				break;
			case 3:
				int filialleiterId = UserInputReader
						.requestInteger("Anmeldung Filialleiter... \n" + "Bitte geben Sie ihre ID ein: ");
				if (LoginChecker.checkValidId(filialleiterId, ValidInputFinder.findAllFilialleiterId())) {
					cookie = new AuthenticationCookie(Role.FILIALLEITER, filialleiterId);
					MainFilialleiterInterface.filialleiterMainMenu(cookie);
				} else {
					System.out.println("Eingegebene ID ist ungültig! \n");
					requestingValidUserInput = true;
				}
				break;
			}
		}
		return cookie;
	}
}
