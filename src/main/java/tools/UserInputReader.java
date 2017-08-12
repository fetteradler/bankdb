package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.sql.Date;

import database.tools.SQLTableParser;

/**
 * Open new BufferedReader. Read all user input via BufferedReader. Close
 * BufferedReader.
 * 
 * @author CM
 *
 */
public abstract class UserInputReader {

	/**
	 * BufferdReader to read console input.
	 */
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Read an int from console input.
	 * 
	 * @param request
	 *            Instruction for what to enter.
	 * @return Valid user entries.
	 */
	public static int requestInteger(String request) {

		System.out.println(request);

		while (true) {
			try {
				int result = Integer.parseInt(br.readLine());
				return result;

			} catch (NumberFormatException | IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Entschuldigung, aber Ihre Eingabe konnte nicht verarbeitet werden. "
						+ "\nBitte geben Sie Ihre Auswahl erneut ein: ");
			}
		}
	}

	/**
	 * Reads an int from the console input.
	 * 
	 * @param request
	 *            Instruction for what to enter.
	 * @param validValues
	 *            Array of all valid valuse.
	 * @return Valid user entry
	 */
	public static int requestInteger(String request, int[] validValues) {

		System.out.println(request);

		while (true) {
			try {
				int result = Integer.parseInt(br.readLine());

				for (int i = 0; i < validValues.length; i++) {
					if (validValues[i] == result) {
						return result;
					}
				}

				System.out.println("Ihre Eingabe entspricht keinem der gültigen Werte.");

			} catch (NumberFormatException | IOException e) {
				System.out.println("Entschuldigung, aber Ihre Eingabe konnte nicht verarbeitet werden. "
						+ "\nBitte geben Sie Ihre Auswahl erneut ein: ");
			}
		}
	}

	/**
	 * Reads a String from console input.
	 * 
	 * @param request
	 *            Instruction for what to enter.
	 * @return Valid user entry.
	 */
	public static String requestString(String request) {

		System.out.println(request);

		while (true) {
			try {
				String result = br.readLine();
				return result;

			} catch (NumberFormatException | IOException e) {
				System.err.println(e.getMessage());
				System.out.println("Entschuldigung, aber Ihre Eingabe konnte nicht verarbeitet werden. "
						+ "\nBitte geben Sie Ihre Auswahl erneut ein: ");
			}
		}
	}

	/**
	 * Reads a Date from console input and parse it to sql.Date.
	 * 
	 * @param request
	 *            Instruction for what to enter.
	 * @return Valid user entry.
	 */
	public static Date requestDate(String request) {

		System.out.println(request);

		while (true) {
			try {
				String entry = br.readLine();
				Date result = SQLTableParser.convertingDateFormat(entry);
				return result;

			} catch (NumberFormatException | IOException | ParseException e) {
				System.err.println(e.getMessage());
				System.out.println("Entschuldigung, aber Ihre Eingabe konnte nicht verarbeitet werden. "
						+ "\nBitte geben Sie Ihre Auswahl erneut ein: ");
			}
		}
	}

	/**
	 * Close BufferedReader.
	 * 
	 * @throws IOException
	 *             If closing BufferedReader fails.
	 */
	public static void closeInputRead() throws IOException {
		br.close();
	}
}