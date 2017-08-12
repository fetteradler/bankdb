package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import database.tools.DatabaseConnectionSingleton;
import database.tools.SQLTableParser;

/**
 * Insert a new 'Konto' to a 'Kunde'. Delete a chosen 'Kunde' from the databse.
 * 
 * @author CM
 *
 */
public class InsertOrDeleteKonto {

	/**
	 * Add a new 'Kredit' for a chosen 'Kunde'.
	 * 
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
	public void allowKredit(int kundeId, int betrag, int zinsenProMonat, int ratenProMonat, String laufzeit)
			throws SQLException, ParseException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

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
		PreparedStatement select = con.prepareStatement(
				"SELECT kreditId FROM Kredit WHERE betrag=? AND zinsen=?  AND raten=? AND laufzeit=? AND erstellDatum=?");
		select.setInt(1, betrag);
		select.setInt(2, zinsenProMonat);
		select.setInt(3, ratenProMonat);
		select.setDate(4, (Date) SQLTableParser.convertingDateFormat(laufzeit));
		select.setTimestamp(5, SQLTableParser.createCurrentTimestamp());
		ResultSet rs = select.executeQuery();

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
	public void allowGirokonto(int kundeId, int guthaben, int gebuehrenProMonat) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		// Create new 'Girokonto' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Girokonto(guthaben, gebuehren, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, gebuehrenProMonat);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Girokonto' by selecting.
		PreparedStatement select = con
				.prepareStatement("SELECT giroId FROM Girokonto WHERE guthaben=? AND gebuehren=?  AND erstellDatum=?");
		select.setInt(1, guthaben);
		select.setInt(2, gebuehrenProMonat);
		select.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ResultSet rs = select.executeQuery();

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
	public void allowKreditkartenkonto(int kundeId, int betrag, int zinsen) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		// Create new 'Kreditkartenkonto' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kreditkartenkonto(betrag, zinsen, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsen);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Kreditkartenkonto' by selecting.
		PreparedStatement select = con.prepareStatement(
				"SELECT kreditkarteId FROM Kreditkartenkonto WHERE betrag=? AND zinsen=?  AND erstellDatum=?");
		select.setInt(1, betrag);
		select.setInt(2, zinsen);
		select.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ResultSet rs = select.executeQuery();

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
	public void allowSparbuch(int kundeId, int guthaben, int zinsen) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		// Create new 'Sparbuch' with given attributes.
		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Sparbuch(guthaben, zinsen, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, zinsen);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ps1.executeUpdate();

		// Find out id for 'Sparbuch' by selecting.
		PreparedStatement select = con
				.prepareStatement("SELECT sparId FROM Sparbuch WHERE guthaben=? AND zinsen=?  AND erstellDatum=?");
		select.setInt(1, guthaben);
		select.setInt(2, zinsen);
		select.setTimestamp(3, SQLTableParser.createCurrentTimestamp());
		ResultSet rs = select.executeQuery();

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
	}

	/**
	 * Delete a chosen 'Konto' from the database. Select this via 'kontoid'.
	 * 
	 * @param kontoId
	 *            Id of the 'Konto'.
	 * @param kontoArt
	 *            Kind of 'Konto'.
	 * 
	 * @throws SQLException
	 *             if deleting of the 'Konto' fails.
	 */
	public void deleteChosenKonto(int kontoId, int kontoArt) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		if (kontoArt == 1) {
			PreparedStatement delete = con.prepareStatement("DELETE FROM Girokonto WHERE giroId=?");
			delete.setInt(1, kontoId);
			delete.executeUpdate();
		} else if (kontoArt == 2) {
			PreparedStatement delete = con.prepareStatement("DELETE FROM Kredit WHERE kreditId=?");
			delete.setInt(1, kontoId);
			delete.executeUpdate();
		} else if (kontoArt == 3) {
			PreparedStatement delete = con.prepareStatement("DELETE FROM Kreditkartenkonto WHERE kreditkarteId=?");
			delete.setInt(1, kontoId);
			delete.executeUpdate();
		} else if (kontoArt == 4) {
			PreparedStatement delete = con.prepareStatement("DELETE FROM Sparbuch WHERE sparIdId=?");
			delete.setInt(1, kontoId);
			delete.executeUpdate();
		}
	}
}
