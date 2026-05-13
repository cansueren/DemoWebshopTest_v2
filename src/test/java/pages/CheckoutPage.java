package pages;                                              // Package für Page Object Klassen

import org.openqa.selenium.By;                              // Locator zum Finden von Elementen
import org.openqa.selenium.WebDriver;                       // Browser-Steuerung
import org.openqa.selenium.WebElement;                      // HTML-Element
import org.openqa.selenium.support.ui.Select;               // Für Dropdowns
import org.openqa.selenium.support.ui.WebDriverWait;        // Für explizite Wartezeiten
import org.openqa.selenium.support.ui.ExpectedConditions;   // Bedingungen für Waits

import java.time.Duration;                                  // Zeitangabe für Wait (z. B. 10 Sekunden)


public class CheckoutPage {

    private WebDriver driver;                               // Speichert die aktuelle Browser-Instanz
    private WebDriverWait wait;                             // Speichert den expliziten Wait, damit alle Checkout-Methoden gezielt warten können


    public CheckoutPage(WebDriver driver) { // Konstruktor bekommt den Driver aus dem Test übergeben
        this.driver = driver; // Übergibt den Browser an die Klassenvariable
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Erstellt einen zentralen Wait mit maximal 10 Sekunden Wartezeit
    }

    private void selectBillingCountry(String countryName) { // Methode: wählt das Land in der Billing Address aus

        WebElement countryDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_CountryId"))
        ); // Wartet, bis das Country-Dropdown sichtbar ist

        Select select = new Select(countryDropdown); // Übergibt das Dropdown an die Selenium-Select-Klasse
        select.selectByVisibleText(countryName); // Wählt das Land anhand des sichtbaren Textes aus
    }

    private void enterCity(String city) { // Methode: trägt die Stadt in das Billing-City-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_City")))
                .sendKeys(city); // Wartet, bis das Stadt-Feld sichtbar ist, und trägt die Stadt ein
    }

    private void enterAddress(String address) { // Methode: trägt die Adresse in das Billing-Address-Feld ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_Address1")))
                .sendKeys(address); // Wartet, bis das Adressfeld sichtbar ist, und trägt die Adresse ein
    }

    private void enterZip(String zip) { // Methode: trägt die Postleitzahl ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_ZipPostalCode")))
                .sendKeys(zip); // Wartet, bis das PLZ-Feld sichtbar ist, und trägt die Postleitzahl ein
    }

    private void enterPhone(String phone) { // Methode: trägt die Telefonnummer ein
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_PhoneNumber")))
                .sendKeys(phone); // Wartet, bis das Telefonnummer-Feld sichtbar ist, und trägt die Telefonnummer ein
    }

    // Kombinierte Methode: füllt alle Pflichtfelder aus
    public void fillBillingAddress(String country, String city, String address, String zip, String phone) {
        selectNewAddressIfAvailable();
        selectBillingCountry(country);
        enterCity(city);
        enterAddress(address);
        enterZip(zip);
        enterPhone(phone);
    }

    public void selectNewAddressIfAvailable() { // Methode: wählt "New Address" aus, falls ein Billing-Address-Dropdown vorhanden ist

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-select")),
                ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_CountryId"))
        )); // Wartet, bis entweder das gespeicherte Adress-Dropdown oder direkt das neue Adressformular sichtbar ist

        if (!driver.findElements(By.id("billing-address-select")).isEmpty()) { // Prüft, ob ein Dropdown für gespeicherte Adressen vorhanden ist

            WebElement addressDropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-select"))
            ); // Wartet, bis das Dropdown sichtbar ist

            Select select = new Select(addressDropdown); // Übergibt das Dropdown an die Select-Klasse
            select.selectByVisibleText("New Address"); // Wählt bewusst New Address aus

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("BillingNewAddress_CountryId")
            )); // Wartet, bis nach Auswahl von New Address das neue Adressformular sichtbar ist
        }
    }


    // Continue Buttons Checkout


    public void clickContinueBilling() { // Methode: klickt auf Continue im Billing-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#billing-buttons-container input.button-1")
        )).click(); // Wartet, bis der Billing-Continue-Button klickbar ist, und klickt ihn
    }

    public void clickContinueShippingAddress() { // Methode: klickt auf Continue im Shipping-Address-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#shipping-buttons-container input.button-1")
        )).click(); // Wartet, bis der Shipping-Address-Continue-Button klickbar ist, und klickt ihn
    }

    public void clickContinueShippingMethod() { // Methode: klickt auf Continue im Shipping-Method-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#shipping-method-buttons-container input.button-1")
        )).click(); // Wartet, bis der Shipping-Method-Continue-Button klickbar ist, und klickt ihn
    }

    public void clickContinuePaymentMethod() { // Methode: klickt auf Continue im Payment-Method-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#payment-method-buttons-container input.button-1")
        )).click(); // Wartet, bis der Payment-Method-Continue-Button klickbar ist, und klickt ihn
    }

    public void clickContinuePaymentInformation() { // Methode: klickt auf Continue im Payment-Information-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#payment-info-buttons-container input.button-1")
        )).click(); // Wartet, bis der Payment-Information-Continue-Button klickbar ist, und klickt ihn
    }

    public void clickConfirmOrder() { // Methode: bestätigt die Bestellung im Confirm-Order-Step
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#confirm-order-buttons-container input.button-1")
        )).click(); // Wartet, bis der Confirm-Button klickbar ist, und klickt ihn
    }

    public String getOrderNumber() { // Methode: liest die Bestellnummer aus der Bestellabschluss-Seite aus

        WebElement orderNumberElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[contains(text(),'Order number')]")
                )
        ); // Wartet, bis das Element mit der Order Number sichtbar ist

        String orderText = orderNumberElement.getText(); // Holt den kompletten Text, z. B. "Order number: 123456"

        return orderText.replaceAll("[^0-9]", ""); // Entfernt alles außer Zahlen und gibt nur die Bestellnummer zurück
    }

    public boolean isOrderSuccessfullyProcessed() { // Methode: prüft, ob die Bestellung erfolgreich verarbeitet wurde

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//strong[contains(text(),'successfully processed')]")
                )
        ); // Wartet, bis die Erfolgsmeldung sichtbar ist

        return successMessage.getText().contains("successfully processed"); // Prüft, ob der erwartete Erfolgstext enthalten ist
    }


}