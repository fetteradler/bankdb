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

import tools.EnumParser.status;
import tools.EnumParser.titel;

public class TestDatensatzBefueller {

	@SuppressWarnings("static-access")
	public static void einfuegenAngestellten(Connection con)
			throws SQLException, FileNotFoundException, ParseException {

		File f = new File("lib" + File.separator + "database" + File.separator + "testdata" + File.separator
				+ "Mitarbeiter2.csv");

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
					SQLTabellenParser tp = new SQLTabellenParser();

					ps.setInt(1, Integer.parseInt(s[0]));
					ps.setString(2, s[1]);
					ps.setString(3, s[2]);
					ps.setDate(4, (Date) tp.umwandlungDateFormat(s[3]));
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
					ps.setDate(12, (Date) tp.umwandlungDateFormat(s[11]));
					ps.setInt(13, Integer.parseInt(s[12]));
					if (s[13] == null) {
						ps.setDate(14, (Date) tp.umwandlungDateFormat(s[13]));
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
	
	@SuppressWarnings("static-access")
	public static void einfuegenFilialleiter(Connection con)
			throws SQLException, FileNotFoundException, ParseException {

		File f = new File("lib" + File.separator + "database" + File.separator + "testdata" + File.separator
				+ "Mitarbeiter2.csv");

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
				"INSERT INTO Filialleiter(angestelltId, vorname, nachname, gebDatum, telNr, mail, wohnort, strasse, plz, land, titel, einstellDatum, monatsLohn, fristDatum, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {
			while ((temp = br.readLine()) != null) {

				s = temp.split(split);

				if (s[15].equals("Fillialleiter")) {
					SQLTabellenParser tp = new SQLTabellenParser();

					ps.setInt(1, Integer.parseInt(s[0]));
					ps.setString(2, s[1]);
					ps.setString(3, s[2]);
					ps.setDate(4, (Date) tp.umwandlungDateFormat(s[3]));
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
					ps.setDate(12, (Date) tp.umwandlungDateFormat(s[11]));
					ps.setInt(13, Integer.parseInt(s[12]));
					if (s[13] == null) {
						ps.setDate(14, (Date) tp.umwandlungDateFormat(s[13]));
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
}
