package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.tools.DatabaseConnectionSingleton;

/**
 * Database queries to insert or delete a 'Mitarbeiter'.
 * 
 * @author CM
 *
 */
public class InsertOrDeleteMitarbeiter {

	/**
	 * Insert a new 'Mitarbeiter' to the database.
	 * 
	 * @param vorname
	 *            'vorname' of the 'Mitarbeiter'.
	 * @param nachname
	 *            'nachname' of the 'Mitarbeiter'.
	 * @param gebDatum
	 *            'gebDatum' of the 'Mitarbeiter'.
	 * @param telNr
	 *            'telNr' of the 'Mitarbeiter'.
	 * @param mail
	 *            'mail' of the 'Mitarbeiter'.
	 * @param wohnort
	 *            'wohnort' of the 'Mitarbeiter'.
	 * @param strasse
	 *            'strasse' of the 'Mitarbeiter'.
	 * @param plz
	 *            'plz' of the 'Mitarbeiter'.
	 * @param land
	 *            'land' of the 'Mitarbeiter'.
	 * @param titel
	 *            'titel' of the 'Mitarbeiter'.
	 * @param einstellDatum
	 *            'einstellDatum' of the 'Mitarbeiter'.
	 * @param monatsLohn
	 *            'monatsLohn' of the 'Mitarbeiter'.
	 * @param fristDatum
	 *            'fristDatum' of the 'Mitarbeiter'.
	 * @param status
	 *            'status' of the 'Mitarbeiter'.
	 * @param anstellung
	 *            'anstellung' of the 'Mitarbeiter'.
	 * 
	 * @throws SQLException
	 *             if table dose not exist or looks different then expected.
	 */
	public void includeNewMitarbeiter(String vorname, String nachname, Date gebDatum, String telNr, String mail,
			String wohnort, String strasse, String plz, String land, String titel, Date einstellDatum, int monatsLohn,
			Date fristDatum, String status, String anstellung) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO Angestellter(vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, einstellDatum, monatsLohn, fristDatum, status, anstellung) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
		ps.setDate(11, einstellDatum);
		ps.setInt(12, monatsLohn);
		ps.setDate(13, fristDatum);
		ps.setString(14, status.toString());
		ps.setString(15, anstellung);

		ps.executeUpdate();
	}

	/**
	 * Delete a chosen 'Mitarbeiter' from the database. Select this 'Mitarbeiter' via
	 * 'angestelltId'.
	 * 
	 * @param angestelltId
	 *            Id of the 'Mitarbeiter'.
	 * @throws SQLException
	 *             if table dose not exist or looks different then expected.
	 */
	public void deletChosenMitarbeiter(int angestelltId) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		PreparedStatement delete = con.prepareStatement("DELETE FROM Angestellter WHERE angestlltId=?");
		delete.setInt(1, angestelltId);
		delete.executeUpdate();
	}
}
