package gui;

import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public class MainFilialleiterInterface {

	/**
	 * Menu for 'Filialleiter'. They can chose what they want to do an navigate
	 * via console input.
	 */
	public static void filialleiterMainMenu(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);

		String mainMenuChoices = "Wie möchten Sie als nächstes vorgehen? \n1 - Alle Mitarbeiter auflisten \n2 Bestimmten Mitarbeiter anzeigen"
				+ "39 - Abmelden \n0 - Beenden";
		
		int input = UserInputReader.requestInteger(mainMenuChoices, new int[] { 0, 1, 2, 3 });

		switch(input) {
		case 0:
			break;
		case 1:
			break;
		case 2: 
			break;
		case 3:
			break;
		}
	}
}
