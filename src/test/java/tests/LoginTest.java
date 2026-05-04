package tests; // Diese Datei gehört zum Package tests für Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup und driver
import org.junit.jupiter.api.Test; // Importiert die @Test Annotation für JUnit
import pages.LoginPage; // Importiert unsere Login-Seitenklasse
import org.junit.jupiter.api.Assertions; // Importiert Assertion-Befehle zum Prüfen von Erwartungen

public class LoginTest extends BaseTest { // Testklasse erbt Browser-Setup und driver aus BaseTest


    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanOpenLoginSuccesfully() { // Test prüft, ob die Login-Seite geöffnet werden kann

        LoginPage loginPage = new LoginPage(driver); // Erstellt das Seitenobjekt und übergibt den Browser

        loginPage.openLoginPage(); // Ruft die Page-Methode auf und klickt auf den Log in-Link

        String email = "Max.mustermann" + System.currentTimeMillis() + "@test.de"; // Erstellt dynamische Test-Email
        loginPage.enterEmail(email); // Übergibt die Email an die Page-Methode und trägt sie ins Feld ein

        String password = "Test123!"; // Erstellt Test-Passwort
        loginPage.enterPassword(password); // Übergibt das Passwort an die Page-Methode und trägt es ins Feld ein

        loginPage.clickLoginButton(); // Klickt auf den Login-Button

        Assertions.assertTrue(
                driver.getPageSource().contains("Log out"),
                "Login war nicht erfolgreich!"
        ); // Prüft, ob nach dem Login der Text "Log out" sichtbar ist = Benutzer erfolgreich eingeloggt

    }
}