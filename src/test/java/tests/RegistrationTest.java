package tests; // Diese Datei gehört zum Package tests für ausführbare Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup, driver und Cleanup
import org.junit.jupiter.api.Assertions; // Importiert Assertion-Befehle zum Prüfen von erwarteten Ergebnissen
import org.junit.jupiter.api.Test; // Importiert @Test, damit JUnit diese Methode als Test erkennt
import pages.RegistrationPage; // Importiert unsere RegistrationPage mit allen Registrierungsaktionen
import utils.RandomDataUtil; // Importiert die Hilfsklasse zum Erzeugen dynamischer Testdaten

public class RegistrationTest extends BaseTest { // Testklasse für Registrierung; erbt Browser-Setup, driver und Cleanup aus BaseTest

    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanRegisterSuccessfully() { // Test prüft, ob ein Benutzer sich erfolgreich registrieren kann

        RegistrationPage registrationPage = new RegistrationPage(driver); // Erstellt das Page Object und übergibt den geöffneten Browser

        String firstName = "Max"; // Speichert den Vornamen zentral für Registrierung und Konsolenausgabe
        String lastName = "Mustermann"; // Speichert den Nachnamen zentral für Registrierung und Konsolenausgabe
        String email = RandomDataUtil.generateEmail(); // Erzeugt eine eindeutige E-Mail-Adresse für die Registrierung
        String password = "Test123!"; // Speichert das Passwort zentral für Registrierung und Konsolenausgabe

        registrationPage.openRegistrationPage(); // Öffnet die Registrierungsseite
        registrationPage.selectMaleGender(); // Wählt den Male-Radiobutton aus
        registrationPage.enterFirstName(firstName); // Trägt den Vornamen ein
        registrationPage.enterLastName(lastName); // Trägt den Nachnamen ein
        registrationPage.enterEmail(email); // Trägt die dynamische E-Mail-Adresse ein
        registrationPage.enterPassword(password); // Trägt das Passwort ein
        registrationPage.enterConfirmPassword(password); // Bestätigt das Passwort

        registrationPage.clickRegisterButton(); // Klickt auf den Register-Button

        Assertions.assertTrue(  // Prüft, ob die Registrierung erfolgreich war
                registrationPage.isRegistrationSuccessful(),
                "Registrierung war nicht erfolgreich!"
        );

        // Konsolenausgabe
        System.out.println("\nRegistration Test Data:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("E-Mail: " + email);
        System.out.println("Password: " + "*".repeat(password.length()));

        registrationPage.clickLogoutButton(); // Meldet den frisch registrierten Benutzer wieder ab
    }
}