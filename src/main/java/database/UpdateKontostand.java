package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.tools.DatabaseConnectionSingleton;

/**
 * Update the sum of money to a chosen 'Konto'. User can add or discount an
 * amount.
 * 
 * @author CM
 *
 */
public class UpdateKontostand {

	/**
	 * Update the amount of a chosen 'Konto'. Select current sum and discount or
	 * add it with another sum the user chose. By discounting check if the entry
	 * is not bigger than the current amount at the 'Konto'.
	 * 
	 * @param kontoType
	 *            Type of the 'Konto' ('Kredit', 'Girokonto',
	 *            'Kreditkartenkonto', 'Sparbuch').
	 * @param kundeId
	 *            Id of the 'Kunde' of the 'Konto'.
	 * @param kontoId
	 *            Id of th chosen 'Konto'.
	 * @param sum
	 *            Sum to update the database.
	 * @param choseOperation
	 *            select if the 'sum' is added or discounted.
	 * @return true if the updating was successful.
	 * @throws SQLException
	 *             If the entry is invalid.
	 */
	public boolean addAndSubtractMoney(int kontoType, int kundeId, int kontoId, int sum, boolean choseOperation)
			throws SQLException {

		Connection con = null;
		try {
			con = DatabaseConnectionSingleton.getInstance().getDbConnection();
		} catch (SQLException e1) {
			System.err.println("DB Verbindung konnte nicht aufgebaut werden!");
			System.exit(1);
		}

		String selectAttribute = null;
		String select = null;
		String update = null;

		// 1- Kredit, 2- Girokonto, 3- Kreditkartenkonto, 4- Sparbuch
		if (kontoType == 1) {
			select = "SELECT betrag FROM Kredit WHERE kreditId=?";
			selectAttribute = "betrag";
			update = "UPDATE Kredit SET betrag=? WHERE kreditId= ?";
		} else if (kontoType == 2) {
			select = "SELECT guthaben FROM Girokonto WHERE giroId=?";
			selectAttribute = "guthaben";
			update = "UPDATE Girokonto SET guthaben=? WHERE giroId= ?";
		} else if (kontoType == 3) {
			select = "SELECT betrag FROM Kreditkartenkonto WHERE kreditkarteId=?";
			selectAttribute = "betrag";
			update = "UPDATE KreditkartenKonto SET betrag=? WHERE kredtitkarteId= ?";
		} else if (kontoType == 4) {
			select = "SELECT guthaben FROM Sparbuch WHERE sparId=?";
			selectAttribute = "guthaben";
			update = "UPDATE Sparbuch SET guthaben=? WHERE sparId= ?";
		}

		PreparedStatement ps1 = con.prepareStatement(select);
		ps1.setInt(1, kontoId);

		ResultSet rs = ps1.executeQuery();
		int currentSum = 0;
		while (rs.next()) {
			currentSum = rs.getInt(selectAttribute);
		}

		int newSum = 0;
		if (choseOperation) {
			if ((currentSum - sum) >= 0) {

				newSum = currentSum - sum;
				PreparedStatement ps2 = con.prepareStatement(update);
				ps2.setInt(1, newSum);
				ps2.setInt(2, kontoId);
				ps2.executeUpdate();

				return true;
			} else {
				return false;
			}
		} else {
			newSum = currentSum + sum;
			PreparedStatement ps2 = con.prepareStatement(update);
			ps2.setInt(1, newSum);
			ps2.setInt(2, kontoId);
			ps2.executeUpdate();

			return true;
		}
	}
}
