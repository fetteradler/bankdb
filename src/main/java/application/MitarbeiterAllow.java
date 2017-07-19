package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import tools.SQLTableParser;

// Dokumentation!!
public class MitarbeiterAllow {

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
	 * @throws SQLException Dokumentation!
	 * @throws ParseException Dokumentation!
	 */
	public void allowKredit(Connection con, int kundeId, int betrag, int zinsen, int raten, String laufzeit)
			throws SQLException, ParseException {

		// 1. Klasse SQLTableParser kann wieder abstract sein.
		// 2. Benennung der Variablen nicht aussagekräftig. Lieber längere Variablen,
		// als dass man nach 3 Zeilen keine Ahnung mehr hat wofür es steht.

		PreparedStatement ps1 = con
				.prepareStatement("INSERT INTO Kredit(betrag,zinsen,raten,laufzeit) VALUES (?,?,?,?)");
		ps1.setInt(1, betrag);
		ps1.setInt(2, zinsen);
		ps1.setInt(3, raten);
		ps1.setDate(4, (Date) SQLTableParser.convertingDateFormat(laufzeit)); //Gebe im SQLTableParser
		// gleich die richtige Date Klasse zurück statt hier nochmal zu casten.

		ps1.executeUpdate();

		String select = "SELECT kreditId FROM Kredit WHERE betrag=" + betrag + " AND zinsen=" + zinsen + " AND raten="
				+ raten + " AND laufzeit='" + (Date) SQLTableParser.convertingDateFormat(laufzeit) + "'"; //s.o.

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

	// Gebühren nicht Gebüren. Methode wird nicht verwendet.
}