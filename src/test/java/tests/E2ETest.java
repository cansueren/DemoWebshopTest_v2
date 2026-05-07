package tests; // Diese Datei gehört zum Package tests für Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup und driver
import org.junit.jupiter.api.Assertions;
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
        CheckoutPage checkoutPage = new CheckoutPage(driver); // Erstellt das Seitenobjekt für Checkout

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

        String productName = "14.1-inch Laptop";
        int expectedQuantity = 1;

        // Ich prüfe, ob das Produkt im Warenkorb ist
        Assertions.assertTrue(
                cartPage.isProductInCart(productName),
                "Produkt wurde nicht zum Warenkorb hinzugefügt"
        );

        // Tatsächliche Menge überprüfen
        int actualQuantity = cartPage.getQuantityForProduct(productName);

        // Ich vergleiche erwartete und tatsächliche Menge
        Assertions.assertEquals(
                expectedQuantity,
                actualQuantity,
                "Erwartet: " + expectedQuantity + " aber war: " + actualQuantity
        );


        cartPage.selectCountry("Germany"); // wählt Germany aus
        cartPage.clickTermsButton(); // Klickt TermsButton
        cartPage.clickCheckoutButton(); // Checkout klicken

        // Checkout
        checkoutPage.fillBillingAddress(
                "Germany",
                "Hamburg",
                "Musterstraße 1",
                "20095",
                "017612345678"
        );

        checkoutPage.clickContinueBilling();

        Assertions.assertTrue(
                driver.getPageSource().contains("Shipping address"),
                "Billing Address konnte nicht abgeschlossen werden"
        );

        checkoutPage.clickContinueShippingAddress();
        checkoutPage.clickContinueShippingMethod();
        checkoutPage.clickContinuePaymentMethod();
        checkoutPage.clickContinuePaymentInformation();
        checkoutPage.clickConfirmOrder();

        Assertions.assertTrue(
                checkoutPage.isOrderSuccessfullyProcessed(),
                "Checkout war nicht erfolgreich"
        );

        String orderNumber = checkoutPage.getOrderNumber();

        System.out.println("Order Number: " + orderNumber);

        Assertions.assertFalse(
                orderNumber.isEmpty(),
                "Order Number ist leer - Checkout fehlgeschlagen"
        );
    }
}