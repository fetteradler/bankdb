package gui;

import java.sql.SQLException;

import database.UpdateKontostand;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

/**
 * Update the sum of money to a chosen 'Konto'. User can add or discount an
 * amount.
 * 
 * @author CM
 *
 */
public class BookingInterface {

	/**
	 * Interface to select the sum a chosen 'Konto' wants to discount.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 * @param kontoType
	 *            Type of the chosen 'Konto' ('Kredit', 'Girokonto',
	 *            'Kreditkartenkonto', 'Sparbuch').
	 */
	public static void removeMoney(AuthenticationCookie cookie, int kontoType) {

		LoginChecker.logoutIfSessionExpired(cookie);

		int kundeId = cookie.getUserId();

		UpdateKontostand uk = new UpdateKontostand();

		String kontoChoices = "Bitte geben Sie die ID des Kontos ein:";
		int inputKonto = UserInputReader.requestInteger(kontoChoices);
		String betragChoices = "Bitte geben Sie den abzuhebenden Betrag ein:";
		int inputBetrag = UserInputReader.requestInteger(betragChoices);
		boolean choseOperation = true;

		try {
			if (uk.addAndSubtractMoney(kontoType, kundeId, inputKonto, inputBetrag, choseOperation)) {
				System.out.println("Betrag wurde erfolgreich abgehoben.");
			} else {
				System.out.println("Betrag unzulässig. Kein negativer Kontostand möglich.");
			}
		} catch (SQLException e) {
			System.out.println("Fehler in der Datenbank!" + e);
		}
	}

	/**
	 * Interface to select the sum a chosen 'Konto' wants to add.
	 * 
	 * @param cookie
	 *            Authentication for the session. Check if session is still
	 *            valid.
	 * @param kontoType
	 *            Type of the chosen 'Konto' ('Kredit', 'Girokonto',
	 *            'Kreditkartenkonto', 'Sparbuch').
	 */
	public static void addMoney(AuthenticationCookie cookie, int kontoType) {

		LoginChecker.logoutIfSessionExpired(cookie);

		int kundeId = cookie.getUserId();

		UpdateKontostand uk = new UpdateKontostand();

		String kontoChoices = "Bitte geben Sie die ID des Kontos ein:";
		int inputKonto = UserInputReader.requestInteger(kontoChoices);
		String betragChoices = "Bitte geben Sie den aufzubuchenden Betrag ein:";
		int inputBetrag = UserInputReader.requestInteger(betragChoices);
		boolean choseOperation = false;

		try {
			if (uk.addAndSubtractMoney(kontoType, kundeId, inputKonto, inputBetrag, choseOperation)) {
				System.out.println("Betrag wurde erfolgreich hinzugebucht.");
			} else {
				System.out.println("Betrag unzulässig. Kein negativer Kontostand möglich.");
			}
		} catch (SQLException e) {
			System.out.println("Fehler in der Datenbank!" + e);
		}

	}
}
