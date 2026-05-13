package pages;                                          // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                          // Importiert Locator-Werkzeuge, um Elemente auf der Webseite zu finden
import org.openqa.selenium.WebDriver;                   // Importiert den Browser-Treiber, damit der Konstruktor den driver entgegennehmen kann

public class RegistrationPage extends BasePage {        // RegistrationPage erbt gemeinsame Selenium-Hilfsmethoden aus BasePage

    public RegistrationPage(WebDriver driver) {         // Konstruktor erhält den geöffneten Browser beim Erstellen der Page
        super(driver);                                  // Übergibt den driver an die BasePage, damit dort driver und wait initialisiert werden
    }

    public void openRegistrationPage() { // Öffnet die Registrierungsseite über den Register-Link
        click(By.linkText("Register")); // Nutzt die BasePage-click-Methode und wartet automatisch, bis der Link klickbar ist
    }

    public void selectMaleGender() { // Methode: wählt die Option Male im Registrierungsformular aus
        click(By.id("gender-male")); // Nutzt die BasePage-click-Methode und klickt den Male-Radiobutton stabil an
    }

    public void enterFirstName(String firstName) { // Methode: trägt den übergebenen Vornamen in das Feld First name ein
        type(By.id("FirstName"), firstName); // Nutzt die BasePage-type-Methode und wartet automatisch, bis das Feld sichtbar ist
    }

    public void enterLastName(String lastName) { // Methode: trägt den übergebenen Nachnamen in das Feld Last name ein
        type(By.id("LastName"), lastName); // Nutzt die BasePage-type-Methode und trägt den Nachnamen stabil ein
    }

    public void enterEmail(String email) { // Methode: trägt die übergebene E-Mail-Adresse in das Feld Email ein
        type(By.id("Email"), email); // Nutzt die BasePage-type-Methode und trägt die E-Mail-Adresse stabil ein
    }

    public void enterPassword(String password) { // Methode: trägt das Passwort in das Passwort-Feld ein
        type(By.id("Password"), password); // Nutzt die BasePage-type-Methode und trägt das Passwort stabil ein
    }

    public void enterConfirmPassword(String password) { // Methode: trägt das Passwort erneut in das Confirm password-Feld ein
        type(By.id("ConfirmPassword"), password); // Nutzt die BasePage-type-Methode und trägt das Bestätigungspasswort stabil ein
    }

    public void clickRegisterButton() { // Methode: klickt auf den Register-Button, um das Formular abzusenden
        click(By.id("register-button")); // Nutzt die BasePage-click-Methode und klickt den Register-Button stabil an
    }

    public boolean isRegistrationSuccessful() { // Methode: prüft, ob die Registrierung erfolgreich abgeschlossen wurde
        return getText(By.className("result")) // Nutzt die BasePage-getText-Methode und wartet automatisch auf die Erfolgsmeldung
                .contains("Your registration completed"); // Prüft, ob der erwartete Erfolgstext enthalten ist
    }

    public void clickLogoutButton() { // Methode: klickt auf den Logout-Link, um den Benutzer abzumelden
        click(By.linkText("Log out")); // Nutzt die BasePage-click-Methode und klickt den Logout-Link stabil an
    }
}