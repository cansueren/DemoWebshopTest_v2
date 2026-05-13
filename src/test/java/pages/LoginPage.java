package pages;                                              // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                              // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver;                       // Importiert den Browser-Treiber für diese Seitenklasse
import org.openqa.selenium.support.ui.ExpectedConditions;   // Importiert fertige Wait-Bedingungen, z. B. warten bis ein Element klickbar oder sichtbar ist
import org.openqa.selenium.support.ui.WebDriverWait;        // Importiert WebDriverWait, um gezielt auf Elemente zu warten

import java.time.Duration;                                  // Importiert Duration, um die maximale Wartezeit festzulegen


public class LoginPage {                                    // Seitenklasse für die Login-Seite mit allen Aktionen und Elementen

    private WebDriver driver;                               // Speichert den Browser dieser Seite; private weil nur innerhalb dieser Klasse genutzt
    private WebDriverWait wait;                             // Speichert den expliziten Wait, damit alle Methoden gezielt auf Elemente warten können


    public LoginPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Erstellt einen expliziten Wait mit maximal 10 Sekunden Wartezeit
    }

    public void openLoginPage() { // Methode: öffnet die Login-Seite über den Log in-Link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in"))).click(); // Wartet, bis der Log in-Link klickbar ist, und klickt ihn erst dann an
    }

    public void enterEmail(String email) { // Methode: trägt die übergebene Email in das Login-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys(email); // Wartet, bis das Email-Feld sichtbar ist, und schreibt erst dann die Email hinein
    }

    public void enterPassword(String password) { // Methode: trägt das übergebene Passwort in das Login-Passwort-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password"))).sendKeys(password); // Wartet, bis das Passwort-Feld sichtbar ist, und schreibt erst dann das Passwort hinein
    }

    public void clickLoginButton() { // Methode: klickt auf den Log in Button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.button-1.login-button"))).click(); // Wartet, bis der Login-Button klickbar ist, und klickt ihn erst dann an
    }

    public boolean isLoginSuccessful() { // Methode: prüft, ob der Login erfolgreich war
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out"))).isDisplayed(); // Wartet, bis der Logout-Link sichtbar ist, und gibt true zurück, wenn er angezeigt wird
    }


}