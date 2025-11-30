package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage {

    private WebDriver driver;

    private By searchBox = By.name("q");
    private By resultsHeaders = By.xpath("//div[@id='search']//h3");

    // "Did you mean" poate fi în EN / RO / RU
    private By didYouMean = By.xpath(
            "//*[contains(text(),'Did you mean')" +
                    " or contains(text(),'Ai vrut să spui')" +
                    " or contains(text(),'Показаны результаты по запросу')" +
                    " or contains(text(),'Искать вместо этого')" +
                    " or contains(text(),'Вы имели в виду')]"
    );

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    // 1) Deschidere google.co.in
    public void openGoogleIndia() {
        driver.get("https://www.google.co.in");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isHomePage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            return driver.getTitle().toLowerCase().contains("google");
        } catch (Exception e) {
            return false;
        }
    }

    // Căutare generică
    public void search(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));

        box.clear();
        box.sendKeys(text);
        box.sendKeys(Keys.ENTER);
    }

    // 2) Număr rezultate pe prima pagină
    public int getResultsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Ждём, пока заголовок страницы будет содержать текст поиска
        wait.until(ExpectedConditions.titleContains("selenium testing"));

        // 2. После этого просто собираем все <h3> внутри div#search
        List<WebElement> headers = driver.findElements(
                By.xpath("//div[@id='search']//h3")
        );

        System.out.println("Număr rezultate găsite pe prima pagină: " + headers.size());
        return headers.size();
    }

    // 3) Submit cu câmp gol
    public void submitEmptySearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.clear();
        box.sendKeys(Keys.ENTER);
    }

    // 4) "Did you mean"
    public boolean didYouMeanVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement suggestion = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(didYouMean)
            );
            System.out.println("Sugestie găsită: " + suggestion.getText());
            return suggestion.isDisplayed();
        } catch (Exception e) {
            System.out.println("\"Did you mean\" NU a fost găsit.");
            return false;
        }
    }
}
