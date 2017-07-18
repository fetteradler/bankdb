package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import tools.SQLTableParser;

public class MitarbeiterAllow {

	/**
	 * Add a new 'Kredit' for a choosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database
	 * @param kundeId
	 *            Id of 'Kunde'
	 * @param betrag
	 *            Sum of credit
	 * @param zinsen
	 *            'Zinsen' of credit
	 * @param raten
	 *            Monthly rate of credit
	 * @param laufzeit
	 *            Term of credit
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void allowKredit(Connection con, int kundeId, int betrag, int zinsen, int raten, String laufzeit)
			throws SQLException, ParseException {

		SQLTableParser tp = new SQLTableParser();

		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kredit(betrag,zinsen,raten,laufzeit) VALUES (?,?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsen);
		ps1.setInt(3, raten);
		ps1.setDate(4, (Date) tp.convertingDateFormat(laufzeit));

		ps1.executeUpdate();

		String select = "SELECT kreditId FROM Kredit WHERE betrag=" + betrag + " AND zinsen=" + zinsen + " AND raten="
				+ raten + " AND laufzeit='" + (Date) tp.convertingDateFormat(laufzeit) + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String kredit = "";

		while (rs.next()) {
			kredit = rs.getString("kreditId");
		}

		int kreditId = Integer.parseInt(kredit);

		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Kredit(kreditId,kundeId) VALUES (?,?)");
		ps2.setInt(1, kreditId);
		ps2.setInt(2, kundeId);

		ps2.executeUpdate();

		con.close();
	}
	
	public void allowGirokonto(Connection con, int kundeId, int guthaben, int gebueren) throws SQLException {
		

		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Girokonto(guthaben, gebueren) VALUES (?,?)");
		ps1.setInt(1, guthaben);
		ps1.setInt(2, gebueren);

		ps1.executeUpdate();
		
		String select = "SELECT giroId FROM Girokonto WHERE guthaben=" + guthaben + " AND gebueren=" + gebueren;

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		String giro = "";

		while (rs.next()) {
			giro = rs.getString("giroId");
		}

		int giroId = Integer.parseInt(giro);
		
		PreparedStatement ps2 = con.prepareStatement("INSERT INTO Kunde_Girokonto(giroId,kundeId) VALUES (?,?)");
		ps2.setInt(1, giroId);
		ps2.setInt(2, kundeId);

		ps2.executeUpdate();

		con.close();
	}
}
