package tests; // Test Package

import base.BaseTest; // Basis mit Setup + Login
import org.junit.jupiter.api.Test; // JUnit Test Annotation
import org.junit.jupiter.api.Assertions; // Assertions
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage; // Warenkorb Page
import pages.CheckoutPage; // Checkout Page

import java.time.Duration;

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


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//strong[contains(text(),'successfully processed')]")
                )
        );

        Assertions.assertTrue(
                successMessage.getText().contains("successfully processed"),
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
