package utils; // Diese Datei gehört zum Package utils für allgemeine Hilfsklassen

public class RandomDataUtil { // Hilfsklasse zum Erzeugen dynamischer Testdaten

    public static String generateEmail() { // Methode erzeugt eine eindeutige E-Mail-Adresse für Tests
        return "max.mustermann" + System.currentTimeMillis() + "@test.de"; // Nutzt die aktuelle Zeit, damit jede E-Mail eindeutig ist
    }
}