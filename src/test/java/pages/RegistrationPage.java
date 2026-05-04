package pages; // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By; // Importiert Locator-Werkzeuge, um Elemente auf der Webseite zu finden
import org.openqa.selenium.WebDriver; // Importiert den Browser-Treiber, damit diese Seite mit dem geöffneten Browser arbeiten kann


public class RegistrationPage { // Seitenklasse für die Registrierungsseite mit allen Aktionen und Elementen

    private WebDriver driver; // Speichert den Browser dieser Seite; private wird genutzt, weil der driver nur innerhalb dieser Page-Klasse verwendet werden soll


    public RegistrationPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Page
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable, damit alle Methoden ihn nutzen können
    }

    public void openRegistrationPage() { // Öffnet die Registrierungsseite über den Register-Link
        driver.findElement(By.linkText("Register")).click(); // Selenium-Befehl: sucht den Link mit dem sichtbaren Text "Register" und führt per .click() einen Klick aus, um die Registrierungsseite zu öffnen
    }

    public void selectMaleGender() { // Methode: wählt die Option Male im Registrierungsformular aus
        driver.findElement(By.id("gender-male")).click(); // Selenium-Befehl: findet den Male-Radiobutton über id und klickt ihn an

    }

    public void enterFirstName(String firstName) {// Methode: trägt den übergebenen Vornamen in das Feld First name ein
        driver.findElement(By.id("FirstName")).sendKeys(firstName); // Selenium-Befehl: findet das Vorname-Feld über id und schreibt Text per .sendKeys() hinein

    }

    public void enterLastName(String lastName) { // Methode um den Nachnamen einzugeben
        driver.findElement(By.id("LastName")).sendKeys(lastName); // Selenium-Befehl: über id das Nachname-Feld finden & Test per .sendKeys() schreiben

    }

    public void enterEmail(String email) { // Methode: trägt die übergebene Email in das Email-Feld ein
        driver.findElement(By.id("Email")).sendKeys(email); // Selenium-Befehl: findet das Feld über id und schreibt die Email hinein
    }

    public void enterPassword(String password) { // Methode: trägt das übergebene Passwort in das Passwortfeld ein
        driver.findElement(By.id("Password")).sendKeys(password); // Selenium-Befehl: findet das Passwortfeld über die id "Password" und schreibt den Text per .sendKeys() hinein
    }

    public void enterConfirmPassword(String password) { // Methode: trägt das gleiche Passwort in das Feld Confirm Password ein
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password); // Selenium-Befehl: findet das Bestätigungsfeld über die id "ConfirmPassword" und schreibt den Text per .sendKeys() hinein
    }

    public void clickRegisterButton() { // Methode: klickt auf den Register-Button und sendet das Formular ab
        driver.findElement(By.id("register-button")).click(); // Selenium-Befehl: findet den Button über die id "register-button" und klickt ihn an
    }

    public void clickLogoutButton() { // Methode: klickt auf den Log out-Link, um den aktuell eingeloggten Benutzer abzumelden
        driver.findElement(By.linkText("Log out")).click(); // Selenium-Befehl: findet den Link über den sichtbaren Text "Log out" und klickt darauf
    }

}
