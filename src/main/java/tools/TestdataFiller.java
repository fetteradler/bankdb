package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import tools.EnumParser.kontoStatus;
import tools.EnumParser.status;
import tools.EnumParser.titel;

/**
 * Fill database with testdata from CSV files.
 * 
 * @author caro
 *
 */
public class TestdataFiller {

	/**
	 * Fill table 'Angestellter' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void insertAngestellten(Connection con) throws SQLException, FileNotFoundException, ParseException {

		File f = new File("lib" + File.separator + "database" + File.separator + "testdata" + File.separator
				+ "Mitarbeiter2.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		String temp = "";
		String[] s = null;
		String split = ":";

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO Angestellter(angestelltId, vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, einstellDatum, monatsLohn, fristDatum, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {

				s = temp.split(split);

				if (s[15].equals("Fillialleiter")) {
					continue;
				} else {
					SQLTableParser tp = new SQLTableParser();

					ps.setInt(1, Integer.parseInt(s[0]));
					ps.setString(2, s[1]);
					ps.setString(3, s[2]);
					ps.setDate(4, (Date) tp.convertingDateFormat(s[3]));
					ps.setString(5, s[4]);
					ps.setString(6, s[5]);
					ps.setString(7, s[6]);
					ps.setString(8, s[7]);
					ps.setString(9, s[8]);
					ps.setString(10, s[9]);
					String titelEnum = s[10].trim();
					if (titelEnum.equals("HERR")) {
						ps.setString(11, titel.HERR.toString());
					} else if (titelEnum.equals("FRAU")) {
						ps.setString(11, titel.FRAU.toString());
					} else {
						throw new IOException();
					}
					ps.setDate(12, (Date) tp.convertingDateFormat(s[11]));
					ps.setInt(13, Integer.parseInt(s[12]));
					if (s[13] == null) {
						ps.setDate(14, (Date) tp.convertingDateFormat(s[13]));
					} else {
						ps.setDate(14, null);
					}
					String statusEnum = s[14];
					if (statusEnum.equals("VERFUEGBAR")) {
						ps.setString(15, status.VERFUEGBAR.toString());
					} else if (statusEnum.equals("KRANK")) {
						ps.setString(15, status.KRANK.toString());
					} else if (statusEnum.equals("BEURLAUBT")) {
						ps.setString(15, status.BEURLAUBT.toString());
					} else {
						System.out.println("Falsche Eingabe! " + statusEnum);
					}
				}
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Angestellten Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Filialleiter' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void insertFilialleiter(Connection con) throws SQLException, FileNotFoundException, ParseException {

		File f = new File("lib" + File.separator + "database" + File.separator + "testdata" + File.separator
				+ "Mitarbeiter2.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		String temp = "";
		String[] s = null;
		String split = ":";

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO Filialleiter(leiterId, vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, einstellDatum, monatsLohn, fristDatum, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {

				s = temp.split(split);

				if (s[15].equals("Fillialleiter")) {
					SQLTableParser tp = new SQLTableParser();

					ps.setInt(1, Integer.parseInt(s[0]));
					ps.setString(2, s[1]);
					ps.setString(3, s[2]);
					ps.setDate(4, (Date) tp.convertingDateFormat(s[3]));
					ps.setString(5, s[4]);
					ps.setString(6, s[5]);
					ps.setString(7, s[6]);
					ps.setString(8, s[7]);
					ps.setString(9, s[8]);
					ps.setString(10, s[9]);
					String titelEnum = s[10].trim();
					if (titelEnum.equals("HERR")) {
						ps.setString(11, titel.HERR.toString());
					} else if (titelEnum.equals("FRAU")) {
						ps.setString(11, titel.FRAU.toString());
					} else {
						throw new IOException();
					}
					ps.setDate(12, (Date) tp.convertingDateFormat(s[11]));
					ps.setInt(13, Integer.parseInt(s[12]));
					if (s[13] == null) {
						ps.setDate(14, (Date) tp.convertingDateFormat(s[13]));
					} else {
						ps.setDate(14, null);
					}
					String statusEnum = s[14];
					if (statusEnum.equals("VERFUEGBAR")) {
						ps.setString(15, status.VERFUEGBAR.toString());
					} else if (statusEnum.equals("KRANK")) {
						ps.setString(15, status.KRANK.toString());
					} else if (statusEnum.equals("BEURLAUBT")) {
						ps.setString(15, status.BEURLAUBT.toString());
					} else {
						System.out.println("Falsche Eingabe! " + statusEnum);
					}
				} else {
					continue;
				}
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Filialleiter Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Kunde' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void insertKunden(Connection con) throws SQLException, FileNotFoundException, ParseException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		String temp = "";
		String[] s = null;
		String split = ":";

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO Kunde(kundeId, vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, aufnahmeDatum, kontoStatus, kreditBerecht) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {

				s = temp.split(split);

				SQLTableParser tp = new SQLTableParser();

				if (s[0].equals("")) {
					continue;
				} else {
					ps.setInt(1, Integer.parseInt(s[0]));
					ps.setString(2, s[1]);
					ps.setString(3, s[2]);
					ps.setDate(4, (Date) tp.convertingDateFormat(s[3]));
					ps.setString(5, s[4]);
					ps.setString(6, s[5]);
					ps.setString(7, s[6]);
					ps.setString(8, s[7]);
					ps.setString(9, s[8]);
					ps.setString(10, s[9]);
					String titelEnum = s[10].trim();
					if (titelEnum.equals("HERR")) {
						ps.setString(11, titel.HERR.toString());
					} else if (titelEnum.equals("FRAU")) {
						ps.setString(11, titel.FRAU.toString());
					} else {
						throw new IOException();
					}
					ps.setDate(12, (Date) tp.convertingDateFormat(s[11]));

					String statusEnum = s[12].trim();
					if (statusEnum.equals("STANDARTKONTO")) {
						ps.setString(13, kontoStatus.STANDARTKONTO.toString());
					} else if (statusEnum.equals("JUGENDKONTO")) {
						ps.setString(13, kontoStatus.JUGENDKONTO.toString());
					} else if (statusEnum.equals("STUDENTENKONTO")) {
						ps.setString(13, kontoStatus.STUDENTENKONTO.toString());
					} else {
						System.out.println("Falsche Eingabe! " + statusEnum);
					}

					String kredit = s[13].trim();
					if (kredit.equals("1")) {
						ps.setBoolean(14, true);
					} else if (kredit.equals("0")) {
						ps.setBoolean(14, false);
					} else {
						System.out.println("Falsche Eingabe! " + kredit);
					}
				}
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Kunden Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Girokonto' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public void insertGirokonto(Connection con) throws FileNotFoundException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Girokonto.csv");

		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Girokonto(giroId, guthaben, gebueren) VALUES (?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {
				s = temp.split(split);

				ps.setInt(1, Integer.parseInt(s[0]));
				ps.setInt(2, Integer.parseInt(s[1]));
				if (s.length <= 2) {
					ps.setInt(3, 0);
				} else {
					ps.setInt(3, Integer.parseInt(s[2]));
				}
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Girokonto Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Sparbuch' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public void insertSparbuch(Connection con) throws FileNotFoundException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Sparbuch.csv");

		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Sparbuch(sparId, guthaben, zinsen) VALUES (?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {
				s = temp.split(split);

				ps.setInt(1, Integer.parseInt(s[0]));
				ps.setInt(2, Integer.parseInt(s[1]));
				ps.setInt(3, Integer.parseInt(s[2]));
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Sparbuch Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Kreditkarte' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public void insertKreditkarte(Connection con) throws FileNotFoundException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kreditkarte.csv");

		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con
				.prepareStatement("INSERT INTO Kreditkartenkonto(kreditkarteId, betrag, zinsen) VALUES (?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {
				s = temp.split(split);

				ps.setInt(1, Integer.parseInt(s[0]));
				ps.setInt(2, Integer.parseInt(s[1]));
				if (s.length <= 2) {
					ps.setInt(3, 0);
				} else {
					ps.setInt(3, Integer.parseInt(s[2]));
				}
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Kreditkarte Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Kredit' with testdata.
	 * 
	 * @param con
	 *            Connection to database
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void insertKredit(Connection con) throws FileNotFoundException, SQLException, ParseException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kredit.csv");

		SQLTableParser tp = new SQLTableParser();

		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			br.readLine();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con
				.prepareStatement("INSERT INTO Kredit(kreditId, betrag, zinsen, raten, laufzeit) VALUES (?,?,?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {
				s = temp.split(split);

				ps.setInt(1, Integer.parseInt(s[0]));
				ps.setInt(2, Integer.parseInt(s[1]));
				ps.setInt(3, Integer.parseInt(s[2]));
				ps.setInt(4, Integer.parseInt(s[3]));
				ps.setDate(5, (Date) tp.convertingDateFormat(s[4]));
				ps.executeUpdate();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Kredit Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Kunde_Sparbuch' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void insertKundeSparbuch(Connection con) throws IOException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		br.readLine();

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Kunde_Sparbuch(sparId, kundeId) VALUES (?,?)");

		while ((temp = br.readLine()) != null) {

			s = temp.split(split);

			if (s.length <= 16) {
				continue;
			} else if (s[16].equals("")) {
				continue;
			} else if (s[0].equals("")) {
				continue;
			} else {
				ps.setInt(1, Integer.parseInt(s[16]));
				ps.setInt(2, Integer.parseInt(s[0]));
			}
			ps.executeUpdate();
		}

		System.out.println("Kunde_Sparbuch Erfolgreich hinzugefuegt.");
	}

	/**
	 * Fill table 'Kunde_Girokonto' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void insertKundeGirokonto(Connection con) throws IOException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		br.readLine();

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Kunde_Girokonto(giroId, kundeId) VALUES (?,?)");

		s = temp.split(split);

		while ((temp = br.readLine()) != null) {

			String personId = s[0];

			s = temp.split(split);
			if (s.length <= 15) {
				continue;
			} else if (s[0].equals("")) {
				if (s[15].equals("")) {
					break;
				}
				ps.setInt(1, Integer.parseInt(s[15]));
				ps.setInt(2, Integer.parseInt(personId));
				s[0] = personId;
			} else {
				ps.setInt(1, Integer.parseInt(s[15]));
				ps.setInt(2, Integer.parseInt(s[0]));
			}
			ps.executeUpdate();
		}

		System.out.println("Kunde_Girokonto Erfolgreich hinzugefuegt.");
	}
	
	/**
	 * Fill table 'Kunde_Kreditkarte' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void insertKundeKreditkarte(Connection con) throws IOException, SQLException {
		
		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		br.readLine();

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Kunde_Kreditkarte(kreditkarteId, kundeId) VALUES (?,?)");


		while ((temp = br.readLine()) != null) {

			s = temp.split(split);

			if (s.length <= 17) {
				continue;
			} else if (s[17].equals("")) {
				continue;
			} else if (s[0].equals("")) {
				continue;
			} else {
				ps.setInt(1, Integer.parseInt(s[17]));
				ps.setInt(2, Integer.parseInt(s[0]));
			}
			ps.executeUpdate();
		}

		System.out.println("Kunde_Kreditkarte Erfolgreich hinzugefuegt.");
	}
	
	/**
	 * Fill table 'Kunde_Kredit' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void insertKundeKredit(Connection con) throws IOException, SQLException {
		
		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		br.readLine();

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Kunde_Kredit(kreditId, kundeId) VALUES (?,?)");

		s = temp.split(split);

		while ((temp = br.readLine()) != null) {

			String personId = s[0];

			s = temp.split(split);
			if (s.length <= 18) {
				continue;
			} else if (s[0].equals("")) {
				if (s[15].equals("")) {
					break;
				}
				ps.setInt(1, Integer.parseInt(s[18]));
				ps.setInt(2, Integer.parseInt(personId));
				s[0] = personId;
			} else {
				ps.setInt(1, Integer.parseInt(s[18]));
				ps.setInt(2, Integer.parseInt(s[0]));
			}
			ps.executeUpdate();
		}

		System.out.println("Kunde_Kredit Erfolgreich hinzugefuegt.");
	}
	
	/**
	 * Fill table 'Angestellter_Kunde' with testdata
	 * 
	 * @param con
	 *            Connection to database
	 * @throws IOException
	 * @throws SQLException
	 */
	public void insertAngestellterKunde(Connection con) throws IOException, SQLException {

		File f = new File(
				"lib" + File.separator + "database" + File.separator + "testdata" + File.separator + "Kunden3.csv");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(f));
		br.readLine();

		String temp = "";
		String[] s;
		String split = ":";

		PreparedStatement ps = con.prepareStatement("INSERT INTO Angestellter_Kunde(kundeId, angestelltId) VALUES (?,?)");

		while ((temp = br.readLine()) != null) {

			s = temp.split(split);
			
			if (s[0].equals("")) {
				continue;
			} else {
				ps.setInt(1, Integer.parseInt(s[0]));
				ps.setInt(2, Integer.parseInt(s[14]));
			}
			ps.executeUpdate();
		}

		System.out.println("Angestellter_Kunde Erfolgreich hinzugefuegt.");

	}
}
