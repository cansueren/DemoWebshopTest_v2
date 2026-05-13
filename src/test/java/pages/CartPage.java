package pages;                                              // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                              // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver;                       // Importiert den Browser-Treiber für diese Seitenklasse
import org.openqa.selenium.WebElement;                      // Ermöglicht das Speichern und Verwenden eines gefundenen HTML-Elements
import org.openqa.selenium.support.ui.Select;               // Importiert Selenium Befehle, für Select zb

import java.util.List;                                      // für List

import org.openqa.selenium.support.ui.WebDriverWait;        // Ermöglicht explizites Warten auf bestimmte Bedingungen
import org.openqa.selenium.support.ui.ExpectedConditions;   // Stellt vordefinierte Wartebedingungen bereit, z. B. sichtbar oder klickbar

import java.time.Duration;                                  // Definiert die max. Wartezeit für den WebDriverWait


public class CartPage { // Seitenklasse für Warenkorb- und Produktaktionen

    private WebDriver driver; // Speichert den Browser dieser Seite; private nur innerhalb dieser Klasse nutzbar
    private WebDriverWait wait; // Speichert den expliziten Wait, damit alle Methoden gezielt auf Elemente warten können


    public CartPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Erstellt einen expliziten Wait mit maximal 10 Sekunden Wartezeit
    }

    public void enterSearchText(String productName) { // Methode: trägt den Suchbegriff in das Suchfeld ein
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("small-searchterms"))); // Wartet, bis das Suchfeld sichtbar ist

        searchField.clear(); // Leert das Suchfeld vorsichtshalber, falls bereits Text enthalten ist
        searchField.sendKeys(productName); // Trägt den übergebenen Produktnamen in das Suchfeld ein
    }

    public void selectLaptopSuggestion() { // Methode: wählt den Produktvorschlag "14.1-inch Laptop" aus
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("14.1-inch Laptop"))).click(); // Wartet, bis der Produktlink klickbar ist, und klickt ihn an
    }

    public void clickAddToCart() { // Methode: klickt auf den Add-to-cart-Button und wartet auf die Erfolgsmeldung

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[value='Add to cart']")
        )).click(); // Wartet, bis der Add-to-cart-Button klickbar ist, und klickt ihn erst dann an

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".bar-notification.success")
        )); // Wartet, bis die grüne Erfolgsmeldung erscheint, dass das Produkt hinzugefügt wurde
    }

    public void openShoppingCart() { // Methode: öffnet den Warenkorb über den Warenkorb-Link
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ico-cart"))).click(); // Wartet, bis der Warenkorb-Link klickbar ist, und klickt ihn erst dann an
    }

    public void selectCountry(String countryName) { // Methode: wählt ein Land aus dem Country-Dropdown im Warenkorb aus
        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CountryId"))); // Wartet, bis das Country-Dropdown sichtbar ist

        Select select = new Select(countryDropdown); // Übergibt das gefundene Dropdown-Element an die Selenium-Select-Klasse
        select.selectByVisibleText(countryName); // Wählt das Land anhand des sichtbaren Textes aus
    }

    public void clickTermsButton() { // Methode: bestätigt die Terms of Service Checkbox
        wait.until(ExpectedConditions.elementToBeClickable(By.id("termsofservice"))).click(); // Wartet, bis die Checkbox klickbar ist, und klickt sie erst dann an
    }

    public void clickCheckoutButton() { // Methode: klickt auf den Checkout-Button
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click(); // Wartet, bis der Checkout-Button klickbar ist, und klickt ihn erst dann an
    }

    public boolean isProductInCart(String productName) { // Methode: prüft, ob ein bestimmtes Produkt im Warenkorb vorhanden ist

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-name"))); // Wartet, bis mindestens ein Produktname im Warenkorb sichtbar ist

        List<WebElement> products = driver.findElements(By.className("product-name")); // Holt alle sichtbaren Produktnamen aus dem Warenkorb

        for (WebElement product : products) { // Geht jeden gefundenen Produktnamen einzeln durch

            if (product.getText().contains(productName)) { // Prüft, ob der Produkttext den gesuchten Produktnamen enthält
                return true; // Gibt true zurück, wenn das Produkt gefunden wurde
            }
        }

        return false; // Gibt false zurück, wenn kein passendes Produkt gefunden wurde
    }


    public int getQuantityForProduct(String productName) { // Methode: liest die Menge eines bestimmten Produkts im Warenkorb aus

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item-row"))); // Wartet, bis mindestens eine Warenkorb-Zeile sichtbar ist

        List<WebElement> rows = driver.findElements(By.cssSelector(".cart-item-row")); // Holt alle Produktzeilen aus dem Warenkorb

        for (WebElement row : rows) { // Geht jede Warenkorb-Zeile einzeln durch

            String name = row.findElement(By.cssSelector("td.product a")).getText(); // Liest den Produktnamen aus der aktuellen Zeile aus

            if (name.equals(productName)) { // Prüft, ob der Produktname genau dem gesuchten Produkt entspricht

                WebElement quantityInput = row.findElement(By.cssSelector("input.qty-input")); // Holt das Mengenfeld des passenden Produkts

                return Integer.parseInt(quantityInput.getAttribute("value")); // Liest den Mengenwert aus und wandelt ihn in eine Zahl um
            }
        }

        throw new RuntimeException("Produkt nicht im Warenkorb gefunden: " + productName); // Wirft einen Fehler, wenn das Produkt nicht gefunden wurde
    }

    public void clearCart() { // Methode: leert den kompletten Warenkorb, falls Produkte vorhanden sind

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ico-cart"))).click(); // Wartet, bis der Warenkorb-Link klickbar ist, und öffnet den Warenkorb

        List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart")); // Holt alle Remove-Checkboxen im Warenkorb

        if (!removeCheckboxes.isEmpty()) { // Prüft, ob überhaupt Produkte im Warenkorb vorhanden sind

            for (WebElement checkbox : removeCheckboxes) { // Geht jede Remove-Checkbox einzeln durch
                wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click(); // Wartet, bis die jeweilige Checkbox klickbar ist, und markiert sie
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.name("updatecart"))).click(); // Wartet, bis der Update-Cart-Button klickbar ist, und entfernt die markierten Produkte

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cart-item-row"))); // Wartet, bis keine Produktzeilen im Warenkorb mehr sichtbar sind
        }
    }

    public void goToHomePage() { // Methode: öffnet die Startseite des Demo Web Shops
        driver.get("https://demowebshop.tricentis.com/"); // Öffnet die Startseite direkt über die URL

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("small-searchterms"))); // Wartet, bis das Suchfeld sichtbar ist, damit klar ist, dass die Startseite geladen wurde
    }

}