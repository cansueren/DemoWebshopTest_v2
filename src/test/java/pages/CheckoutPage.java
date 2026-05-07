package pages; // Package für Page Object Klassen

import org.openqa.selenium.By; // Locator zum Finden von Elementen
import org.openqa.selenium.WebDriver; // Browser-Steuerung
import org.openqa.selenium.WebElement; // HTML-Element
import org.openqa.selenium.support.ui.Select; // Für Dropdowns
import org.openqa.selenium.support.ui.WebDriverWait; // Für explizite Wartezeiten
import org.openqa.selenium.support.ui.ExpectedConditions; // Bedingungen für Waits

import java.time.Duration; // Zeitangabe für Wait (z. B. 10 Sekunden)

public class CheckoutPage {

    // Speichert die aktuelle Browser-Instanz
    private WebDriver driver;

    // Konstruktor bekommt den Driver aus dem Test übergeben
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private void selectBillingCountry(String countryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement countryDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_CountryId"))
        );

        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }

    private void enterCity(String city) {
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys(city);
    }

    private void enterAddress(String address) {
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(address);
    }

    private void enterZip(String zip) {
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(zip);
    }

    private void enterPhone(String phone) {
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(phone);
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

    public void selectNewAddressIfAvailable() {
        if (!driver.findElements(By.id("billing-address-select")).isEmpty()) {
            Select select = new Select(driver.findElement(By.id("billing-address-select")));
            select.selectByVisibleText("New Address");
        }
    }


    // Continue Buttons Checkout


    // Klickt auf den Continue-Button im Billing Step
    public void clickContinueBilling() {

        // Wartet bis zu 10 Sekunden, bis das Element verfügbar ist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wartet gezielt darauf, dass der Continue Button klickbar ist
        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input.new-address-next-step-button") // KORREKTER Selector
                )
        );

        // Klickt auf den Button
        continueButton.click();
    }

    public void clickContinueShippingAddress() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#shipping-buttons-container input.button-1") // KORREKTER Selector
                )
        );

        continueButton.click();
    }

    public void clickContinueShippingMethod() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#shipping-method-buttons-container input.button-1")
                )
        );

        continueButton.click();
    }

    public void clickContinuePaymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#payment-method-buttons-container input.button-1")
                )
        );

        continueButton.click();
    }

    public void clickContinuePaymentInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#payment-info-buttons-container input.button-1")
                )
        );

        continueButton.click();
    }

    public void clickConfirmOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("#confirm-order-buttons-container input.button-1")
                )
        );

        confirmButton.click();
    }

    public String getOrderNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement orderNumberElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//li[contains(text(),'Order number')]")
                )
        );

        String orderText = orderNumberElement.getText();

        // nur Zahl zurückgeben
        return orderText.replaceAll("[^0-9]", "");
    }

    public boolean isOrderSuccessfullyProcessed() {
        // Erstellt einen expliziten Wait, damit Selenium maximal 10 Sekunden auf die Erfolgsmeldung wartet
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wartet, bis die Erfolgsmeldung auf der Bestellabschluss-Seite sichtbar ist
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//strong[contains(text(),'successfully processed')]")
                )
        );

        // Prüft, ob der Text der Erfolgsmeldung den erwarteten Erfolgsinhalt enthält
        return successMessage.getText().contains("successfully processed");
    }
}