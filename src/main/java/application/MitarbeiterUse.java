package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import tools.SQLTableParser;

/**
 * Implements the features for 'Mitarbeiter'
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

		// SQLTableParser tp = new SQLTableParser();

		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kredit(betrag,zinsen,raten,laufzeit, erstellDatum) VALUES (?,?,?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsenProMonat);
		ps1.setInt(3, ratenProMonat);
		ps1.setDate(4, SQLTableParser.convertingDateFormat(laufzeit));
		ps1.setTimestamp(5, SQLTableParser.createCurrentTimestamp());

		ps1.executeUpdate();

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

		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Girokonto(guthaben, gebuehren, erstellDatum) VALUES (?,?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, gebuehrenProMonat);
		ps1.setTimestamp(3, SQLTableParser.createCurrentTimestamp());

		ps1.executeUpdate();

		String select = "SELECT giroId FROM Girokonto WHERE guthaben=" + guthaben + " AND gebuehren="
				+ gebuehrenProMonat + " AND erstellDatum='" + SQLTableParser.createCurrentTimestamp() + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String giroIdString = "";

		while (rs.next()) {
			giroIdString = rs.getString("giroId");
		}

		int giroId = Integer.parseInt(giroIdString);

		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Girokonto(giroId,kundeId) VALUES (?,?)");
		ps2.setInt(1, giroId);
		ps2.setInt(2, kundeId);

		ps2.executeUpdate();

		con.close();
	}

}
