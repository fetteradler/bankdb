package tools;

import java.util.ArrayList;

import gui.LoginInterface;

/**
 * Check if a session and the id is valid.
 * 
 * @author CM
 *
 */
public abstract class LoginChecker {

	/**
	 * Check an id if it is valid.
	 * 
	 * @param id
	 *            Id that wants to check if it is valid.
	 * @param allId
	 *            List of all Id's.
	 * @return If the Id is valid or not.
	 */
	public static boolean checkValidId(int id, ArrayList<Integer> allId) {

		if (allId.contains(id)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Logout user if the session is not valid. Return back to
	 * 'LoginInterface.anmeldung()'. Set AuthenticationCookie = null.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 */
	public static void logoutIfSessionExpired(AuthenticationCookie cookie) {

		if (!cookie.isValidCookie()) {
			cookie = null;
			LoginInterface.anmeldung();
		}
	}
}
