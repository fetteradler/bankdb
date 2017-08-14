# bankdb
Verwaltungsapplikation einer Bank.

Die Software kann bereits: 
- automatisches erstellen einer Datenbank
- Testdaten mit Hilfe eines Javaprogramms aus .csv-Daten einlesen und in die Datenbank einfuegen
- über die Konsoleneingabe manuel Kredite zu Kunden hinzufügen
- neue Kunden/Mitarbeiter hinzufügen
- Kunden können von einem ausgewählten Konto Geld abheben oder aufbuchen
- über ein Startmenü navigieren ob man sich als Kunde, Mitarbeiter oder Filialleiter anmelden möchte
- prüfen, ob die eingegebene anmeldeId gültig ist 
- prüfen, ob eine Sitzung noch gültig ist
- bestehende Mitarbeiter- und Kundendaten bearbeiten/löschen
- für bestehende Kunden neue Konten hinzufügen

Die Software soll in Zukunft: 
- Kunden sollen Überweisungen tätigen können
- automatische berechnung und verrechnung von Zinsen und Gebuehren


Installieren der Software:
Nötig ist eine Java Version(mind. 1.8) und eine aktuelle Version von MySQL. 
In MySQL muss ein Benutzerkonto angelegt werden. Name und Passwort kann beliebig in der Datei 'DatenbaseInitializer.java' angepasst werden. 
Dann muss nur noch die Datei 'ApplicationStarter.class' mit Java ausgeführt werden und die Anwendung läuft. 
