package base; // Diese Datei gehört zur Klasse base. Das ist unser Fundament / unsere Zentrale für andere Klassem, für die Nutzung von Funktionen

import org.junit.jupiter.api.BeforeEach; // Importiert @BeforeEach, damit Code vor jedem Test automatisch ausgeführt wird
import org.junit.jupiter.api.AfterEach; // Importiert @AfterEach, damit Code nach jedem Test automatisch ausgeführt wird
import org.openqa.selenium.WebDriver; // Importiert das WebDriver Interface zur allgemeinen Browsersteuerung
import org.openqa.selenium.chrome.ChromeDriver; // Importiert ChromeDriver, um den Google Chrome Browser konkret zu starten
import org.openqa.selenium.chrome.ChromeOptions; // Importiert ChromeOptions, um Chrome mit eigenen Einstellungen zu starten


public class BaseTest {

    protected WebDriver driver; // private = nur diese Klasse | protected = diese Klasse + erbende Kinderklassen | public = überall nutzbar

    @BeforeEach // Wird vor jedem Test automatisch ausgeführt und bereitet den Test vor
    public void setUp() { // Setup-Methode: startet vor jedem Test und bereitet Browser + Testumgebung vor

        ChromeOptions options = new ChromeOptions(); // Erstellt ein ChromeOptions Objekt für eigene Browser-Start Einstellungen

        options.addArguments("--incognito"); // Startet Chrome im Inkognito Modus ohne gespeicherte Sitzungsdaten

        options.addArguments("--disable-save-password-bubble"); // Deaktiviert das Passwort-Speichern Popup, damit Tests nicht gestört werden

        options.addArguments("--disable-notifications"); // Deaktiviert Browser Benachrichtigungen, damit keine Popups erscheinen

        driver = new ChromeDriver(options); // Startet den Chrome Browser mit unseren zuvor festgelegten Optionen und speichert ihn im driver

        driver.manage().window().maximize(); // Maximiert das Browserfenster für bessere Sichtbarkeit und stabile Elementpositionen

        driver.get("https://demowebshop.tricentis.com/"); // Öffnet unsere Test-Webseite als Startpunkt für alle Tests
    }


    // @AfterEach // Wird nach jedem Test automatisch ausgeführt und räumt die Testumgebung auf
    public void cleanUp() { // Aufräum-Methode nach jedem Test, z. B. Browser sauber schließen und Testumgebung zurücksetzen

        driver.quit();
    }
}