# Demo Web Shop Test Automation

![Selenium Tests](https://github.com/cansueren/DemoWebshopTest_v2/actions/workflows/selenium-tests.yml/badge.svg)

This project automates key user flows of the Demo Web Shop using Java, Selenium WebDriver, JUnit 5 and Maven.

The test suite covers registration, login, cart validation and checkout, including a full end-to-end purchase flow based on the Page Object Model.

## Application Under Test

[Demo Web Shop](https://demowebshop.tricentis.com/)

## Test Scenarios

The project currently covers the following scenarios:

1. User registration with dynamic test data
2. User authentication with a predefined test account
3. Product search and add-to-cart flow
4. Cart validation including product name and quantity
5. Checkout process until order confirmation
6. Full end-to-end purchase flow

## Tech Stack

- Java
- Selenium WebDriver
- JUnit 5
- Maven
- IntelliJ IDEA
- GitHub Actions

## Project Structure

```text
src/test/java
├── base
│   └── BaseTest.java
├── pages
│   ├── BasePage.java
│   ├── RegistrationPage.java
│   ├── LoginPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
├── tests
│   ├── RegistrationTest.java
│   ├── LoginTest.java
│   ├── CartTest.java
│   ├── CheckoutTest.java
│   └── E2ETest.java
└── utils
    └── RandomDataUtil.java
```

## Architecture

The project follows the Page Object Model.

- `BaseTest` handles browser setup, cleanup and shared test login logic.
- `BasePage` provides reusable Selenium helper methods such as `click`, `type`, `getText`, `isVisible` and dropdown selection.
- Page classes contain page-specific actions and validations.
- Test classes describe the test flow and assertions.
- Utility classes provide reusable test data helpers.

## Key Features

- Clean Page Object Model structure
- Centralized WebDriver setup in `BaseTest`
- Shared Selenium actions through `BasePage`
- Explicit waits for stable UI interaction
- Dynamic email generation for registration tests
- Assertions for registration, login, cart and checkout validation
- Console summaries for important test data and order numbers
- GitHub Actions CI pipeline for automated test execution

## Running Tests Locally

Run all tests with Maven:

```bash
mvn test
```

Alternatively, tests can be executed directly from IntelliJ IDEA using the Maven tool window:

```text
Maven → Lifecycle → test
```

## Continuous Integration

This project uses GitHub Actions to run the Selenium test suite automatically on every push and pull request.

The CI workflow includes:

- Java 17 setup
- Maven dependency caching
- Headless Chrome execution
- Selenium/JUnit test execution via Maven

Workflow file:

```text
.github/workflows/selenium-tests.yml
```

## Notes

This project was created as a learning and practice project with a focus on:

- test automation
- maintainable test architecture
- Page Object Model design
- clean code structure
- CI integration with GitHub Actions
