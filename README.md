# Testautomatisierung Demo Webshop (Selenium + Java)

Dieses Projekt automatisiert den Kaufprozess auf der Demo-Webshop-Seite unter Verwendung von Java, Selenium WebDriver und JUnit. 

Das Projekt umfasst die Testautomatisierung der Bereiche Registrierung, Authentifizierung, Warenkorb und Checkout als End-to-End-Szenarien unter Anwendung des Page Object Models.



## Projektbeschreibung
Ziel ist die Umsetzung einer strukturierten Testautomatisierung unter Anwendung moderner Best Practices.

Getestete Anwendung:
[Demo Webshop](http://demowebshop.tricentis.com)

---

## Ziel der Aufgabe
Automatisierung folgender End-to-End Test-Szenarien:

1. Registrierung eines neuen Benutzers
2. Authentifizierung (Login)
3. Auswahl eines Produkts und Hinzufügen zum Warenkorb
4. Durchführung des Checkout-Prozesses bis zur Bestellbestätigung

---

## Verwendete Technologien

- Java
- Selenium WebDriver
- JUnit 5
- Maven
- IntelliJ IDEA

---

## Projektstruktur

Das Projekt folgt dem Page Object Model (POM):

- `pages/` → enthält Seitenklassen (z. B. CartPage, LoginPage)
- `tests/` → enthält Testklassen (z. B. E2ETest, CartTest)
- `base/` → Basis-Setup für WebDriver
- `utils/` → wiederverwendbare Helfer (z. B. Waits, Actions, Testdaten)

---

## Features

- Strukturierte Testarchitektur (Page Object Model)
- Wiederverwendbare Utilities (Waits, Actions, Generatoren)
- End-to-End Testabdeckung
- Verwendung von Assertions zur Validierung
- Dynamische Testdaten (z. B. zufällige E-Mail für Registrierung)

---

## Code-Qualität

- Trennung von Testlogik und Seitenlogik
- Reduzierung von Code-Duplikaten durch Utilities
- Lesbare und wartbare Teststruktur

---

## Hinweise

Dieses Projekt wurde im Rahmen einer Lern- bzw. Übungsaufgabe erstellt, mit Fokus auf:
- Testautomatisierung
- saubere Code-Struktur
- Einsatz von Best Practices
