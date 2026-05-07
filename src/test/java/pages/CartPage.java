package pages; // Diese Datei gehört zum Package pages für Seitenklassen im Page Object Model

import org.openqa.selenium.By; // Importiert Locator-Werkzeuge zum Finden von Elementen auf der Webseite
import org.openqa.selenium.WebDriver; // Importiert den Browser-Treiber für diese Seitenklasse
import org.openqa.selenium.WebElement; // Importiert
import org.openqa.selenium.support.ui.Select; // Importiert Selenium Befehle, für Select zb
import java.util.List; // für List
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class CartPage { // Seitenklasse für Warenkorb- und Produktaktionen

    private WebDriver driver; // Speichert den Browser dieser Seite; private nur innerhalb dieser Klasse nutzbar


    public CartPage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser beim Erstellen der Seite
        this.driver = driver; // Übergibt den erhaltenen Browser an die Klassenvariable
    }

    public void enterSearchText(String productName) { // Methode: trägt den übergebenen Suchbegriff in das Suchfeld ein
        driver.findElement(By.id("small-searchterms")).sendKeys(productName); // Selenium-Befehl: findet das Suchfeld über id und schreibt den Text hinein
    }

    public void selectLaptopSuggestion() {
        // Erstellt einen expliziten Wait, damit Selenium auf das Suchergebnis warten kann
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wartet, bis der Produktlink "14.1-inch Laptop" sichtbar und klickbar ist
        WebElement laptopSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("14.1-inch Laptop"))
        );

        // Klickt auf den Produktlink und öffnet die Produktdetailseite
        laptopSuggestion.click();
    }

    public void clickSearchButton() { // Methode: klickt auf den Search-Button
        driver.findElement(By.cssSelector("input[value='Search']")).click(); // Selenium-Befehl: findet den Button über value und klickt darauf
    }

    public void clickAddToCart() {
        // Erstellt einen expliziten Wait, damit Selenium maximal 10 Sekunden auf bestimmte Bedingungen wartet
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wartet, bis der Add-to-cart-Button sichtbar und klickbar ist
        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[value='Add to cart']")
                )
        );

        // Klickt auf den Add-to-cart-Button und fügt das Produkt dem Warenkorb hinzu
        addToCartButton.click();

        // Wartet, bis die Warenkorb-Anzeige oben auf der Seite auf "(1)" aktualisiert wurde
        // Dadurch wird sichergestellt, dass das Produkt wirklich im Warenkorb gelandet ist
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector("span.cart-qty"),
                        "(1)"
                )
        );
    }

    public void openShoppingCart() {
        // Erstellt einen expliziten Wait, damit Selenium auf den Warenkorb-Link warten kann
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wartet, bis der Warenkorb-Link sichtbar und klickbar ist
        // Der CSS-Selektor ist stabiler als LinkText, weil sich der Text von "Shopping cart(0)" zu "Shopping cart(1)" ändern kann
        WebElement shoppingCartLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a.ico-cart"))
        );

        // Klickt auf den Warenkorb-Link und öffnet die Warenkorb-Seite
        shoppingCartLink.click();
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


    public int getQuantityForProduct(String productName) {

        // Ich hole mir alle Zeilen im Warenkorb
        List<WebElement> rows = driver.findElements(By.cssSelector(".cart-item-row"));

        // Ich gehe jede Zeile durch
        for (WebElement row : rows) {

            // Ich hole mir den Produktnamen aus der aktuellen Zeile
            String name = row.findElement(By.cssSelector("td.product a")).getText();

            // Ich prüfe, ob es mein gesuchtes Produkt ist
            if (name.equals(productName)) {

                // Ich hole mir das Mengenfeld (Input)
                WebElement quantityInput = row.findElement(By.cssSelector("input.qty-input"));

                // Ich lese den Wert und wandle ihn in eine Zahl um
                return Integer.parseInt(quantityInput.getAttribute("value"));
            }
        }

        // Falls das Produkt nicht gefunden wurde, werfe ich einen Fehler
        throw new RuntimeException("Produkt nicht im Warenkorb gefunden");
    }

    // Leert den kompletten Warenkorb
    public void clearCart() {

        // Öffnet den Warenkorb
        driver.findElement(By.linkText("Shopping cart")).click();

        // Holt alle "Remove"-Checkboxen (für jedes Produkt im Warenkorb)
        List<WebElement> removeCheckboxes = driver.findElements(By.name("removefromcart"));

        // Prüft, ob überhaupt Produkte im Warenkorb sind
        if (!removeCheckboxes.isEmpty()) {

            // Markiert alle Produkte zum Entfernen
            for (WebElement checkbox : removeCheckboxes) {
                checkbox.click();
            }

            // Klickt auf "Update Cart", um die Entfernung zu bestätigen
            driver.findElement(By.name("updatecart")).click();
        }
    }

    public void goToHomePage() {
        driver.get("https://demowebshop.tricentis.com/");
    }

}