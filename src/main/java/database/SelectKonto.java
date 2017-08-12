package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.tools.DatabaseConnectionSingleton;
import gui.AllUserInterface;

/**
 * Select all 'Konto' of a chosen type for a selected 'Kunde'.
 * 
 * @author CM
 *
 */
public class SelectKonto {

	/**
	 * Choose the type of the 'Konto' that want to select.
	 * 
	 * @param kundeId
	 *            Id of the 'Kunde' of the 'Konto'.
	 * @param type
	 *            Type of the 'Konto' ('Kredit', 'Girokonto',
	 *            'Kreditkartenkonto', 'Sparbuch')
	 * @throws SQLException
	 *             If selection fails.
	 */
	public void kontoType(int kundeId, int type) throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		String selectAttribute = null;

		// 1- Kredit, 2- Girokonto, 3- Kreditkartenkonto, 4- Sparbuch
		if (type == 1) {
			selectAttribute = "kreditId";
			String select1 = "SELECT kreditId FROM Kunde_Kredit WHERE kundeId=?";
			String select2 = "SELECT * FROM Kredit WHERE kreditId=?";
			selectChoosenKonto(con, kundeId, select1, select2, selectAttribute);
		} else if (type == 2) {
			selectAttribute = "giroId";
			String select1 = "SELECT giroIdId FROM Kunde_Girokonto WHERE kundeId=?";
			String select2 = "SELECT * FROM Girokonto WHERE giroId=?";
			selectChoosenKonto(con, kundeId, select1, select2, selectAttribute);
		} else if (type == 3) {
			selectAttribute = "kreditkarteId";
			String select1 = "SELECT kreditkarteId FROM Kunde_Kreditkarte WHERE kundeId=?";
			String select2 = "SELECT * FROM Kreditkartenkonto WHERE kreditkarteId=?";
			selectChoosenKonto(con, kundeId, select1, select2, selectAttribute);
		} else if (type == 4) {
			selectAttribute = "sparId";
			String select1 = "SELECT sparId FROM Kunde_Sparbuch WHERE kundeId=?";
			String select2 = "SELECT * FROM Sparbuch WHERE sparId=?";
			selectChoosenKonto(con, kundeId, select1, select2, selectAttribute);
		}
	}

	/**
	 * Find all 'Konto' for the chosen type and 'Kunde'.
	 * 
	 * @param con
	 *            Connection to databse.
	 * @param kontoId
	 *            Id of the 'Kunde' of the 'Konto'.
	 * @param select1
	 *            Select statement to find the id of the 'Konto'.
	 * @param select2
	 *            Select statement to find all attribute of the 'Konto'.
	 * @param selectAttribute
	 *            Attribute of the Id of the 'Konto'.
	 * @throws SQLException
	 *             If selection fails.
	 */
	public void selectChoosenKonto(Connection con, int kontoId, String select1, String select2, String selectAttribute)
			throws SQLException {

		// Select all kontoId for choosen 'Kunde' and choosen type of 'Konto'
		PreparedStatement selectKontoId = con.prepareStatement(select1);
		selectKontoId.setInt(1, kontoId);

		ResultSet rs1 = selectKontoId.executeQuery();
		if (rs1.next()) {
			rs1.beforeFirst();
			while (rs1.next()) {
				// For all kontoIds get all informations about the 'Konto'
				int kontoId1 = rs1.getInt(selectAttribute);
				PreparedStatement selectAllKonto = con.prepareStatement(select2);
				selectAllKonto.setInt(1, kontoId1);
				ResultSet rs2 = selectAllKonto.executeQuery();

				// To all attributes give the column name as well
				int columns = rs2.getMetaData().getColumnCount();
				int maxColumnLength = 1;
				for (int j = 1; j <= columns; j++) {
					if (rs2.getMetaData().getColumnLabel(j).length() > maxColumnLength) {
						maxColumnLength = rs2.getMetaData().getColumnLabel(j).length();
					}
				}
				int maxValueLength = 1;
				while (rs2.next()) {
					for (int i = 1; i <= columns; i++) {
						if (rs2.getString(i).length() > maxValueLength) {
							maxValueLength = rs2.getString(i).length();
						}
					}
				}
				AllUserInterface.outputColumnNamesAndAttributes(maxValueLength, maxColumnLength, rs2, columns);
				rs2.beforeFirst();
			}
		}
	}
}
