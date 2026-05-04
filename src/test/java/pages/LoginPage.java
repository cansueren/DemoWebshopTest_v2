package pages; // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By; // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver; // Importiert den Browser-Treiber für diese Seitenklasse

public class LoginPage { // Seitenklasse für die Login-Seite mit allen Aktionen und Elementen

    private WebDriver driver; // Speichert den Browser dieser Seite; private weil nur innerhalb dieser Klasse genutzt


    public LoginPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
    }

    public void openLoginPage() { // Methode: öffnet die Login-Seite über den Log in-Link
        driver.findElement(By.linkText("Log in")).click(); // Selenium-Befehl: findet den Link über sichtbaren Text und klickt ihn an
    }

    public void enterEmail(String email) { // Methode: trägt die übergebene (auch dynamische) Email in das Login-Feld ein
        driver.findElement(By.id("Email")).sendKeys(email); // Selenium-Befehl: findet das Email-Feld über id und schreibt die Email hinein
    }

    public void enterPassword(String password) { // Methode: trägt das übergebene Passwort in das Login-Passwort-Feld ein
        driver.findElement(By.id("Password")).sendKeys(password); // Selenium-Befehl: findet das Passwort-Feld über id und schreibt das Passwort hinein
    }

    public void clickLoginButton() { // Methode: klickt auf den Log in Button
        driver.findElement(By.cssSelector("input.button-1.login-button")).click(); // Selenium-Befehl: findet den Login-Button über CSS Selector und klickt ihn
    }




}