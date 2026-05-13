package pages;                                  // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                  // Importiert Locator-Werkzeuge, um Elemente auf der Webseite zu finden
import org.openqa.selenium.WebDriver;           // Importiert den Browser-Treiber, damit der Konstruktor den driver entgegennehmen kann

public class LoginPage extends BasePage {       // LoginPage erbt gemeinsame Selenium-Hilfsmethoden aus BasePage

    public LoginPage(WebDriver driver) {        // Konstruktor erhält den geöffneten Browser beim Erstellen der LoginPage
        super(driver);                          // Übergibt den driver an die BasePage, damit dort driver und wait initialisiert werden
    }

    public void openLoginPage() { // Methode: öffnet die Login-Seite über den Log in-Link
        click(By.linkText("Log in")); // Nutzt die BasePage-click-Methode und wartet automatisch, bis der Link klickbar ist
    }

    public void enterEmail(String email) { // Methode: trägt die übergebene E-Mail-Adresse in das Login-Feld ein
        type(By.id("Email"), email); // Nutzt die BasePage-type-Methode und wartet automatisch, bis das Feld sichtbar ist
    }

    public void enterPassword(String password) { // Methode: trägt das übergebene Passwort in das Passwort-Feld ein
        type(By.id("Password"), password); // Nutzt die BasePage-type-Methode und trägt das Passwort stabil ein
    }

    public void clickLoginButton() { // Methode: klickt auf den Login-Button
        click(By.cssSelector("input.button-1.login-button")); // Nutzt die BasePage-click-Methode und klickt den Login-Button stabil an
    }

    public boolean isLoginSuccessful() { // Methode: prüft, ob der Login erfolgreich war
        return isVisible(By.linkText("Log out")); // Nutzt die BasePage-isVisible-Methode und prüft, ob der Logout-Link sichtbar ist
    }
}