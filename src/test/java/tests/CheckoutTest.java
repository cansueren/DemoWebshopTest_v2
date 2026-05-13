package tests;                                      // Diese Datei gehört zum Package tests für ausführbare Testklassen

import base.BaseTest;                               // Importiert die Basistestklasse mit Browser-Setup, Login-Hilfsmethoden und Cleanup
import org.junit.jupiter.api.Assertions;            // Importiert Assertion-Befehle zum Prüfen von erwarteten Ergebnissen
import org.junit.jupiter.api.Test;                  // Importiert @Test, damit JUnit diese Methode als Test erkennt
import pages.CartPage;                              // Importiert die CartPage mit Warenkorb- und Produktaktionen
import pages.CheckoutPage;                          // Importiert die CheckoutPage mit allen Checkout-Aktionen

public class CheckoutTest extends BaseTest {        // Testklasse für den Checkout-Prozess, erbt den driver aus BaseTest

    @Test                                           // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanCheckoutSuccessfully() {     // Test prüft, ob ein Benutzer erfolgreich eine Bestellung abschließen kann

        loginAsDefaultUser(); // Meldet den festen Testbenutzer an und setzt den Warenkorb zurück

        CartPage cartPage = new CartPage(driver); // Erstellt das CartPage-Objekt und übergibt den geöffneten Browser
        CheckoutPage checkoutPage = new CheckoutPage(driver); // Erstellt das CheckoutPage-Objekt und übergibt den geöffneten Browser

        String productName = "14.1-inch Laptop"; // Speichert den Produktnamen zentral
        String country = "Germany"; // Speichert das Land zentral für Warenkorb und Billing Address
        String city = "Hamburg"; // Speichert die Stadt für die Billing Address
        String address = "Musterstraße 1"; // Speichert die Straße für die Billing Address
        String zip = "20095"; // Speichert die Postleitzahl für die Billing Address
        String phone = "017612345678"; // Speichert die Telefonnummer für die Billing Address

        cartPage.enterSearchText(productName); // Gibt den Produktnamen in das Suchfeld ein
        cartPage.selectLaptopSuggestion(); // Wählt den passenden Produktvorschlag aus
        cartPage.clickAddToCart(); // Fügt das Produkt dem Warenkorb hinzu
        cartPage.openShoppingCart(); // Öffnet den Warenkorb

        cartPage.selectCountry(country); // Wählt das Land im Warenkorb aus
        cartPage.clickTermsButton(); // Bestätigt die Terms of Service Checkbox
        cartPage.clickCheckoutButton(); // Klickt auf den Checkout-Button

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

        System.out.println("Order Nummer: " + orderNumber); // Gibt die Bestellnummer in der Konsole aus

        Assertions.assertFalse(
                orderNumber.isEmpty(),
                "Order Nummer ist leer – Checkout fehlgeschlagen"
        ); // Prüft, ob eine Bestellnummer vorhanden ist
    }
}