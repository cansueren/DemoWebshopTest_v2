package pages; // Package für Page Object Klassen

import org.openqa.selenium.By; // Importiert Locator-Werkzeuge zum Finden von Elementen
import org.openqa.selenium.WebDriver; // Importiert den Browser-Treiber, damit der Konstruktor den driver entgegennehmen kann
import org.openqa.selenium.WebElement; // Ermöglicht das Arbeiten mit gefundenen HTML-Elementen
import org.openqa.selenium.support.ui.ExpectedConditions; // Importiert spezielle Wait-Bedingungen
import org.openqa.selenium.support.ui.Select; // Importiert Select für Dropdowns mit gespeicherten Adressen

import java.util.List; // Ermöglicht das Arbeiten mit Listen von WebElementen

public class CheckoutPage extends BasePage { // CheckoutPage erbt gemeinsame Selenium-Hilfsmethoden aus BasePage

    public CheckoutPage(WebDriver driver) { // Konstruktor bekommt den Driver aus dem Test übergeben
        super(driver); // Übergibt den driver an die BasePage, damit dort driver und wait initialisiert werden
    }

    private void selectBillingCountry(String countryName) { // Methode: wählt das Land in der Billing Address aus
        selectByVisibleText(By.id("BillingNewAddress_CountryId"), countryName); // Nutzt die BasePage-Dropdown-Methode
    }

    private void enterCity(String city) { // Methode: trägt die Stadt in das Billing-City-Feld ein
        type(By.id("BillingNewAddress_City"), city); // Nutzt die BasePage-type-Methode und wartet automatisch auf Sichtbarkeit
    }

    private void enterAddress(String address) { // Methode: trägt die Adresse in das Billing-Address-Feld ein
        type(By.id("BillingNewAddress_Address1"), address); // Nutzt die BasePage-type-Methode und trägt die Adresse ein
    }

    private void enterZip(String zip) { // Methode: trägt die Postleitzahl ein
        type(By.id("BillingNewAddress_ZipPostalCode"), zip); // Nutzt die BasePage-type-Methode und trägt die PLZ ein
    }

    private void enterPhone(String phone) { // Methode: trägt die Telefonnummer ein
        type(By.id("BillingNewAddress_PhoneNumber"), phone); // Nutzt die BasePage-type-Methode und trägt die Telefonnummer ein
    }

    public void fillBillingAddress(String country, String city, String address, String zip, String phone) { // Methode: füllt alle Pflichtfelder der Billing Address aus
        selectNewAddressIfAvailable(); // Wählt New Address aus, falls gespeicherte Adressen vorhanden sind
        selectBillingCountry(country); // Wählt das Land aus
        enterCity(city); // Trägt die Stadt ein
        enterAddress(address); // Trägt die Adresse ein
        enterZip(zip); // Trägt die Postleitzahl ein
        enterPhone(phone); // Trägt die Telefonnummer ein
    }

    private void selectNewAddressIfAvailable() { // Methode: wählt bewusst New Address aus, falls ein Billing-Address-Dropdown vorhanden ist

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-select")),
                ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_CountryId"))
        )); // Wartet, bis entweder das gespeicherte Adress-Dropdown oder direkt das neue Adressformular sichtbar ist

        List<WebElement> addressDropdowns = driver.findElements(By.id("billing-address-select")); // Prüft, ob ein Billing-Address-Dropdown vorhanden ist

        if (addressDropdowns.isEmpty()) { // Wenn kein Dropdown vorhanden ist, ist das neue Adressformular bereits sichtbar
            return; // Dann muss nichts ausgewählt werden
        }

        WebElement addressDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("billing-address-select"))
        ); // Wartet, bis das Billing-Address-Dropdown sichtbar ist

        Select select = new Select(addressDropdown); // Übergibt das Dropdown an die Selenium-Select-Klasse

        boolean newAddressFound = false; // Speichert, ob die Option New Address gefunden wurde

        for (WebElement option : select.getOptions()) { // Geht alle Optionen im Dropdown durch

            String optionText = option.getText().trim(); // Liest den sichtbaren Text der aktuellen Option aus

            if (optionText.equalsIgnoreCase("New Address")) { // Prüft, ob die Option New Address vorhanden ist
                select.selectByVisibleText(optionText); // Wählt New Address aus
                newAddressFound = true; // Merkt sich, dass New Address gefunden wurde
                break; // Beendet die Schleife
            }
        }

        if (!newAddressFound) { // Falls New Address nicht vorhanden ist
            throw new RuntimeException("Die Option 'New Address' wurde im Billing-Address-Dropdown nicht gefunden"); // Bricht den Test bewusst ab
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("BillingNewAddress_CountryId")
        )); // Wartet, bis nach Auswahl von New Address das neue Adressformular sichtbar ist
    }

    public void clickContinueBilling() { // Methode: klickt auf Continue im Billing-Step
        click(By.cssSelector("#billing-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public void clickContinueShippingAddress() { // Methode: klickt auf Continue im Shipping-Address-Step
        click(By.cssSelector("#shipping-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public void clickContinueShippingMethod() { // Methode: klickt auf Continue im Shipping-Method-Step
        click(By.cssSelector("#shipping-method-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public void clickContinuePaymentMethod() { // Methode: klickt auf Continue im Payment-Method-Step
        click(By.cssSelector("#payment-method-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public void clickContinuePaymentInformation() { // Methode: klickt auf Continue im Payment-Information-Step
        click(By.cssSelector("#payment-info-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public void clickConfirmOrder() { // Methode: bestätigt die Bestellung im Confirm-Order-Step
        click(By.cssSelector("#confirm-order-buttons-container input.button-1")); // Nutzt die BasePage-click-Methode
    }

    public String getOrderNumber() { // Methode: liest die Bestellnummer aus der Bestellabschluss-Seite aus

        String orderText = getText(By.xpath("//li[contains(text(),'Order number')]")); // Liest den kompletten Order-Number-Text aus

        return orderText.replaceAll("[^0-9]", ""); // Entfernt alles außer Zahlen und gibt nur die Bestellnummer zurück
    }

    public boolean isOrderSuccessfullyProcessed() { // Methode: prüft, ob die Bestellung erfolgreich verarbeitet wurde

        return getText(By.xpath("//strong[contains(text(),'successfully processed')]"))
                .contains("successfully processed"); // Prüft, ob der erwartete Erfolgstext enthalten ist
    }
}