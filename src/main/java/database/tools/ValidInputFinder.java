package database.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Find all Id's of a chosen table in database. Table will be selected by a
 * 'roleId', to select the searching attribute, and a 'roleStatement', to select
 * the SQL Statement.
 * 
 * @author CM
 *
 */
public class ValidInputFinder {

	/**
	 * Search for all Id's of a selected table in databse.
	 * 
	 * @param roleId
	 *            Name of the searching attribute.
	 * @param roleStatement
	 *            Select statement.
	 * @return All Id's from chosen table in database.
	 */
	public static ArrayList<Integer> findAllIdInDatabase(String roleId, String roleStatement) {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		ArrayList<Integer> allId = new ArrayList<Integer>();

		try {
			PreparedStatement ps = con.prepareStatement(roleStatement);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allId.add(rs.getInt(roleId));
			}
		} catch (SQLException e) {
			System.err.println("Es konnten keine passenden Einträge gefunden werden! ");
		}
		return allId;
	}

	/**
	 * Search for all Id's of 'Mitarbeiter'.
	 * 
	 * @return List of all Id's.
	 */
	public static ArrayList<Integer> findAllMitarbeiterId() {

		String roleId = "angestelltId";
		String roleStatement = "SELECT angestelltId FROM Angestellter";
		ArrayList<Integer> allMitarbeiterId = findAllIdInDatabase(roleId, roleStatement);
		return allMitarbeiterId;
	}

	/**
	 * Search for all Id's of 'Kunde'.
	 * 
	 * @return List of all Id's.
	 */
	public static ArrayList<Integer> findAllKundeId() {

		String roleId = "kundeId";
		String roleStatement = "SELECT kundeId FROM Kunde";
		ArrayList<Integer> allKundeId = findAllIdInDatabase(roleId, roleStatement);

		return allKundeId;
	}

	/**
	 * Search for all Id's of 'Filialleiter'.
	 * 
	 * @return List of all Id's.
	 */
	public static ArrayList<Integer> findAllFilialleiterId() {

		String roleId = "leiterId";
		String roleStatement = "SELECT leiterId FROM Filialleiter";
		ArrayList<Integer> allFilialleiterId = findAllIdInDatabase(roleId, roleStatement);

		return allFilialleiterId;
	}
}
