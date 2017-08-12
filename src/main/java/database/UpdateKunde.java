package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import database.tools.DatabaseConnectionSingleton;
import database.tools.SQLTableParser;

/**
 * Update attributes of a 'Kunde'. Selected 'Kunde' via 'kundeId' and chose the
 * attribute to be updated.
 * 
 * @author CM
 *
 */
public class UpdateKunde {

	/**
	 * Select a 'Kunde' via 'kundeId' an update a chosen attribute of it.
	 * 
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @param attributeArt
	 *            Type of the attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 * @throws ParseException
	 *             If the parsing from the entry String to sql.Date fails.
	 */
	public void updateKundeAttribute(int kundeId, String attribute, int attributeArt)
			throws SQLException, ParseException {

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

	/**
	 * Update the attribute 'vorname' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateVorname(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET vorname=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'nachname' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateNachname(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET nachname=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'gebDatum' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 * @throws ParseException
	 *             If the parsing from the entry String to sql.Date fails.
	 */
	public void updateGeburtsdatum(Connection con, int kundeId, String attribute) throws SQLException, ParseException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET gebDatum=? WHERE kundeId= ?");
		Date entryDate = SQLTableParser.convertingDateFormat(attribute);
		update.setDate(1, entryDate);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'telNr' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateTelephone(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET telNr=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'mail' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateMail(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET mail=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'wohnort' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateWohnort(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET wohnort=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'strasse' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateStrasse(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET strasse=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'plz' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updatePLZ(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET plz=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'land' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateLand(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET land=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'kreditBerecht' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateKreditBerechtigt(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET kreditBerecht=? WHERE kundeId= ?");
		if (attribute.equals("0")) {
			update.setBoolean(1, false);
		} else if (attribute.equals("1")) {
			update.setBoolean(1, true);
		}
		update.setInt(2, kundeId);
		update.executeUpdate();
	}

	/**
	 * Update the attribute 'kontoStatus' for a chosen 'Kunde'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde' to be updated.
	 * @param attribute
	 *            New attribute to be updated.
	 * @throws SQLException
	 *             If updating the database failed. The entry is invalid.
	 */
	public void updateKontoStatus(Connection con, int kundeId, String attribute) throws SQLException {

		PreparedStatement update = con.prepareStatement("UPDATE Kunde SET kontoStatus=? WHERE kundeId= ?");
		update.setString(1, attribute);
		update.setInt(2, kundeId);
		update.executeUpdate();
	}
}
