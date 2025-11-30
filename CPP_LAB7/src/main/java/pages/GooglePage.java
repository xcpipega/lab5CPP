package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage {

    private WebDriver driver;

    private By searchBox = By.name("q");
    private By calculatorContainer = By.cssSelector("div.tyYmIf");
    private By searchResults = By.cssSelector("div#search a h3");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openGoogle() {
        driver.get("https://www.google.com/");
    }

    public void search(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));

        box.clear();
        box.sendKeys(text);
        box.sendKeys(Keys.ENTER);
    }

    public boolean calculatorVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            wait.until(ExpectedConditions.visibilityOfElementLocated(calculatorContainer));
            return driver.findElement(calculatorContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCalculatorResult() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement calc = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(calculatorContainer)
            );
            return calc.getText();
        } catch (Exception e) {
            return "NOT FOUND";
        }
    }

    public boolean converterVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));

            List<WebElement> results = driver.findElements(searchResults);

            return results.stream()
                    .limit(3)
                    .anyMatch(e -> e.getText().toLowerCase().contains("convert"));

        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstConverterResult() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> results = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResults)
            );

            return results.stream()
                    .map(WebElement::getText)
                    .filter(t -> t.toLowerCase().contains("convert"))
                    .findFirst()
                    .orElse("NOT FOUND");

        } catch (Exception e) {
            return "NOT FOUND";
        }
    }

    public boolean resultsExist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Ждём, пока появится хотя бы один заголовок результата
            WebElement firstResult = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div#search h3")
                    )
            );

            System.out.println("Primul rezultat găsit: " + firstResult.getText());
            return firstResult.isDisplayed();

        } catch (Exception e) {
            System.out.println("NU am găsit niciun rezultat. Titlu pagină: " + driver.getTitle());
            return false;
        }
    }

}
