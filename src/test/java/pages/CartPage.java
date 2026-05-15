package pages;                                                  // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By;                                  // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver;                           // Importiert den Browser-Treiber, damit der Konstruktor den driver entgegennehmen kann
import org.openqa.selenium.WebElement;                          // Ermöglicht das Arbeiten mit gefundenen HTML-Elementen
import org.openqa.selenium.support.ui.ExpectedConditions;       // Stellt spezielle Wait-Bedingungen bereit, z. B. für Erfolgsmeldungen oder Listen

import java.util.List;                                          // Ermöglicht das Arbeiten mit Listen von WebElementen

public class CartPage extends BasePage {                        // CartPage erbt gemeinsame Selenium-Hilfsmethoden aus BasePage

    public CartPage(WebDriver driver) {                         // Konstruktor erhält den geöffneten Browser beim Erstellen der CartPage
        super(driver);                                          // Übergibt den driver an die BasePage, damit dort driver und wait initialisiert werden
    }

    public void enterSearchText(String productName) { // Methode: trägt den Suchbegriff in das Suchfeld ein
        type(By.id("small-searchterms"), productName); // Nutzt die BasePage-type-Methode und wartet automatisch, bis das Suchfeld sichtbar ist
    }

    public void selectLaptopSuggestion() { // Methode: wählt den Produktvorschlag "14.1-inch Laptop" aus
        click(By.linkText("14.1-inch Laptop")); // Nutzt die BasePage-click-Methode und klickt den Produktlink stabil an
    }

    public void clickAddToCart() { // Methode: fügt das Produkt dem Warenkorb hinzu und schließt die Erfolgsmeldung

        click(By.cssSelector("input[value='Add to cart']")); // Klickt auf den Add-to-cart-Button über die BasePage-click-Methode

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".bar-notification.success")
        )); // Wartet, bis die grüne Erfolgsmeldung sichtbar ist

        click(By.cssSelector("#bar-notification .close")); // Schließt die grüne Erfolgsmeldung, damit sie keine weiteren Klicks blockiert

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("bar-notification")
        )); // Wartet, bis die Erfolgsmeldung nicht mehr sichtbar ist
    }

    public void openShoppingCart() { // Methode: öffnet den Warenkorb über den Warenkorb-Link
        click(By.cssSelector("a.ico-cart")); // Nutzt die BasePage-click-Methode und klickt den Warenkorb-Link stabil an
    }

    public void selectCountry(String countryName) { // Methode: wählt ein Land aus dem Country-Dropdown im Warenkorb aus
        selectByVisibleText(By.id("CountryId"), countryName); // Nutzt die BasePage-Dropdown-Methode und wählt das Land aus
    }

    public void clickTermsButton() { // Methode: bestätigt die Terms of Service Checkbox
        click(By.id("termsofservice")); // Nutzt die BasePage-click-Methode und klickt die Checkbox stabil an
    }

    public void clickCheckoutButton() { // Methode: klickt auf den Checkout-Button
        click(By.id("checkout")); // Nutzt die BasePage-click-Methode und klickt den Checkout-Button stabil an
    }

    public boolean isProductInCart(String productName) { // Methode: prüft, ob ein bestimmtes Produkt im Warenkorb vorhanden ist

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-name"))); // Wartet, bis mindestens ein Produktname im Warenkorb sichtbar ist

        List<WebElement> products = driver.findElements(By.className("product-name")); // Holt alle Produktnamen aus dem Warenkorb

        for (WebElement product : products) { // Geht jeden Produktnamen einzeln durch

            if (product.getText().contains(productName)) { // Prüft, ob der Produkttext den gesuchten Produktnamen enthält
                return true; // Produkt wurde gefunden
            }
        }

        return false; // Produkt wurde nicht gefunden
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

        click(By.cssSelector("a.ico-cart")); // Öffnet den Warenkorb über die BasePage-click-Methode

        List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart")); // Holt alle Remove-Checkboxen im Warenkorb

        if (!removeCheckboxes.isEmpty()) { // Prüft, ob überhaupt Produkte im Warenkorb vorhanden sind

            for (WebElement checkbox : removeCheckboxes) { // Geht jede Remove-Checkbox einzeln durch
                wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click(); // Wartet, bis die Checkbox klickbar ist, und markiert sie
            }

            click(By.name("updatecart")); // Klickt auf Update Cart und entfernt die markierten Produkte

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cart-item-row"))); // Wartet, bis keine Produktzeilen mehr sichtbar sind
        }
    }

    public void goToHomePage() { // Methode: öffnet die Startseite
        driver.get("https://demowebshop.tricentis.com/"); // Navigiert direkt zur Demo Web Shop Startseite
    }
}