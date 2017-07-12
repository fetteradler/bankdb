package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLTabellenParser {

	/**
	 * SQL Tabellen werden aus externem File eingelesen
	 * 
	 * @param f
	 *            Datei aus der Statements gelesen werden
	 * @return gibt SQL Statements zurueck
	 * @throws IOException
	 *             wird geworfen wenn Textfile nicht gelesen werden kann
	 */
	public static ArrayList<String> liesSQLFile(File f) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(f));

		ArrayList<String> res = new ArrayList<String>();

		String temp = "";

		while ((temp = br.readLine()) != null) {
			temp = temp.trim();
			if (temp.equals("")) {
				continue;
			} else {
				res.add(temp);
			}
		}

		br.close();

		return res;
	}

	/**
	 * Umwandeln von Dokument Date Format zu SQL Date Format
	 * 
	 * @param strAlt
	 * @return
	 * @throws ParseException
	 */
	public static Date umwandlungDateFormat(String strAlt) throws ParseException {

		final String altesDateFormat = "dd.MM.yyyy";
		final String neuesDateFormat = "yyyy-MM-dd";
		String dateNeu;
		SimpleDateFormat sdf = new SimpleDateFormat(altesDateFormat);
		Date d = sdf.parse(strAlt);
		sdf.applyPattern(neuesDateFormat);
		dateNeu = sdf.format(d);
		java.sql.Date res = java.sql.Date.valueOf(dateNeu);
		
		return res;

	}

	/**
	 * Liest PW aus Datei ein
	 * 
	 * @param f
	 *            Datei aus der PW gelesen wird
	 * @return gibt PW zurueck
	 * @throws IOException
	 */
	public static String liesPW(File f) throws IOException {

		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(f));

		while ((str = br.readLine()) != null) {
			str = str.trim();
		}

		br.close();
		return str;
	}
}
