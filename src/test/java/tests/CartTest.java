package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;

public class CartTest extends BaseTest {

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