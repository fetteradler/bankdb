package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.tools.DatabaseConnectionSingleton;

/**
 * Insert or delete a chosen 'Kunde' to/from the database.
 * 
 * @author CM
 *
 */
public class InsertOrDeleteKunde {

	/**
	 * Delete a chosen 'Kunde' from the database. Select this 'Kunde' via
	 * 'kundeId'.
	 * 
	 * @param kundeId
	 *            Id of the 'Kunde'.
	 * @throws SQLException
	 *             If deleting of 'Kunde' faild.
	 */
	public void deletChosenKunde(int kundeId) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		PreparedStatement delete = con.prepareStatement("DELETE FROM Kunde WHERE kundeId=?");
		delete.setInt(1, kundeId);
		delete.executeUpdate();
	}

	/**
	 * Insert a new 'Kunde' to the database.
	 * 
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
	 * 
	 * @throws SQLException
	 *             if table dose not exist or looks different then expected.
	 */
	public void includeNewKunde(String vorname, String nachname, Date gebDatum, String telNr, String mail,
			String wohnort, String strasse, String plz, String land, String titel, Date aufnahmeDatum,
			String kontoStatus, int kreditBerecht) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

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
	}
}
