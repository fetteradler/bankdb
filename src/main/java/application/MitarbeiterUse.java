package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import tools.ConsoleInterface;
import tools.database.SQLTableParser;

/**
 * Implements the features for 'Mitarbeiter'. Dokumentation! Und versuche deine
 * Klassen aussagekräftig zu benennen. Wenn die Klasse einen Mitarbeiter
 * präsentieren soll, nenne sie Mitarbeiter. Was soll denn der Unterschiedn
 * zwischen MitarbeiterAllow und MitarbeiterUse sein??
 * 
 * @author caro
 *
 */
public class MitarbeiterUse {

	/**
	 * Add a new 'Kredit' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database
	 * @param kundeId
	 *            Id of 'Kunde'
	 * @param betrag
	 *            Sum of 'Kredit'
	 * @param zinsen
	 *            'Zinsen' of 'Kredit'
	 * @param raten
	 *            Monthly rate of 'Kredit'
	 * @param laufzeit
	 *            Term of 'Kredit'
	 * @throws SQLException
	 *             if table dose not exist or looks different then expected
	 * @throws ParseException
	 *             if parse from java.util format to java.sql fails
	 */
	public void allowKredit(Connection con, int kundeId, int betrag, int zinsenProMonat, int ratenProMonat,
			String laufzeit) throws SQLException, ParseException {

		// Create new 'Kredit' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kredit(betrag,zinsen,raten,laufzeit, erstellDatum) VALUES (?,?,?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsenProMonat);
		ps1.setInt(3, ratenProMonat);
		ps1.setDate(4, SQLTableParser.convertingDateFormat(laufzeit));
		ps1.setTimestamp(5, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Kredit' by selecting.
		String select = "SELECT kreditId FROM Kredit WHERE betrag=" + betrag + " AND zinsen=" + zinsenProMonat
				+ " AND raten=" + ratenProMonat + " AND laufzeit='"
				+ (Date) SQLTableParser.convertingDateFormat(laufzeit) + "' AND erstellDatum='"
				+ SQLTableParser.createCurrentTimestamp() + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String kreditIdString = "";
		while (rs.next()) {
			kreditIdString = rs.getString("kreditId");
		}
		int kreditId = Integer.parseInt(kreditIdString);

		// Connect 'Kunde' with 'Kredit' via id of 'Kunde' and 'Kredit'
		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Kredit(kreditId,kundeId) VALUES (?,?)");
		ps2.setInt(1, kreditId);
		ps2.setInt(2, kundeId);
		ps2.executeUpdate();
		con.close();
	}

	/**
	 * Add a new 'Girokonto' to a chosen 'Kunde'
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id for the 'Kunde' who get the new 'Girokonto'.
	 * @param guthaben
	 *            Balance of the 'Girokonto'.
	 * @param gebuehrenProMonat
	 *            Fees per month.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void allowGirokonto(Connection con, int kundeId, int guthaben, int gebuehrenProMonat) throws SQLException {

		System.out.println("1");
		// Create new 'Girokonto' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Girokonto(guthaben, gebuehren, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, gebuehrenProMonat);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		System.out.println("2");

		// Find out id for 'Girokonto' by selecting.
		String select = "SELECT giroId FROM Girokonto WHERE guthaben=" + guthaben + " AND gebuehren="
				+ gebuehrenProMonat + " AND erstellDatum='" + SQLTableParser.createCurrentTimestamp() + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String giroIdString = "";
		while (rs.next()) {
			giroIdString = rs.getString("giroId");
		}
		int giroId = Integer.parseInt(giroIdString);

		System.out.println("3");

		// Connect 'Kunde' with 'Girokonto' via id of 'Kunde' and 'Girokonto'
		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Girokonto(giroId,kundeId) VALUES (?,?)");
		ps2.setInt(1, giroId);
		ps2.setInt(2, kundeId);
		ps2.executeUpdate();

		con.close();
	}

	/**
	 * Add a new 'Kreditkartenkonto' to a chosen 'Kunde'
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id for the 'Kunde' who get the new 'Kreditkartenkonto'
	 * @param betrag
	 *            Sum of 'Kreditkartenkonto', monthly.
	 * @param zinsen
	 *            'Zinsen' of 'Kreditkartenkonto', monthly.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void allowKreditkartenkonto(Connection con, int kundeId, int betrag, int zinsen) throws SQLException {

		// Create new 'Kreditkartenkonto' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kreditkartenkonto(betrag, zinsen, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsen);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Kreditkartenkonto' by selecting.
		String select = "SELECT kreditkarteId FROM Kreditkartenkonto WHERE betrag=" + betrag + " AND zinsen=" + zinsen
				+ " AND erstellDatum='" + SQLTableParser.createCurrentTimestamp() + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String kreditkarteIdString = "";
		while (rs.next()) {
			kreditkarteIdString = rs.getString("kreditkarteId");
		}
		int kreditkarteId = Integer.parseInt(kreditkarteIdString);

		// Connect 'Kunde' with 'Kreditkartenkonto' via id of 'Kunde' and
		// 'Kreditkarte'
		PreparedStatement ps2 = con
				.prepareStatement("INSERT INTO Kunde_Kreditkarte(kreditkarteId,kundeId) VALUES (?,?)");
		ps2.setInt(1, kreditkarteId);
		ps2.setInt(2, kundeId);
		ps2.executeUpdate();

		con.close();
	}

	/**
	 * Add a new 'Sparbuch' to a chosen 'Kunde'
	 * 
	 * @param con
	 *            Connection to databse.
	 * @param kundeId
	 *            Id for the 'Kunde' who get the new 'Sparbuch'.
	 * @param guthaben
	 *            'Guthaben' of the 'Sparbuch'.
	 * @param zinsen
	 *            'Zinsen' of the 'Sparbuch', monthly.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void allowSparbuch(Connection con, int kundeId, int guthaben, int zinsen) throws SQLException {

		// Create new 'Sparbuch' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Sparbuch(guthaben, zinsen, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, zinsen);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Sparbuch' by selecting.
		String select = "SELECT sparId FROM Sparbuch WHERE guthaben=" + guthaben + " AND zinsen=" + zinsen
				+ " AND erstellDatum='" + SQLTableParser.createCurrentTimestamp() + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String sparIdString = "";
		while (rs.next()) {
			sparIdString = rs.getString("sparId");
		}
		int sparId = Integer.parseInt(sparIdString);

		// Connect 'Kunde' with 'Sparbuch' via id of 'Kunde' and 'Sparbuch'
		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Kreditkarte(sparId,kundeId) VALUES (?,?)");
		ps2.setInt(1, sparId);
		ps2.setInt(2, kundeId);
		ps2.executeUpdate();

		con.close();
	}

	/**
	 * Select everything from a chosen 'Kunde' in the database via 'kundeId' or
	 * 'vorname' and 'nachname' of it.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the chosen 'Kunde'.
	 * @param vorname
	 *            'vorname' of the chosen 'Kunde'.
	 * @param nachname
	 *            'nachname' of a chosen 'Kunde'.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void findKunde(Connection con, int kundeId, String vorname, String nachname) throws SQLException {

		String select = "";

		if (kundeId != 0) {
			select = "SELECT * FROM Kunde WHERE kundeId=" + kundeId;
		} else if (!vorname.equals(null) && !nachname.equals(null)) {
			select = "SELECT * FROM Kunde WHERE vorname='" + vorname + "' AND nachname='" + nachname + "'";
		}
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);

		int columns = rs.getMetaData().getColumnCount();
		int maxColumnLength = 1;
		for (int j = 1; j <= columns; j++) {
			if (rs.getMetaData().getColumnLabel(j).length() > maxColumnLength) {
				maxColumnLength = rs.getMetaData().getColumnLabel(j).length();
			}
		}
		int maxValueLength = 1;
		while (rs.next()) {
			for (int i = 1; i <= columns; i++) {
				if (rs.getString(i).length() > maxValueLength) {
					maxValueLength = rs.getString(i).length();
				}
			}
		}

		if (maxValueLength == 1) {
			System.out.println("Ihre Suchanfrage war leider ohne Erfolg. \n");
		} else {

			rs.beforeFirst();
			if (maxColumnLength > maxValueLength) {
				for (int i = 1; i <= columns; i++) {
					System.out.print(
							String.format("%-" + (maxColumnLength + 1) + "s", rs.getMetaData().getColumnLabel(i)));
				}
				System.out.println();
				while (rs.next()) {
					for (int i = 1; i <= columns; i++) {
						System.out.print(String.format("%-" + (maxColumnLength + 1) + "s", rs.getString(i)));
					}
					System.out.println();
				}
			} else {
				for (int i = 1; i <= columns; i++) {
					System.out.print(
							String.format("%-" + (maxValueLength + 1) + "s", rs.getMetaData().getColumnLabel(i)));
				}
				System.out.println();
				while (rs.next()) {
					for (int i = 1; i <= columns; i++) {
						System.out.print(String.format("%-" + (maxValueLength + 1) + "s", rs.getString(i)));
					}
					System.out.println();
				}
			}
		}
		rs.beforeFirst();
	}

	/**
	 * Insert a new 'Kunde' to the database.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param vorname
	 *            'vorname' of the 'Kunde'.
	 * @param nachname
	 *            'nachname' of the 'Kunde'.
	 * @param gebDatum
	 *            'gebDatum' of the 'Kunde'.
	 * @param telNr
	 *            'telNr' of the 'Kunde'.
	 * @param mail
	 *            'mail' of the 'Kunde'.
	 * @param wohnort
	 *            'wohnort' of the 'Kunde'.
	 * @param strasse
	 *            'strasse' of the 'Kunde'.
	 * @param plz
	 *            'plz' of the 'Kunde'.
	 * @param land
	 *            'land' of the 'Kunde'.
	 * @param titel
	 *            'titel' of the 'Kunde'.
	 * @param aufnahmeDatum
	 *            'aufnahmeDatum' of the 'Kunde'.
	 * @param kontoStatus
	 *            'kontoStatus' of the 'Kunde'.
	 * @param kreditBerecht
	 *            'kreditBerecht' of the 'Kunde'.
	 */
	public void includeNewKunde(Connection con, String vorname, String nachname, Date gebDatum, String telNr,
			String mail, String wohnort, String strasse, String plz, String land, String titel, Date aufnahmeDatum,
			String kontoStatus, int kreditBerecht) {

		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Kunde(vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, aufnahmeDatum, kontoStatus, kreditBerecht) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setDate(3, gebDatum);
			ps.setString(4, telNr);
			ps.setString(5, mail);
			ps.setString(6, wohnort);
			ps.setString(7, strasse);
			ps.setString(8, plz);
			ps.setString(9, land);
			ps.setString(10, titel.toString());
			ps.setDate(11, aufnahmeDatum);
			ps.setString(12, kontoStatus.toString());
			if (kreditBerecht == 0)
				ps.setBoolean(13, false);
			else if (kreditBerecht == 1)
				ps.setBoolean(13, true);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Fehler beim hinzufügen: " + e + "\n");
			ConsoleInterface ci = new ConsoleInterface();
			ci.mitarbeiterScreen();
		}
	}

	/**
	 * Delete a chosen 'Kunde' from the database. Select this 'Kunde' via
	 * 'kundeId'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde'.
	 */
	public void deletChosenKunde(Connection con, int kundeId) {

		try {
			String delete = "DELETE FROM Kunde WHERE kundeId=" + kundeId; // TODO
																			// Prepared
																			// Statement
			Statement stmnt = con.createStatement();
			stmnt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a chosen 'Konto' from the database. Select this via 'kontoid'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kontoId
	 *            Id of the 'Konto'.
	 * @param kontoArt
	 *            Kind of 'Konto'.
	 */
	public void deleteChosenKonto(Connection con, int kontoId, int kontoArt) {

		if (kontoArt == 1) {

			try {
				String delete = "DELETE FROM Girokonto WHERE giroId=" + kontoId;
				Statement stmnt = con.createStatement();
				stmnt.executeUpdate(delete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (kontoArt == 2) {

			try {
				String delete = "DELETE FROM Kredit WHERE kreditId=" + kontoId;
				Statement stmnt = con.createStatement();
				stmnt.executeUpdate(delete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (kontoArt == 3) {

			try {
				String delete = "DELETE FROM Kreditkartenkonto WHERE kreditkarteId=" + kontoId;
				Statement stmnt = con.createStatement();
				stmnt.executeUpdate(delete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (kontoArt == 4) {

			try {
				String delete = "DELETE FROM Sparbuch WHERE sparIdId=" + kontoId;
				Statement stmnt = con.createStatement();
				stmnt.executeUpdate(delete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
