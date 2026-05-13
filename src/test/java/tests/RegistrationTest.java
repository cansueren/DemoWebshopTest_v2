package tests;                                          // Diese Datei gehört zum Package tests für ausführbare Testklassen

import base.BaseTest;                                   // Importiert unsere Basisklasse mit driver, Browserstart und cleanUp
import org.junit.jupiter.api.Test;                      // Importiert @Test, damit JUnit diese Methode als Test ausführt
import pages.RegistrationPage;                          // Importiert unsere Page-Klasse mit den Registrierungsfunktionen
import org.junit.jupiter.api.Assertions;                // Importiert Assertion-Befehle zum Prüfen von Erwartungen
import utils.RandomDataUtil;                            // Importiert die Hilfsklasse zum Erzeugen dynamischer Testdaten

public class RegistrationTest extends BaseTest {        // Testklasse erbt Browser Setup und driver aus BaseTest


    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanRegisterSuccessfully() { // Testmethode prüft, ob die Registrierungsseite geöffnet werden kann

        RegistrationPage registrationPage = new RegistrationPage(driver); // Erstellt das Seitenobjekt und übergibt den geöffneten Browser aus BaseTest

        registrationPage.openRegistrationPage(); // Ruft unsere Page-Methode auf, die per Selenium auf Register klickt
        registrationPage.selectMaleGender(); // Ruft die Page-Methode auf und wählt den Male-Radiobutton au
        registrationPage.enterFirstName("Max"); // Übergibt den Text Can an unsere Methode und trägt ihn ins Feld ein
        registrationPage.enterLastName("Mustermann"); // Wir übergeben unseren Nachnamen Text "Suren"

        String email = RandomDataUtil.generateEmail(); // Erzeugt eine eindeutige E-Mail-Adresse für die Registrierung
        registrationPage.enterEmail(email); // Übergibt die erzeugte Email an unsere Page-Methode und trägt sie ein

        String password = "Test123!"; // Speichert das Passwort als Textvariable, damit wir es für beide Felder verwenden
        registrationPage.enterPassword(password); // Trägt das Passwort in das erste Passwortfeld ein
        registrationPage.enterConfirmPassword(password); // Trägt dasselbe Passwort in das Bestätigungsfeld ein

        registrationPage.clickRegisterButton(); // Register-Button klicken

        Assertions.assertTrue(
                registrationPage.isRegistrationSuccessful(),
                "Registrierung war nicht erfolgreich!"
        ); // Prüft über die Page-Methode, ob die Erfolgsmeldung sichtbar ist

        registrationPage.clickLogoutButton(); // Meldet den frisch registrierten Benutzer wieder ab

    }

}