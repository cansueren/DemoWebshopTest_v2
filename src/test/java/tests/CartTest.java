package tests;                                      // Diese Datei gehört zum Package tests für ausführbare Testklassen

import base.BaseTest;                               // Importiert die Basisklasse mit Browser-Setup, Login-Hilfsmethoden und Cleanup
import org.junit.jupiter.api.Assertions;            // Importiert Assertion-Methoden, um erwartete und tatsächliche Ergebnisse zu prüfen
import org.junit.jupiter.api.Test;                  // Importiert die @Test-Annotation, damit JUnit die Methode als Test erkennt
import pages.CartPage;                              // Importiert die CartPage, über die Warenkorb- und Produktaktionen ausgeführt werden

public class CartTest extends BaseTest {            // CartTest erbt den WebDriver und gemeinsame Testlogik aus BaseTest

    @Test
    public void userCanAddLaptopToCart() {

        // Login mit festem Test-User (inkl. Cart Reset)
        loginAsDefaultUser();

        // Page Objekt initialisieren
        CartPage cartPage = new CartPage(driver);

        // Produktname zentral definieren
        String productName = "14.1-inch Laptop";
        int expectedQuantity = 1; // Speichert die erwartete Produktmenge im Warenkorb

        // Produkt suchen und auswählen
        cartPage.enterSearchText(productName);
        cartPage.selectLaptopSuggestion();

        // Produkt in den Warenkorb legen
        cartPage.clickAddToCart();

        // Warenkorb öffnen
        cartPage.openShoppingCart();

        // Prüfen, ob Produkt im Warenkorb ist
        Assertions.assertTrue(
                cartPage.isProductInCart(productName),
                "Produkt wurde nicht zum Warenkorb hinzugefügt"
        );

        // Menge auslesen
        int actualQuantity = cartPage.getQuantityForProduct(productName);

        Assertions.assertEquals(
                expectedQuantity,
                actualQuantity,
                "Erwartet: " + expectedQuantity + ", aber war: " + actualQuantity
        );
    }
}