package tests; // Test Package

import base.BaseTest; // Basis mit Setup + Login
import org.junit.jupiter.api.Test; // JUnit Test Annotation
import org.junit.jupiter.api.Assertions; // Assertions
import pages.CartPage; // Warenkorb Page
import pages.CheckoutPage; // Checkout Page


public class CheckoutTest extends BaseTest {

    @Test
    public void userCanCheckoutSuccessfully() {

        // Login mit festem Test-User
        loginAsDefaultUser();

        // Produkt in den Warenkorb legen
        CartPage cartPage = new CartPage(driver);

        cartPage.enterSearchText("laptop");
        cartPage.selectLaptopSuggestion();
        cartPage.clickAddToCart();
        cartPage.openShoppingCart();

        // Zum Checkout gehen
        cartPage.selectCountry("Germany");
        cartPage.clickTermsButton();
        cartPage.clickCheckoutButton();

        // Checkout Page initialisieren
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Billing Address ausfüllen (Pflichtfelder)
        checkoutPage.fillBillingAddress(
                "Germany",
                "Hamburg",
                "Musterstraße 1",
                "20095",
                "017612345678"
        );

        // Weiter zum nächsten Schritt
        checkoutPage.clickContinueBilling();

        // Prüfen, ob wir im nächsten Step sind (Shipping Address)
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

        // Ausgabe
        System.out.println("Order Nummer: " + orderNumber);

        // Assertion
        Assertions.assertFalse(
                orderNumber.isEmpty(),
                "Order Nummer ist leer – Checkout fehlgeschlagen"
        );

    }

}
