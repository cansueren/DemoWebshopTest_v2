package tests; // Diese Datei gehört zum Package tests für Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup und Login-Daten
import org.junit.jupiter.api.Test; // Importiert die @Test Annotation für JUnit
import pages.LoginPage; // Importiert unsere Login-Seitenklasse
import org.junit.jupiter.api.Assertions; // Importiert Assertion-Befehle zum Prüfen von Erwartungen

public class LoginTest extends BaseTest { // Testklasse erbt Browser-Setup und Variablen aus BaseTest

    @Test // Markiert die folgende Methode als ausführbaren Test
    public void userCanLoginSuccessfully() { // Test prüft, ob ein Benutzer sich erfolgreich einloggen kann

        // Erstelle ein LoginPage-Objekt und übergebe den Browser (driver)
        LoginPage loginPage = new LoginPage(driver);

        // Öffnet die Login-Seite im Browser
        loginPage.openLoginPage();

        // Trägt die feste Test-Email aus BaseTest in das Email-Feld ein
        loginPage.enterEmail(TEST_EMAIL);

        // Trägt das feste Passwort aus BaseTest in das Passwort-Feld ein
        loginPage.enterPassword(TEST_PASSWORD);

        // Klickt auf den Login-Button
        loginPage.clickLoginButton();

        // Prüft, ob nach dem Login der Text "Log out" sichtbar ist
        // Wenn ja, war der Login erfolgreich
        Assertions.assertTrue(
                driver.getPageSource().contains("Log out"),
                "Login war nicht erfolgreich!"
        );
    }
}