package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import database.tools.DatabaseConnectionSingleton;
import database.tools.SQLTableParser;

public class UpdateKunde {

	public void updateKundeAttribute(int kundeId, String attribute, int attributeArt) throws SQLException, ParseException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		if (attributeArt == 1) {
			updateVorname(con, kundeId, attribute);
		} else if (attributeArt == 2) {
			updateNachname(con, kundeId, attribute);
		} else if (attributeArt == 3) {
			updateGeburtsdatum(con, kundeId, attribute);
		} else if (attributeArt == 4) {
			updateTelephone(con, kundeId, attribute);
		} else if (attributeArt == 5) {
			updateMail(con, kundeId, attribute);
		} else if (attributeArt == 6) {
			updateWohnort(con, kundeId, attribute);
		} else if (attributeArt == 7) {
			updateStrasse(con, kundeId, attribute);
		} else if (attributeArt == 8) {
			updatePLZ(con, kundeId, attribute);
		} else if (attributeArt == 9) {
			updateLand(con, kundeId, attribute);
		} else if (attributeArt == 10) {
			updateKontoStatus(con, kundeId, attribute);
		} else if (attributeArt == 11) {
			updateKreditBerechtigt(con, kundeId, attribute);
		}
	}

	public void updateVorname(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET vorname=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateNachname(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET nachname=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateGeburtsdatum(Connection con, int kundeId, String attribute) throws SQLException, ParseException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET gebDatum=? WHERE kundeId= ?");
		Date entryDate = SQLTableParser.convertingDateFormat(attribute);
		update.setDate(1, entryDate);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateTelephone(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET telNr=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateMail(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET mail=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateWohnort(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET wohnort=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateStrasse(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET strasse=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updatePLZ(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET plz=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateLand(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET land=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateKontoStatus(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET kontoStatus=? WHERE kundeId= ?");
		if (attribute.equals("0")) {
			update.setBoolean(1, false);
			update.executeUpdate();
		} else if (attribute.equals("1")) {
			update.setBoolean(1, true);
			update.executeUpdate();
		}
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	public void updateKreditBerechtigt(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET kreditBerecht=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}
}
