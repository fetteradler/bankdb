package gui;

import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public class MainKundenInterface {
	
	/**
	 * Menu for 'Kunde'. They can chose what they want to do an navigate via
	 * console input.
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

	public static void editProfile(AuthenticationCookie cookie) {
		
		LoginChecker.logoutIfSessionExpired(cookie);

	}

	public static void showKonto(AuthenticationCookie cookie) {

		LoginChecker.logoutIfSessionExpired(cookie);
		
		

	}

	public static void showProfile(AuthenticationCookie cookie) {
		
		LoginChecker.logoutIfSessionExpired(cookie);

		
	}
}
