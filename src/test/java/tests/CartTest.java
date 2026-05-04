package tests; // Diese Datei gehört zum Package tests für Testklassen

import base.BaseTest; // Importiert unsere Basisklasse mit Browser-Setup und driver
import org.junit.jupiter.api.Test; // Importiert die @Test Annotation für JUnit
import pages.CartPage; // Importiert unsere Cart-Seitenklasse
import org.junit.jupiter.api.Assertions; // Importiert Assertion-Befehle zum Prüfen von Erwartungen

public class CartTest extends BaseTest { // Testklasse erbt Browser-Setup und driver aus BaseTest


    @Test // Markiert die folgende Methode als ausführbaren JUnit-Test
    public void userCanAddLaptopToCart() { // Test prüft, ob ein Laptop gesucht und ausgewählt werden kann

        CartPage cartPage = new CartPage(driver); // Erstellt das Seitenobjekt und übergibt den Browser

        cartPage.enterSearchText("laptop"); // Trägt laptop in das Suchfeld ein
        cartPage.selectLaptopSuggestion(); // Wählt den Vorschlag 14.1-inch Laptop aus
        cartPage.clickSearchButton(); // Klickt auf den Search-Button

        cartPage.clickAddToCart(); // Fügt den Laptop dem Warenkorb hinzu
        cartPage.openShoppingCart(); // Öffnet den Warenkorb oben rechts

        Assertions.assertTrue(
                cartPage.isProductInCart("14.1-inch Laptop")
        );

        cartPage.selectCountry("Germany"); // Ich wähle Germany aus meiner Dropdown Liste aus
        cartPage.clickTermsButton(); // Klickt auf TermsButton
        cartPage.clickCheckoutButton(); // Auf Checkout klicken





    }
}