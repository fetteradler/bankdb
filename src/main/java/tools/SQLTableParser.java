package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLTableParser {

	/**
	 * Reade SQL Tables from external file
	 * 
	 * @param f
	 *            File with statements
	 * @return SQL statements
	 * @throws IOException
	 *             if reading file fails
	 */
	public static ArrayList<String> readSQLFile(File f) throws IOException {

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
	 * Converting file date format to SQL date format
	 * 
	 * @param strOld
	 * @return
	 * @throws ParseException
	 */
	public Date convertingDateFormat(String strOld) throws ParseException {

		final String oldFormat = "dd.MM.yyyy";
		final String newFormat = "yyyy-MM-dd";
		String dateNew;
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(strOld);
		sdf.applyPattern(newFormat);
		dateNew = sdf.format(d);
		java.sql.Date res = java.sql.Date.valueOf(dateNew);
		
		return res;

	}

	/**
	 * Reads PW from file
	 * 
	 * @param f
	 *            File with PW
	 * @return PW as String
	 * @throws IOException
	 */
	public static String readPW(File f) throws IOException {

		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(f));

		while ((str = br.readLine()) != null) {
			str = str.trim();
		}

		br.close();
		return str;
	}
}
