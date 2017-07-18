package tools;

import java.util.Scanner;

public class ConsoleInterface {

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
				mitarbeiterScreen();
				break;
			case 2:
				System.out.println("Anmeldung Kunde...");
				i = 1;
				kundeScreen();
				break;
			default:
				System.out.println(
						"Entschuldigung aber Ihre Eingabe konnte nicht verarbeitet werden. \nBitte geben Sie Ihre Auswahl erneut ein: ");
				break;
			}
		}
		scanner.close();
	}

	public void mitarbeiterScreen() {
		System.out.println("Wie möchten Sie als nächstes vorgehen?");
	}

	public void kundeScreen() {
		System.out.println("Wie möchten Sie als nächstes vorgehen?");
	}
}
