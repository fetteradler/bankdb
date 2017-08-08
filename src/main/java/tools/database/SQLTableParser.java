package tools.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class SQLTableParser {

	/**
	 * Reads SQL Tables from external file
	 * 
	 * @param file
	 *            File with statements
	 * @return createTables SQL statements
	 * @throws IOException
	 *             if reading file fails
	 */
	static ArrayList<String> readSQLFile(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));

		ArrayList<String> createTables = new ArrayList<String>();

		String temp = "";

		while ((temp = br.readLine()) != null) {
			temp = temp.trim();
			if (!temp.equals("")) {
				createTables.add(temp);
			}
		}

		br.close();

		return createTables;
	}

	/**
	 * Converting file date format to SQL date format
	 * 
	 * @param oldDate
	 *            Date which should be converted.
	 * @return new dateformat as a String
	 * @throws ParseException
	 *             if parse from old format to new fails
	 */
	public static Date convertingDateFormat(String oldDate) throws ParseException {

		final String oldDateFormat = "dd.MM.yyyy";
		final String newDateFormat = "yyyy-MM-dd";
		String newDate;
		SimpleDateFormat sdf = new SimpleDateFormat(oldDateFormat);
		java.util.Date d = sdf.parse(oldDate);
		sdf.applyPattern(newDateFormat);
		newDate = sdf.format(d);
		java.sql.Date newDateSQLFormat = java.sql.Date.valueOf(newDate);

		return newDateSQLFormat;

	}

	/**
	 * Reads PW from file
	 * 
	 * @param f
	 *            File with PW
	 * @return PW as String
	 * @throws IOException
	 *             if reading from file fails
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

	/**
	 * Create a current DateFormat an converts it to a SQL Timestamp.
	 * 
	 * @return Current timestamp like yyyy-MM-dd hh:mm:ss
	 * @throws SQLException
	 *             If input is invalid for database.
	 */
	public static Timestamp createCurrentTimestamp() throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Timestamp currentTime = Timestamp.valueOf(sdf.format(cal.getTime()));

		return currentTime;
	}
}
