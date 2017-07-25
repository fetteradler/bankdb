package tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import application.GenerallyUse;
import application.MitarbeiterUse;

/**
 * User navigation through the program. Input via console.
 * 
 * @author caro
 *
 */
public class ConsoleInterface {

	Scanner scanner = new Scanner(System.in);

	/**
	 * Menu to chose what to do next. Login as 'Mitarbeiter', login as 'Kunde'
	 * or to quit the program.
	 */
	public void anmeldung() {

		// Scanner scanner = new Scanner(System.in);

		System.out.println("Was möchten Sie als nächstes tun?");
		System.out.println("1 - Anmeldung Mitarbeiter \n" + "2 - Anmeldung Kunde \n" + "3 - Anmeldung Filialleiter \n"
				+ "0 - Beenden");

		int i = 0;
		while (i == 0) {
			int input = Integer.parseInt(scanner.next());
			switch (input) {
			case 1:
				System.out.println("Anmeldung Mitarbeiter...");
				i = 1;
				System.out.println("Wie möchten Sie als nächstes vorgehen?");
				break;
			case 2:
				System.out.println("Anmeldung Kunde...");
				i = 1;
				System.out.println("Wie möchten Sie als nächstes vorgehen?");
				break;
			case 3:
				System.out.println("Anmeldung Filialleiter...");
				i = 1;
				filialleiterScreen();
				break;
			case 0:
				System.out.println("Das Programm wird beendet...");
				i = 1;
				scanner.close();
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
		scanner.close();
	}

	/**
	 * Menu for 'Filialleiter'. They can chose what they want to do an navigate
	 * via console input.
	 */
	private void filialleiterScreen() {

		System.out.println("Wie möchten Sie als nächstes vorgehen? \n" + "1 - Datenbank neue Initialisieren \n"
				+ "9 - Abmelden \n" + "0 - Beenden");

		Connection con = null;

		int i = 0;
		while (i == 0) {
			int auswahl = Integer.parseInt(scanner.next());
			switch (auswahl) {
			case 1:
				try {
					con = DatabaseInitializer.connectToDatabase();
					DatabaseInitializer.createTables(con);
					TestdataFiller.fillAllTestdata(con);
				} catch (IOException e) {
					System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
					filialleiterScreen();
				} catch (SQLException e) {
					System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
					filialleiterScreen();
				} catch (ParseException e) {
					System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
					filialleiterScreen();
				}
				i = 1;
				break;
			case 9:
				System.out.println("Abmeldung erfolgt...");
				anmeldung();
				i = 1;
				break;
			case 0:
				System.out.println("Das Programm wird beendet...");
				i = 1;
				scanner.close();
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
	}

	/**
	 * Menu for 'Mitarbeiter'. They can chose what they want to do an navigate
	 * via console input.
	 */
	public void mitarbeiterScreen() {

		System.out.println(
				"Wie möchten Sie als nächstes vorgehen? \n" + "1 - Kredit für einen bestehenden Kunden hinzufügen \n"
						+ "2 - Konto für bestehenden Kunden hinzufügen \n" + "3 - Anzeigen eines Kunden \n"
						+ "4 - Neuen Kunden hinzufügen \n" + "5 - Löschen oder bearbeiten eines Kunden \n"
						+ "6 - Anzeigen eines Kontos \n" + "7 - Löschen eines bestehenden Kontos \n" + "9 - Abmelden \n"
						+ "0 - Beenden");

		int i = 0;
		while (i == 0) {
			int auswahl = Integer.parseInt(scanner.next());
			switch (auswahl) {
			case 1:
				addKredit();
				i = 1;
				break;
			case 2:
				System.out.println("Wie möchten Sie als nächstes vorgene? \n" + "1 - Hinzufügen Girokonto\n"
						+ "2 - Hinzufügen Kreditkartenkonto\n" + "3 - Hinzufügen Sparbuch\n" + "9 - Abbrechen\n");
				int j = 0;
				while (j == 0) {
					int auswahlKonto = Integer.parseInt(scanner.next());
					switch (auswahlKonto) {
					case 1:
						addGirokonto();
						j = 1;
						break;
					case 2:
						addKreditkartenkonto();
						j = 1;
						break;
					case 3:
						addSparbuch();
						j = 1;
						break;
					case 9:
						mitarbeiterScreen();
						j = 1;
						break;
					default:
						System.out.println(
								"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
						break;
					}
				}
				i = 1;
				break;
			case 3:
				selectKunde();
				i = 1;
				break;
			case 4:
				try {
					addNewKunde();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				i = 1;
				break;
			case 5:
				System.out.println("1 - Löschen eines Kunden \n" + "2 - Bearbeiten eine Kunden \n");
				int select = Integer.parseInt(scanner.next());
				if(select == 1) {
				deleteKunde();
				} else if(select == 2) {
					editKunde();
				} else {
					System.out.println("Entschulden Sie, es ist ein Fehler unterlaufen. " + select + " ist keine gültige Eingabe.");
					mitarbeiterScreen();
				}
				i = 1;
				break;
			case 6:
				showKonto();
				i = 1;
				break;
			case 7:
				deleteKonto();
				i = 1;
				break;
			case 9:
				System.out.println("Abmeldung erfolgt...");
				anmeldung();
				i = 1;
				break;
			case 0:
				System.out.println("Das Programm wird beendet...");
				i = 1;
				scanner.close();
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
	}
	
	/**
	 * Menu for 'Kunde'. They can chose what they want to do an navigate
	 * via console input.
	 */
	public void kundeScreen() {
		
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Girokonto' to a 'Kunde'.
	 */
	public void addGirokonto() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Girokonto: kundeId, guthaben, gebuehrenProMonat");

		try {
			con = DatabaseInitializer.connectToDatabase();
			String kundeId = scanner.next();
			String guthaben = scanner.next();
			String gebuehrenProMonat = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(guthaben);
			int input3 = Integer.parseInt(gebuehrenProMonat);
			mu.allowGirokonto(con, input1, input2, input3);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addGirokonto();
		}
		System.out.println("Girokonto wurde erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Kredit' to a 'Kunde'.
	 */
	public void addKredit() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Kredit: kundeId, betrag, zinsen, raten, laufzeit(bsp.: 21.07.2019)");

		try {
			con = DatabaseInitializer.connectToDatabase();
			String kundeId = scanner.next();
			String betrag = scanner.next();
			String zinsen = scanner.next();
			String raten = scanner.next();
			String laufzeit = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(betrag);
			int input3 = Integer.parseInt(zinsen);
			int input4 = Integer.parseInt(raten);
			mu.allowKredit(con, input1, input2, input3, input4, laufzeit);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		} catch (ParseException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		}
		System.out.println("Kredit wurde erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Kreditkartenkonto' to a
	 * 'Kunde'.
	 */
	public void addKreditkartenkonto() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Kreditkartenkonto: kundeId, betrag, zinsen");

		try {
			con = DatabaseInitializer.connectToDatabase();
			String kundeId = scanner.next();
			String betrag = scanner.next();
			String zinsen = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(betrag);
			int input3 = Integer.parseInt(zinsen);
			mu.allowKreditkartenkonto(con, input1, input2, input3);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		}
		System.out.println("Kreditkartenkonto wurde erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}

	/**
	 * Enter input for 'Mitarbeiter' to add a new 'Sparbuch' to a 'Kunde'.
	 */
	public void addSparbuch() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Bewillige Sparbuch:");

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("KundeId: ");
			String kundeId = scanner.next();
			System.out.println("Guthaben: ");
			String guthaben = scanner.next();
			System.out.println("Zinsen: ");
			String zinsen = scanner.next();
			int input1 = Integer.parseInt(kundeId);
			int input2 = Integer.parseInt(guthaben);
			int input3 = Integer.parseInt(zinsen);
			mu.allowKreditkartenkonto(con, input1, input2, input3);
		} catch (SQLException e) {
			System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
			addKredit();
		}
		System.out.println("Sparbuch wurde erfolgreich hinzugefügt.");
		mitarbeiterScreen();
	}

	/**
	 * The 'Mitarbeiter' can choose a several 'Kunde' and show the details of
	 * its account. It is possible to search for the 'Kunde' via 'kundeId' or
	 * 'vorname' & 'nachname'.
	 */
	public void selectKunde() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Finde Kunde:");
		System.out.println("Bitte wählen Sie: \n" + "1 - Suche über Kunden ID \n" + "2 - Suche über Name des Kunden ");

		int i = 0;
		while (i == 0) {
			int auswahlSearch = Integer.parseInt(scanner.next());
			switch (auswahlSearch) {
			case 1:
				try {
					con = DatabaseInitializer.connectToDatabase();
					System.out.println("KundeId: ");
					String kundeId = scanner.next();
					int inputId = Integer.parseInt(kundeId);
					String vorname = null;
					String nachname = null;
					mu.findKunde(con, inputId, vorname, nachname);
				} catch (SQLException e) {
					System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
					selectKunde();
				}
				i = 1;
				break;

			case 2:
				try {
					con = DatabaseInitializer.connectToDatabase();
					System.out.println("Vorname: ");
					String vorname = scanner.next();
					System.out.println("Nachname: ");
					String nachname = scanner.next();
					int inputId = 0;
					mu.findKunde(con, inputId, vorname, nachname);
				} catch (SQLException e) {
					System.out.println("Falsche Eingabe: " + e + "\nBitte versuchen Sie es erneut: ");
					selectKunde();
				}
				i = 1;
				break;

			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;

			}
			mitarbeiterScreen();
		}
	}

	/**
	 * The 'Mitarbeiter' can create a new account for 'Kunde'.
	 * 
	 * @throws ParseException
	 *             If converting of the date fails.
	 * @throws IOException
	 *             If the entry is invalid.
	 */
	public void addNewKunde() throws ParseException, IOException {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Füge neuen Kunde hinzu:");

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("Vorname:");
			String vorname = scanner.next();
			System.out.println("Nachname:");
			String nachname = scanner.next();
			System.out.println("Geburtsdatum");
			String geb = scanner.next();
			Date gebDatum = SQLTableParser.convertingDateFormat(geb);
			System.out.println("Telefon:");
			String telNr = scanner.next();
			System.out.println("Mail:");
			String mail = scanner.next();
			System.out.println("Wohnort:");
			String wohnort = scanner.next();
			System.out.println("Strasse:");
			String strasse = scanner.next();
			System.out.println("PLZ:");
			String plz = scanner.next();
			System.out.println("Land:");
			String land = scanner.next();
			System.out.println("Titel:");
			String titelEnum = scanner.next().toUpperCase();
			System.out.println("Aufnahmedatum:");
			String autnahmeDatum = scanner.next();
			Date aufnahme = SQLTableParser.convertingDateFormat(autnahmeDatum);
			System.out.println("Kontostatus:");
			String kontoStatusEnum = scanner.next().toUpperCase();
			System.out.println("Kreditberechtigung:");
			int kreditBerecht = Integer.parseInt(scanner.next());

			mu.includeNewKunde(con, vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titelEnum,
					aufnahme, kontoStatusEnum, kreditBerecht);

			System.out.println("Kunde wurde erfolgreich hinzugefügt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mitarbeiterScreen();
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Kunde' and delete everything of it.
	 */
	public void deleteKunde() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("Löschen eines Kunden:");

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("Bitte geben Sie die ID des zu löschenden Kunden ein:");
			int kundeId = Integer.parseInt(scanner.next());

			mu.deletChosenKunde(con, kundeId);

			System.out.println("Kunde wurde erfolgreich gelöscht.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mitarbeiterScreen();
	}

	/**
	 * The 'Mitarbeiter' can choose a 'Konto' an can delete it.
	 */
	public void deleteKonto() {

		MitarbeiterUse mu = new MitarbeiterUse();
		Connection con = null;
		System.out.println("--- Löschen eines Kontos ---");

		System.out.println("Welche Art Konto möchten Sie entfernen? \n" + "1 - Girokonto \n" + "2 - Kredit \n"
				+ "3 - Kreditkartenkonto \n" + "4 - Sparbuch \n");
		int kontoArt = 0;
		int i = 0;

		while (i == 0) {
			int select = Integer.parseInt(scanner.next());
			if (select == 1 || select == 2 || select == 3 || select == 4) {
				kontoArt = select;
				i = 1;
			} else {
				System.out.println(
						"Falsche Eingabe! " + select + " ist kein gültiges Argument. Bitte versuchen Sie es erneut: ");
			}
		}

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("Bitte geben Sie die ID des zu löschenden Kontos ein:");
			int kundeId = Integer.parseInt(scanner.next());

			mu.deleteChosenKonto(con, kundeId, kontoArt);

			System.out.println("Konto wurde erfolgreich gelöscht.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mitarbeiterScreen();
	}

	/**
	 * User can choose a 'Konto' and can show the details of it. To find the
	 * 'Konto' the user want it need to ente the 'kundeId' of the owner of it.
	 */
	public void showKonto() {

		GenerallyUse gu = new GenerallyUse();
		Connection con = null;
		System.out.println("\n" + "--- Anzeigen eines Kontos ---");

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("Welche art von Konto wollen Sie anzeigen? \n" + "1 - Kredit anzeigen \n"
					+ "2 - Girokonto anzeigen \n" + "3 - Kreditkartenkonto anzeigen \n" + "4 - Sparbuch anzeigen \n"
					+ "9 - Abbrechen \n" + "0 Beenden");
			int i = 0;
			while (i == 0) {
				int auswahl = Integer.parseInt(scanner.next());
				if (auswahl == 1 || auswahl == 2 || auswahl == 3 || auswahl == 4) {
					System.out.println("Bitte geben Sie die Kunden ID ein:");
					int kundeId1 = Integer.parseInt(scanner.next());
					gu.selectKonto(con, kundeId1, auswahl);
					i = 1;
					showKonto();
				} else if (auswahl == 9) {
					System.out.println("Abmeldung erfolgt...");
					i = 1;
					anmeldung();
				} else if (auswahl == 0) {
					System.out.println("Das Programm wird beendet...");
					i = 1;
					scanner.close();
				} else {
					System.out.println(
							"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * User can edit the attributes in database of a chosen 'Kunde'. Finde the 'Kunde' via 'kundeId'.
	 */
	public void editKunde() {

		GenerallyUse gu = new GenerallyUse();
		Connection con = null;
		System.out.println("\n" + "--- Bearbeiten eines Kunden ---");

		try {
			con = DatabaseInitializer.connectToDatabase();
			System.out.println("Was möchen Sie bearbeiten? \n" + "1 - Vorname \n" + "2 - Nachname \n"
					+ "3 - Geburtsdatum\n" + "4 - Telefon \n" + "5 - Mail \n" + "6 - Wohnort \n" + "7 - Strasse \n"
					+ "8 - PLZ \n" + "9 - Land \n" + "10 - Kontostatus \n" + "11 - Kreditberechtigung \n"
					+ "0 - Abbrechen \n");
			int i = 0;
			while (i == 0) {
				int auswahl = Integer.parseInt(scanner.next());
				System.out.println("Bitte geben Sie die ID des Kunden ein: ");
				int kundeId = Integer.parseInt(scanner.next());
				if (auswahl == 1 || auswahl == 2 || auswahl == 3 || auswahl == 4 || auswahl == 5 || auswahl == 6 || auswahl == 7
						|| auswahl == 8 || auswahl == 9 || auswahl == 10 || auswahl == 11) {
					System.out.println("Bitte geben Sie das neue Attribut ein:");
					String entryString = scanner.next();
					i = 1;
					gu.updateKunde(con, kundeId, entryString, auswahl);
				} else if(auswahl == 0) {
					System.out.println("Abmeldung erfolgt...");
					i = 1;
					anmeldung();
				} else {
					System.out.println(
							"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		anmeldung();
	}
}
