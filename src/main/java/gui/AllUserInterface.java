package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import database.UpdateKunde;
import tools.AuthenticationCookie;
import tools.LoginChecker;
import tools.UserInputReader;

public class AllUserInterface {

	/**
	 * User can edit the attributes in database of a chosen 'Kunde'. Finde the
	 * 'Kunde' via 'kundeId'.
	 */
	public static void editKunde(AuthenticationCookie cookie, int kundeId) {

		LoginChecker.logoutIfSessionExpired(cookie);

		UpdateKunde uk = new UpdateKunde();

		String attributeChoices = "Was möchen Sie bearbeiten? \n1 - Vorname \n2 - Nachname \n"
				+ "3 - Geburtsdatum\n4 - Telefon \n5 - Mail \n6 - Wohnort \n7 - Strasse \n"
				+ "8 - PLZ \n9 - Land \n10 - Kontostatus \n11 - Kreditberechtigung \n0 - Abbrechen \n";
		int input = UserInputReader.requestInteger(attributeChoices,
				new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });

		if (input == 1 || input == 2 || input == 3 || input == 4 || input == 5 || input == 6 || input == 7 || input == 8
				|| input == 9 || input == 10 || input == 11) {
			String attribute = "Bitte geben Sie das neue Attribut ein:";
			LoginChecker.logoutIfSessionExpired(cookie);
			String entryString = UserInputReader.requestString(attribute);

			if (input == 11 && (!entryString.equals("STANDARTKONTO") || !entryString.equals("JUGENDKONTO")
					|| !entryString.equals("STUDENTENKONTO"))) {
				System.out.println("Falsche Eingabe! " + input + " ist kein gültiger Wert.");
				editKunde(cookie, kundeId);
			}
			if (input == 10 && (!entryString.equals("1") || !entryString.equals("2"))) {
				System.out.println("Falsche Eingabe! " + input + " ist kein gültiger Wert.");
				editKunde(cookie, kundeId);
			}
			
			try {
				uk.updateKundeAttribute(kundeId, attribute, input);
			} catch (SQLException | ParseException e) {
				System.out.println("Daten konnten nicht aktualisiert werden. Bitte versuchen Sie es erneut.");
				editKunde(cookie, kundeId);
			}

		} else if (input == 0) {
			MainMitarbeiterInterface.mitarbeiterMainMenu(cookie);
		}
	}

	public static void outputColumnNamesAndAttributes(int maxValueLength, int maxColumnLength, ResultSet rs2,
			int columns) {

		// Output columnames and attributes
		if (maxValueLength == 1) {
			System.out.println("Ihre Suchanfrage ergab leider kein Erfolg. \n");
		} else {

			try {
				rs2.beforeFirst();
				if (maxColumnLength > maxValueLength) {
					for (int i = 1; i <= columns; i++) {
						System.out.print(
								String.format("%-" + (maxColumnLength + 1) + "s", rs2.getMetaData().getColumnLabel(i)));
					}
					System.out.println();
					while (rs2.next()) {
						for (int i = 1; i <= columns; i++) {
							System.out.print(String.format("%-" + (maxColumnLength + 1) + "s", rs2.getString(i)));
						}
						System.out.println();
					}
				} else {
					for (int i = 1; i <= columns; i++) {
						System.out.print(
								String.format("%-" + (maxValueLength + 1) + "s", rs2.getMetaData().getColumnLabel(i)));
					}
					System.out.println();
					while (rs2.next()) {
						for (int i = 1; i <= columns; i++) {
							System.out.print(String.format("%-" + (maxValueLength + 1) + "s", rs2.getString(i)));
						}
						System.out.println();
					}
				}
			} catch (SQLException e) {
				System.out.println("Es ist ein Fehler aufgetreten!");
			}
		}
	}
}
