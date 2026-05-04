package pages;

import org.openqa.selenium.By; // Import Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver; // Import den Browser-Treiber für diese Seitenklasse (Browsersteuerung)
import org.openqa.selenium.WebElement; // Import HTML-Element
import org.openqa.selenium.support.ui.Select; // Importiert Selenium Befehle, für Select zb (Dropdown-Klasse)


public class CheckoutPage {

    private WebDriver driver; // Speichert den Browser dieser Seite; private nur innerhalb dieser Klasse nutzbar

    public CheckoutPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
    }

    public void selectBillingCountry(String countryName) { // Methode: wählt Land im Checkout-Dropdown aus
        WebElement countryDropdown = driver.findElement(By.id("BillingNewAddress_CountryId")); // findet das <select>-Element über ID
        Select select = new Select(countryDropdown); // erstellt Select-Objekt für Dropdown
        select.selectByVisibleText(countryName); // wählt z.B. "Germany"
    }


}
