package tests; // Diese Datei gehört zum Package tests für ausführbare Testklassen

import base.BaseTest; // Importiert die Basisklasse mit Browser-Setup, driver, Login-Hilfen und Cleanup
import org.junit.jupiter.api.Assertions; // Importiert Assertion-Methoden zum Prüfen von erwarteten Ergebnissen
import org.junit.jupiter.api.Test; // Importiert die @Test-Annotation, damit JUnit die Methode als Test erkennt
import pages.RegistrationPage; // Importiert die RegistrationPage mit allen Registrierungsaktionen
import pages.LoginPage; // Importiert die LoginPage mit allen Login-Aktionen
import pages.CartPage; // Importiert die CartPage mit Warenkorb- und Produktaktionen
import pages.CheckoutPage; // Importiert die CheckoutPage mit allen Checkout-Aktionen
import utils.RandomDataUtil; // Importiert die Hilfsklasse für dynamische Testdaten

public class E2ETest extends BaseTest { // E2E-Testklasse; erbt Browser-Setup, driver und Cleanup aus BaseTest

    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanCompleteFullPurchaseFlow() { // Test prüft den kompletten Kaufprozess von Registrierung bis Bestellabschluss

        RegistrationPage registrationPage = new RegistrationPage(driver); // Erstellt das Page Object für die Registrierung
        LoginPage loginPage = new LoginPage(driver); // Erstellt das Page Object für den Login
        CartPage cartPage = new CartPage(driver); // Erstellt das Page Object für Warenkorb- und Produktaktionen
        CheckoutPage checkoutPage = new CheckoutPage(driver); // Erstellt das Page Object für den Checkout

        String email = RandomDataUtil.generateEmail(); // Erzeugt eine eindeutige E-Mail-Adresse für die Registrierung
        String password = "Test123!"; // Speichert das Passwort zentral für Registrierung und Login

        String productName = "14.1-inch Laptop"; // Speichert den Produktnamen zentral
        int expectedQuantity = 1; // Speichert die erwartete Produktmenge im Warenkorb

        String country = "Germany"; // Speichert das Land zentral für Warenkorb und Billing Address
        String city = "Hamburg"; // Speichert die Stadt für die Billing Address
        String address = "Musterstraße 1"; // Speichert die Straße für die Billing Address
        String zip = "20095"; // Speichert die Postleitzahl für die Billing Address
        String phone = "017612345678"; // Speichert die Telefonnummer für die Billing Address

        // Registrierung
        registrationPage.openRegistrationPage(); // Öffnet die Registrierungsseite
        registrationPage.selectMaleGender(); // Wählt den Male-Radiobutton aus
        registrationPage.enterFirstName("Max"); // Trägt den Vornamen ein
        registrationPage.enterLastName("Mustermann"); // Trägt den Nachnamen ein
        registrationPage.enterEmail(email); // Trägt die dynamische E-Mail-Adresse ein
        registrationPage.enterPassword(password); // Trägt das Passwort ein
        registrationPage.enterConfirmPassword(password); // Bestätigt das Passwort
        registrationPage.clickRegisterButton(); // Sendet das Registrierungsformular ab

        Assertions.assertTrue(
                registrationPage.isRegistrationSuccessful(),
                "Registrierung war nicht erfolgreich"
        ); // Prüft, ob die Registrierung erfolgreich abgeschlossen wurde

        registrationPage.clickLogoutButton(); // Meldet den frisch registrierten Benutzer wieder ab

        // Authentifizierung
        loginPage.openLoginPage(); // Öffnet die Login-Seite
        loginPage.enterEmail(email); // Trägt dieselbe dynamische E-Mail-Adresse ein
        loginPage.enterPassword(password); // Trägt dasselbe Passwort ein
        loginPage.clickLoginButton(); // Klickt auf den Login-Button

        Assertions.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login war nicht erfolgreich"
        ); // Prüft, ob der Login erfolgreich war

        // Warenkorb
        cartPage.enterSearchText(productName); // Gibt den Produktnamen in das Suchfeld ein
        cartPage.selectLaptopSuggestion(); // Wählt den passenden Produktvorschlag aus
        cartPage.clickAddToCart(); // Fügt das Produkt dem Warenkorb hinzu
        cartPage.openShoppingCart(); // Öffnet den Warenkorb

        Assertions.assertTrue(
                cartPage.isProductInCart(productName),
                "Produkt wurde nicht zum Warenkorb hinzugefügt"
        ); // Prüft, ob das Produkt im Warenkorb vorhanden ist

        int actualQuantity = cartPage.getQuantityForProduct(productName); // Liest die tatsächliche Produktmenge im Warenkorb aus

        Assertions.assertEquals(
                expectedQuantity,
                actualQuantity,
                "Erwartet: " + expectedQuantity + ", aber war: " + actualQuantity
        ); // Prüft, ob die Produktmenge korrekt ist

        // Checkout vorbereiten
        cartPage.selectCountry(country); // Wählt das Land im Warenkorb aus
        cartPage.clickTermsButton(); // Bestätigt die Terms of Service Checkbox
        cartPage.clickCheckoutButton(); // Klickt auf den Checkout-Button

        // Checkout durchführen
        checkoutPage.fillBillingAddress(
                country,
                city,
                address,
                zip,
                phone
        ); // Füllt die Pflichtfelder der Billing Address aus

        checkoutPage.clickContinueBilling(); // Schließt den Billing-Step ab
        checkoutPage.clickContinueShippingAddress(); // Schließt den Shipping-Address-Step ab
        checkoutPage.clickContinueShippingMethod(); // Schließt den Shipping-Method-Step ab
        checkoutPage.clickContinuePaymentMethod(); // Schließt den Payment-Method-Step ab
        checkoutPage.clickContinuePaymentInformation(); // Schließt den Payment-Information-Step ab
        checkoutPage.clickConfirmOrder(); // Bestätigt die Bestellung

        Assertions.assertTrue(
                checkoutPage.isOrderSuccessfullyProcessed(),
                "Checkout war nicht erfolgreich"
        ); // Prüft, ob die Bestellung erfolgreich verarbeitet wurde

        String orderNumber = checkoutPage.getOrderNumber(); // Liest die Bestellnummer aus der Abschlussseite aus

        System.out.println("Order Number: " + orderNumber); // Gibt die Bestellnummer in der Konsole aus

        Assertions.assertFalse(
                orderNumber.isEmpty(),
                "Order Number ist leer - Checkout fehlgeschlagen"
        ); // Prüft, ob eine Bestellnummer vorhanden ist
    }
}