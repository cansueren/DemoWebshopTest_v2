package pages;                                              // Diese Datei gehört zum Package pages für gemeinsame Page-Logik

import org.openqa.selenium.By;                              // Importiert Locator-Werkzeuge, um Elemente auf der Webseite zu finden
import org.openqa.selenium.WebDriver;                       // Importiert den Browser-Treiber, damit alle Page-Klassen damit arbeiten können
import org.openqa.selenium.WebElement;                      // Ermöglicht das Zwischenspeichern gefundener HTML-Elemente
import org.openqa.selenium.support.ui.ExpectedConditions;   // Importiert fertige Wait-Bedingungen wie sichtbar oder klickbar
import org.openqa.selenium.support.ui.Select;               // Importiert Selenium Select für Dropdowns
import org.openqa.selenium.support.ui.WebDriverWait;        // Importiert WebDriverWait für explizite Wartezeiten

import java.time.Duration;                                  // Importiert Duration, um die maximale Wartezeit festzulegen

public class BasePage {                                     // Basisklasse für alle Page Objects mit gemeinsamen Selenium-Hilfsmethoden

    protected WebDriver driver;                             // Speichert den Browser für alle Page-Klassen, die von BasePage erben
    protected WebDriverWait wait;                           // Speichert den zentralen expliziten Wait für alle Page-Klassen


    public BasePage(WebDriver driver) { // Konstruktor erhält den geöffneten Browser aus dem Test
        this.driver = driver; // Übergibt den Browser an die Klassenvariable
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Erstellt einen zentralen Wait mit maximal 10 Sekunden Wartezeit
    }

    protected void click(By locator) { // Klickt auf ein Element, sobald es klickbar ist
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); // Wartet auf Klickbarkeit und führt danach den Klick aus
    }

    protected void type(By locator, String text) { // Schreibt Text in ein sichtbares Eingabefeld
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text); // Wartet auf Sichtbarkeit und schreibt danach den Text hinein
    }

    protected String getText(By locator) { // Liest den sichtbaren Text eines Elements aus
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText(); // Wartet auf Sichtbarkeit und gibt den Text zurück
    }

    protected boolean isVisible(By locator) { // Prüft, ob ein Element sichtbar ist
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed(); // Wartet auf Sichtbarkeit und gibt true zurück, wenn es angezeigt wird
    }

    protected void selectByVisibleText(By locator, String visibleText) { // Wählt eine Dropdown-Option anhand des sichtbaren Textes aus
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wartet, bis das Dropdown sichtbar ist
        Select select = new Select(dropdown); // Übergibt das Dropdown an die Selenium-Select-Klasse
        select.selectByVisibleText(visibleText); // Wählt die Option anhand des sichtbaren Textes aus
    }
}