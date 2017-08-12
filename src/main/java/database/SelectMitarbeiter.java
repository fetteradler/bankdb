package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.tools.DatabaseConnectionSingleton;
import gui.AllUserInterface;

/**
 * Database queries to select a 'Mitarbeiter' in different ways or select all
 * 'Mitarbeiter'.
 * 
 * @author CM
 *
 */
public class SelectMitarbeiter {

	/**
	 * Select everything from a chosen 'Mitarbeiter' in the database via
	 * 'kundeId' of it.
	 * 
	 * @param angestelltId
	 *            Id of the chosen 'Mitarbeiter'.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void selectMitarbeiterViaId(int angestelltId) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		PreparedStatement ps = con.prepareStatement("SELECT * FROM Angestellter WHERE angestelltId=?");
		ps.setInt(1, angestelltId);

		ResultSet rs = ps.executeQuery();

		// Count maximum size of column labels and entries
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
				System.out.println(rs.getString(i));
				if (rs.getString(i) != null) {
					if (rs.getString(i).length() > maxValueLength) {
						maxValueLength = rs.getString(i).length();
					}
				}
			}
		}

		AllUserInterface.outputColumnNamesAndAttributes(maxValueLength, maxColumnLength, rs, columns);

		rs.beforeFirst();
	}

	/**
	 * Select everything from a chosen 'Mitarbeiter' in the database via
	 * 'vorname' and 'nachname' of it.
	 * 
	 * @param vorname
	 *            'vorname' of the chosen 'Mitarbeiter'.
	 * @param nachname
	 *            'nachname' of a chosen 'Mitarbeiter'.
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void selectMitarbeiterViaName(String vorname, String nachname) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		PreparedStatement ps = con.prepareStatement("SELECT * FROM Angestellter WHERE vorname=? AND nachname=?");
		ps.setString(1, vorname);
		ps.setString(2, nachname);

		ResultSet rs = ps.executeQuery();

		// Count maximum size of column labels and entries
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
				if (rs.getString(i) != null) {
					if (rs.getString(i).length() > maxValueLength) {
						maxValueLength = rs.getString(i).length();
					}
				}
			}
		}

		AllUserInterface.outputColumnNamesAndAttributes(maxValueLength, maxColumnLength, rs, columns);
		rs.beforeFirst();
	}

	/**
	 * Select everything from all 'Mitarbeiter' from the database.
	 * 
	 * @throws SQLException
	 *             If table dose not exist or looks different then expected.
	 */
	public void selectAllMitarbeiter() throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		String selectAll = "SELECT * FROM Angestellter";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectAll);

		// Count maximum size of column labels and entries
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
				if (rs.getString(i) != null) {
					if (rs.getString(i).length() > maxValueLength) {
						maxValueLength = rs.getString(i).length();
					}
				}
			}
		}

		AllUserInterface.outputColumnNamesAndAttributes(maxValueLength, maxColumnLength, rs, columns);
		rs.beforeFirst();
	}
}
