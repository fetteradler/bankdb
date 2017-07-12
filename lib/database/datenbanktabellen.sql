CREATE TABLE IF NOT EXISTS Angestellter (angestelltId SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, vorname VARCHAR(30), nachname VARCHAR(30), gebDatum DATE, telNr VARCHAR(20), mail VARCHAR(50), wohnort VARCHAR(30), strasse VARCHAR(45), plz CHAR(6), land VARCHAR(30), titel ENUM('HERR', 'FRAU'), einstellDatum DATE, monatsLohn INTEGER, fristDatum DATE, status ENUM('VERFUEGBAR', 'KRANK', 'BEURLAUBT') NOT NULL )

CREATE TABLE IF NOT EXISTS Filialleiter (leiterId SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, vorname VARCHAR(30), nachname VARCHAR(30), gebDatum DATE, telNr VARCHAR(20), mail VARCHAR(50), wohnort VARCHAR(30), strasse VARCHAR(45), plz CHAR(6), land VARCHAR(30), titel ENUM('HERR', 'FRAU'), einstellDatum DATE, monatsLohn INTEGER, status ENUM('VERFUEGBAR', 'KRANK', 'BEURLAUBT') NOT NULL )

CREATE TABLE IF NOT EXISTS Kunde (kundeId SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, vorname VARCHAR(30), nachname VARCHAR(30), gebDatum DATE, telNr VARCHAR(20), mail VARCHAR(50), wohnort VARCHAR(30), strasse VARCHAR(45), plz CHAR(6), land VARCHAR(30), titel ENUM('HERR', 'FRAU'), aufnahmeDatum DATE NOT NULL, kontoStatus ENUM('JUGENDKONTO', 'STUDENTENKONTO', 'STANDARTKONTO') NOT NULL, kreditBerecht BOOLEAN DEFAULT NULL )

CREATE TABLE IF NOT EXISTS Sparbuch (sparId INTEGER UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, guthaben INTEGER UNSIGNED NOT NULL, zinsen INTEGER)

CREATE TABLE IF NOT EXISTS Girokonto (giroId INTEGER UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, guthaben INTEGER NOT NULL, gebueren INTEGER)

CREATE TABLE IF NOT EXISTS Kreditkartenkonto (kreditkarteId INTEGER UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, betrag INTEGER UNSIGNED NOT NULL, zinsen INTEGER)

CREATE TABLE IF NOT EXISTS Kredit (kreditId INTEGER UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY, betrag INTEGER NOT NULL, zinsen INTEGER, raten INTEGER, laufzeit DATE)

CREATE TABLE IF NOT EXISTS Kunde_Sparbuch (sparId INTEGER UNSIGNED NOT NULL, kundeId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (sparId, kundeId), FOREIGN KEY (sparId) REFERENCES Sparbuch (sparId) ON DELETE CASCADE, FOREIGN KEY (kundeId) REFERENCES Kunde (kundeId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Kunde_Girokonto (giroId INTEGER UNSIGNED NOT NULL, kundeId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (giroId, kundeId), FOREIGN KEY (giroId) REFERENCES Girokonto (giroId) ON DELETE CASCADE, FOREIGN KEY (kundeId) REFERENCES Kunde (kundeId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Kunde_Kreditkarte (kreditkarteId INTEGER UNSIGNED NOT NULL, kundeId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (kreditkarteId, kundeId), FOREIGN KEY (kreditkarteId) REFERENCES Kreditkartenkonto (kreditkarteId) ON DELETE CASCADE, FOREIGN KEY (kundeId) REFERENCES Kunde (kundeId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Kunde_Kredit (kreditId INTEGER UNSIGNED NOT NULL, kundeId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (kreditId, kundeId), FOREIGN KEY (kreditId) REFERENCES Kredit (kreditId) ON DELETE CASCADE, FOREIGN KEY (kundeId) REFERENCES Kunde (kundeId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Filialleiter_Angestellter (leiterId SMALLINT UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (leiterId, angestelltId), FOREIGN KEY (leiterId) REFERENCES Filialleiter (leiterId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Girokonto_Kreditkarte (kreditkarteId INTEGER UNSIGNED NOT NULL, giroId INTEGER UNSIGNED NOT NULL, PRIMARY KEY (kreditkarteId, giroId), FOREIGN KEY (kreditkarteId) REFERENCES Kreditkartenkonto (kreditkarteId) ON DELETE CASCADE, FOREIGN KEY (giroId) REFERENCES Girokonto (giroId) ON DELETE RESTRICT)

CREATE TABLE IF NOT EXISTS Girokonto_Kredit (kreditId INTEGER UNSIGNED NOT NULL, giroId INTEGER UNSIGNED NOT NULL, PRIMARY KEY (kreditId, giroId), FOREIGN KEY (kreditId) REFERENCES Kredit (kreditId) ON DELETE CASCADE, FOREIGN KEY (giroId) REFERENCES Girokonto (giroId) ON DELETE RESTRICT)

CREATE TABLE IF NOT EXISTS Angestellter_Kunde (kundeId SMALLINT UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (kundeId, angestelltId), FOREIGN KEY (kundeId) REFERENCES Kunde (kundeId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Angestellter_Kredit (kreditId INTEGER UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (kreditId, angestelltId), FOREIGN KEY (kreditId) REFERENCES Kredit (kreditId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Angestellter_Kreditkarte (kreditkarteId INTEGER UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (kreditkarteId, angestelltId), FOREIGN KEY (kreditkarteId) REFERENCES Kreditkartenkonto (kreditkarteId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Angestellter_Girokonto (giroId INTEGER UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (giroId, angestelltId), FOREIGN KEY (giroId) REFERENCES Girokonto (giroId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)

CREATE TABLE IF NOT EXISTS Angestellter_Sparbuch (sparId INTEGER UNSIGNED NOT NULL, angestelltId SMALLINT UNSIGNED NOT NULL, PRIMARY KEY (sparId, angestelltId), FOREIGN KEY (sparId) REFERENCES Sparbuch (sparId) ON DELETE CASCADE, FOREIGN KEY (angestelltId) REFERENCES Angestellter (angestelltId) ON DELETE CASCADE)
