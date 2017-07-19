package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Dokumentation!
public abstract class SQLTableParser {

	/**
	 * Reade SQL Tables from external file
	 * 
	 * @param file
	 *            File with statements
	 * @return SQL statements
	 * @throws IOException
	 *             if reading file fails
	 */
	static ArrayList<String> readSQLFile(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));

		ArrayList<String> res = new ArrayList<String>();

		String temp = "";

		while ((temp = br.readLine()) != null) {
			temp = temp.trim();
			if (!temp.equals("")) {
				// Kein continue nowendig. continue und break vermeiden so gut es geht. Sonst hast du irgendwann GOTO Programme.
				res.add(temp);
			}
		}

		br.close();

		return res;
	}

	/**
	 * Converting file date format to SQL date format
	 * 
	 * @param strOld Daokumentation!
	 * @return
	 * @throws ParseException
	 */
	public static Date convertingDateFormat(String strOld) throws ParseException {

		final String oldFormat = "dd.MM.yyyy";
		final String newFormat = "yyyy-MM-dd";
		String dateNew;
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = sdf.parse(strOld);
		sdf.applyPattern(newFormat);
		dateNew = sdf.format(d);
		return java.sql.Date.valueOf(dateNew);

	}

	// readPW wird nirgendwo verwendet.
}
