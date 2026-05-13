package pages;                                              // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                              // Importiert Locator-Werkzeuge, um Elemente auf der Webseite zu finden
import org.openqa.selenium.WebDriver;                       // Importiert den Browser-Treiber, damit diese Seite mit dem geöffneten Browser arbeiten kann
import org.openqa.selenium.support.ui.ExpectedConditions;   // Importiert fertige Wait-Bedingungen
import org.openqa.selenium.support.ui.WebDriverWait;        // Importiert WebDriverWait, um gezielt auf Elemente zu warten

import java.time.Duration;                                  // Importiert Duration, um die maximale Wartezeit festzulegen


public class RegistrationPage {                             // Seitenklasse für die Registrierungsseite mit allen Aktionen und Elementen

    private WebDriver driver;                               // Speichert den Browser dieser Seite; private wird genutzt, weil der driver nur innerhalb dieser Page-Klasse verwendet werden soll
    private WebDriverWait wait;                             // Speichert den expliziten Wait, damit wir gezielt auf Elemente warten können


    public RegistrationPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Page
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable, damit alle Methoden ihn nutzen können
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Erstellt einen expliziten Wait mit maximal 10 Sekunden Wartezeit
    }

    public void openRegistrationPage() { // Öffnet die Registrierungsseite über den Register-Link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click(); // Wartet, bis der Register-Link klickbar ist, und klickt ihn erst dann an
    }

    public void selectMaleGender() { // Methode: wählt die Option Male im Registrierungsformular aus
        wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-male"))).click(); // Wartet, bis der Male-Radiobutton klickbar ist, und klickt ihn erst dann an
    }

    public void enterFirstName(String firstName) { // Methode: trägt den übergebenen Vornamen in das Feld First name ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(firstName); // Wartet, bis das Vorname-Feld sichtbar ist, und schreibt erst dann den übergebenen Vornamen hinein
    }

    public void enterLastName(String lastName) { // Methode: trägt den übergebenen Nachnamen in das Feld Last name ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))).sendKeys(lastName); // Wartet, bis das Nachname-Feld sichtbar ist, und schreibt erst dann den übergebenen Nachnamen hinein
    }

    public void enterEmail(String email) { // Methode: trägt die übergebene E-Mail-Adresse in das Feld Email ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys(email); // Wartet, bis das E-Mail-Feld sichtbar ist, und schreibt erst dann die übergebene E-Mail-Adresse hinein
    }

    public void enterPassword(String password) { // Methode: trägt das Passwort in das Passwort-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password"))).sendKeys(password); // Wartet, bis das Passwort-Feld sichtbar ist, und schreibt erst dann das Passwort hinein
    }

    public void enterConfirmPassword(String password) { // Methode: trägt das Passwort erneut in das Confirm password-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ConfirmPassword"))).sendKeys(password); // Wartet, bis das Confirm password-Feld sichtbar ist, und schreibt erst dann das Passwort erneut hinein
    }

    public void clickRegisterButton() { // Methode: klickt auf den Register-Button, um das Formular abzusenden
        wait.until(ExpectedConditions.elementToBeClickable(By.id("register-button"))).click(); // Wartet, bis der Register-Button klickbar ist, und klickt ihn erst dann an
    }

    public boolean isRegistrationSuccessful() { // Methode: prüft, ob die Registrierung erfolgreich abgeschlossen wurde
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("result"))) // Wartet, bis die Erfolgsmeldung sichtbar ist
                .getText() // Holt den sichtbaren Text der Erfolgsmeldung
                .contains("Your registration completed"); // Prüft, ob der erwartete Erfolgstext enthalten ist
    }

    public void clickLogoutButton() { // Methode: klickt auf den Logout-Link, um den Benutzer abzumelden
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out"))).click(); // Wartet, bis der Logout-Link klickbar ist, und klickt ihn erst dann an
    }

}
