package tests; // Diese Datei gehört zum Package tests für Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup und driver
import org.junit.jupiter.api.Test; // Importiert die @Test Annotation für JUnit
import pages.RegistrationPage; // Importiert die Registrierungs-Seitenklasse
import pages.LoginPage; // Importiert die Login-Seitenklasse
import pages.CartPage; // Importiert die Warenkorb-Seitenklasse
import pages.CheckoutPage; // Importiert die Checkout-Seitenklasse

public class E2ETest extends BaseTest { // Testklasse erbt Browser-Setup und driver aus BaseTest

    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanCompleteFullPurchaseFlow() { // Test prüft den kompletten Ablauf von Registrierung bis Warenkorb

        RegistrationPage registrationPage = new RegistrationPage(driver); // Erstellt das Seitenobjekt für Registrierung
        LoginPage loginPage = new LoginPage(driver); // Erstellt das Seitenobjekt für Login
        CartPage cartPage = new CartPage(driver); // Erstellt das Seitenobjekt für Warenkorb

        String email = "Max.mustermann" + System.currentTimeMillis() + "@test.de"; // Erstellt eine dynamische eindeutige Email
        String password = "Test123"; // Erstellt ein Passwort für Registrierung und Login

        // Registrierung
        registrationPage.openRegistrationPage(); // Öffnet die Registrierungsseite
        registrationPage.selectMaleGender(); // Wählt Male aus
        registrationPage.enterFirstName("Max"); // Trägt Vorname ein
        registrationPage.enterLastName("Mustermann"); // Trägt Nachname ein
        registrationPage.enterEmail(email); // Trägt dynamische Email ein
        registrationPage.enterPassword(password); // Trägt Passwort ein
        registrationPage.enterConfirmPassword(password); // Bestätigt Passwort
        registrationPage.clickRegisterButton(); // Klickt auf Register
        registrationPage.clickLogoutButton(); // ausloggen

        // Authentifizierung
        loginPage.openLoginPage(); // Öffnet Login-Seite
        loginPage.enterEmail(email); // Trägt dieselbe dynamische Email ein
        loginPage.enterPassword(password); // Trägt dasselbe Passwort ein
        loginPage.clickLoginButton(); // Klickt auf Login

        // Warenkorb
        cartPage.enterSearchText("laptop"); // Sucht nach Laptop
        cartPage.selectLaptopSuggestion(); // Wählt Laptop aus Vorschlagsliste
        cartPage.clickAddToCart(); // Fügt Produkt dem Warenkorb hinzu
        cartPage.openShoppingCart(); // Öffnet Warenkorb
        cartPage.selectCountry("Germany"); // wählt Germany aus
        cartPage.clickTermsButton(); // Klickt auf TermsButton
        cartPage.clickCheckoutButton(); // Auf Checkout klicken
    }
}