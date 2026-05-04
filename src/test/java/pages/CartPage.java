package pages; // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By; // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver; // Importiert den Browser-Treiber für diese Seitenklasse
import org.openqa.selenium.WebElement; // Importiert
import org.openqa.selenium.support.ui.Select; // Importiert Selenium Befehle, für Select zb
import java.util.List; // für List


public class CartPage { // Seitenklasse für Warenkorb- und Produktaktionen

    private WebDriver driver; // Speichert den Browser dieser Seite; private nur innerhalb dieser Klasse nutzbar


    public CartPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
    }

    public void enterSearchText(String productName) { // Methode: trägt den übergebenen Suchbegriff in das Suchfeld ein
        driver.findElement(By.id("small-searchterms")).sendKeys(productName); // Selenium-Befehl: findet das Suchfeld über id und schreibt den Text hinein
    }

    public void selectLaptopSuggestion() { // Methode: wählt den Vorschlag 14.1-inch Laptop aus der Suchliste aus
        driver.findElement(By.linkText("14.1-inch Laptop")).click(); // Selenium-Befehl: findet den Vorschlag über sichtbaren Text und klickt darauf
    }

    public void clickSearchButton() { // Methode: klickt auf den Search-Button
        driver.findElement(By.cssSelector("input[value='Search']")).click(); // Selenium-Befehl: findet den Button über value und klickt darauf
    }

    public void clickAddToCart() { // Methode: klickt auf den Add-to-cart-Button des ausgewählten Laptops
        driver.findElement(By.cssSelector("input[value='Add to cart']")).click(); // klickt den Button über den value-Text
    }

    public void openShoppingCart() { // Methode: öffnet den Warenkorb oben rechts
        driver.findElement(By.linkText("Shopping cart")).click(); // Selenium-Befehl: findet den Link über sichtbaren Text und klickt darauf
    }

    public void selectCountry(String countryName) { // Methode: wählt ein Land aus dem Country-Dropdown aus
        WebElement countryDropdown = driver.findElement(By.id("CountryId")); // Ich nutze einen Locator "By.Id", um ein WebElement zu finden
        Select select = new Select(countryDropdown); // Ich wrappe das WebElement in eine Select-Klasse
        select.selectByVisibleText(countryName); // Ich führe eine Dropdown-Aktion aus
    }

    public void clickTermsButton() { // Methode: Auf Term bestätigen klicken
        driver.findElement(By.id("termsofservice")).click(); // Findet den Terms-Button und klickt drauf

    }

    public void clickCheckoutButton() { // Methode: auf Checkout klicken
        driver.findElement(By.id("checkout")).click(); // Checkout Button finden und raufklicken

    }

    public boolean isProductInCart(String productName) { // prüft, ob Produkt im Warenkorb ist

        List<WebElement> products = driver.findElements(By.className("product-name")); // holt alle Produktnamen im Warenkorb


        for (WebElement product : products) { // geht jedes Produkt durch

            if (product.getText().contains(productName)) { // prüft, ob der Text dem gesuchten Produkt entspricht
                return true; // Produkt gefunden
            }
        }

        return false; // Produkt nicht gefunden
    }




}