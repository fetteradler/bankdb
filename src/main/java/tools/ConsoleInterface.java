package tools;

import java.util.Scanner;

// Dokumentation!
public class ConsoleInterface {

	// Dokumentation!
	public void anmeldung() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("----- Herzlich wilkommen bei Bankdb ----- \n" + "Was möchten Sie als nächstes tun?");
		System.out.println("1 - Anmeldung Mitarbeiter \n" + "2 - Anmeldung Kunde");

		int i = 0;
		while (i == 0) {
			String s = scanner.next();
			int input = Integer.parseInt(s);
			switch (input) {
			case 1:
				System.out.println("Anmeldung Mitarbeiter...");
				i = 1;
				System.out.println("Wie möchten Sie als nächstes vorgehen?");
				break;
			case 2:
				System.out.println("Anmeldung Kunde...");
				i = 1;
				System.out.println("Wie möchten Sie als nächstes vorgehen?");
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
		scanner.close();
	}

	// 1 Zeile muss nicht ausgelagert werden. 2 Funktionen sind auf jeden Fall zu viel
}
