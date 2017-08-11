package tools;

import java.util.ArrayList;

import gui.LoginInterface;

public abstract class LoginChecker {

	public static boolean checkValidId(int id, ArrayList<Integer> allId) {

		if (allId.contains(id)) {
			return true;
		} else {
			return false;
		}
	}

	public static void logoutIfSessionExpired(AuthenticationCookie cookie) {

		if (!cookie.isValidCookie()) {
			cookie = null;
			LoginInterface.anmeldung();
		}
	}
}
