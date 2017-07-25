package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import tools.SQLTableParser;
import tools.EnumParser.kontoStatus;

/**
 * Implements generally features for all users.
 * 
 * @author caro
 *
 */
public class GenerallyUse {

	/**
	 * Select everything from a chosen 'Konto' in the database via 'kundeId'.
	 * 
	 * @param con
	 *            Connection to database.
	 * @param kundeId
	 *            Id of the 'Kunde'.
	 * @param kontoArt
	 *            Kind of 'Konto'.
	 */
	public void selectKonto(Connection con, int kundeId, int kontoArt) {

		String selectAttribute = null;
		String columnNameKundeKonto = null;
		String columnNameKonto = null;

		// 1- Kredit, 2- Girokonto, 3- Kreditkartenkonto, 4- Sparbuch
		if (kontoArt == 1) {
			selectAttribute = "kreditId";
			columnNameKundeKonto = "Kunde_Kredit";
			columnNameKonto = "Kredit";
		} else if (kontoArt == 2) {
			selectAttribute = "giroId";
			columnNameKundeKonto = "Kunde_Girokonto";
			columnNameKonto = "Girokonto";
		} else if (kontoArt == 3) {
			selectAttribute = "kreditkarteId";
			columnNameKundeKonto = "Kunde_Kreditkarte";
			columnNameKonto = "Kreditkartenkonto";
		} else if (kontoArt == 4) {
			selectAttribute = "sparId";
			columnNameKundeKonto = "Kunde_Sparbuch";
			columnNameKonto = "Sparbuch";
		}

		String selectKontoId = "SELECT " + selectAttribute + " FROM " + columnNameKundeKonto + " WHERE kundeId="
				+ kundeId;
		try {
			Statement stmnt1 = con.createStatement();
			ResultSet rs1 = stmnt1.executeQuery(selectKontoId);
			if (rs1.next()) {
				rs1.beforeFirst();
				while (rs1.next()) {
					int kontoId1 = rs1.getInt(selectAttribute);
					String selectAllKonto = "SELECT * FROM " + columnNameKonto + " WHERE " + selectAttribute + "="
							+ kontoId1;
					Statement stmnt2 = con.createStatement();
					ResultSet rs2 = stmnt2.executeQuery(selectAllKonto);

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

					if (maxValueLength == 1) {
						System.out.println("Ihre Suchanfrage ergab leider kein Erfolg. \n");
					} else {

						rs2.beforeFirst();
						if (maxColumnLength > maxValueLength) {
							for (int i = 1; i <= columns; i++) {
								System.out.print(String.format("%-" + (maxColumnLength + 1) + "s",
										rs2.getMetaData().getColumnLabel(i)));
							}
							System.out.println();
							while (rs2.next()) {
								for (int i = 1; i <= columns; i++) {
									System.out
											.print(String.format("%-" + (maxColumnLength + 1) + "s", rs2.getString(i)));
								}
								System.out.println();
							}
						} else {
							for (int i = 1; i <= columns; i++) {
								System.out.print(String.format("%-" + (maxValueLength + 1) + "s",
										rs2.getMetaData().getColumnLabel(i)));
							}
							System.out.println();
							while (rs2.next()) {
								for (int i = 1; i <= columns; i++) {
									System.out
											.print(String.format("%-" + (maxValueLength + 1) + "s", rs2.getString(i)));
								}
								System.out.println();
							}
						}
						rs2.beforeFirst();
					}
				}
			} else {
				System.out.println("Ihre Suchanfrage ergab leider kein Erfolg. \n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateKunde(Connection con, int kundeId, String attribute, int attributeArt) {

		String selectAttribute = null;
		Date entryDate = null;
		
		if(attributeArt == 1) {
			selectAttribute = "vorname";
		} else if (attributeArt == 2) {
			selectAttribute = "nachname";
		}else if(attributeArt == 3) {
			try {
				entryDate = SQLTableParser.convertingDateFormat(attribute);
				selectAttribute = "gebDatum";
			} catch (ParseException e) {
				System.out.println("Falsche Eingabe! " + attribute + " ist kein Gültiges Datum!");
			}
		} else if (attributeArt == 4) {
			selectAttribute = "telNr";
		} else if (attributeArt == 5) {
			selectAttribute = "mail";
		} else if (attributeArt == 6) {
			selectAttribute = "wohnort";
		}  else if (attributeArt == 7) {
			selectAttribute = "strasse";
		}  else if (attributeArt == 8) {
			selectAttribute = "plz";
		}  else if (attributeArt == 9) {
			selectAttribute = "land";
		}  else if (attributeArt == 10) {
			selectAttribute = "kontoStatus";
		}  else if (attributeArt == 11) {
			selectAttribute = "kreditBerecht";
		}
		
		String update = "UPDATE Kunde SET " + selectAttribute + "=? WHERE kundeId=" + kundeId ;
		try {
			PreparedStatement ps = con.prepareStatement(update);
			if(attributeArt == 3) {
				ps.setDate(1, entryDate);
				ps.executeUpdate();
			} else if(attributeArt == 10) {
				if(attribute.equals("0")) {
					ps.setBoolean(1, false);
				ps.executeUpdate();
				}
				else if(attribute.equals("1")) {
					ps.setBoolean(1, true);
					ps.executeUpdate();
					}
				else
					System.out.println("Fehler bei der Eingabe!");
				
			} else if(attributeArt == 11) {
				if (attribute.equals("STANDARTKONTO")) {
					ps.setString(1, kontoStatus.STANDARTKONTO.toString());
				} else if (attribute.equals("JUGENDKONTO")) {
					ps.setString(1, kontoStatus.JUGENDKONTO.toString());
				} else if (attribute.equals("STUDENTENKONTO")) {
					ps.setString(1, kontoStatus.STUDENTENKONTO.toString());
				} else {
					System.out.println("Falsche Eingabe! " + attribute);
				}
				ps.executeUpdate();
			} else {
				ps.setString(1, attribute);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
