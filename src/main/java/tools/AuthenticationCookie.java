package tools;

import java.util.Date;

/**
 * Object to check if a session is vaild. Check if the entry for an Id for the
 * chosen role by 'anmeldung' is a existing entry in database. If the user don't
 * do something for 30 minutes, the session change to invalid.
 * 
 * @author CM
 *
 */
public class AuthenticationCookie {

	/**
	 * Time for a valid session with no action.
	 */
	private static int expiringTimeInMilliSeconds = 1000 * 60 * 30; // 30
																	// minutes

	/**
	 * Role of the user session('Kunde', 'Mitarbeiter' or
	 *            'Filialleiter').
	 */
	private Role role;
	/**
	 * Id of the user.
	 */

	private int userId;
	/**
	 * Date the session change to invalid.
	 */
	private Date expiringDate;

	/**
	 * Create object and set expiring date of current time + 30 minutes.
	 * 
	 * @param role
	 *            Role of the user session('Kunde', 'Mitarbeiter' or
	 *            'Filialleiter').
	 * @param userId
	 *            Id of the user.
	 */
	public AuthenticationCookie(Role role, int userId) {
		this.role = role;
		this.userId = userId;
		expiringDate = new Date();
		expiringDate.setTime(expiringDate.getTime() + expiringTimeInMilliSeconds);
	}

	/**
	 * Create expiring date of current time + 30 minutes.
	 */
	public void extendExpiringTime() {
		expiringDate = new Date();
		expiringDate.setTime(expiringDate.getTime() + expiringTimeInMilliSeconds);
	}

	/**
	 * Check if session is sill valid.
	 * 
	 * @return true if session is valid -> expiring date is 'bigger' than
	 *         current time.
	 */
	public boolean isValidCookie() {
		Date currentTime = new Date();
		return (expiringDate.getTime() - currentTime.getTime()) > 0;
	}

	/**
	 * Getter of userId
	 * 
	 * @return Id of the user.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Getter of roleId
	 * 
	 * @return role of the user.
	 */
	public Role getRole() {
		return role;
	}
}