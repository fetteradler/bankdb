package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.sql.Date;

import database.tools.SQLTableParser;

public abstract class UserInputReader {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

	public static void closeInputRead() throws IOException {
		br.close();
	}
}