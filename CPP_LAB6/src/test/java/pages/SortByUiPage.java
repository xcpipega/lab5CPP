package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SortByUiPage {

    private final WebDriver driver;


    private final String mens2Url = "https://loving-hermann-e2094b.netlify.app/mens2";


    private final By sortByContainer = By.cssSelector(".sorting");
    private final By sortBySelect = By.id("country1");


    private final By filterByPriceText = By.xpath("//*[contains(text(),'Filter by Price')]");

    public SortByUiPage(WebDriver driver) {
        this.driver = driver;
    }


    public void openMens2() {
        driver.get(mens2Url);
        driver.manage().window().maximize();
    }


    public String getSortByText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement container = wait.until(
                ExpectedConditions.visibilityOfElementLocated(sortByContainer)
        );
        return container.getText().trim();
    }


    public void hoverSortByArea() {
        WebElement container = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(sortByContainer));

        Actions actions = new Actions(driver);
        actions.moveToElement(container).perform();
    }


    public void openSortByMenu() {
        WebElement select = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sortBySelect));
        select.click();
    }


    public boolean isFilterByPriceVisible() {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(filterByPriceText));
            return el.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Проверка наличия лишнего символа возле Sort By
    public boolean textContainsExtraSymbol(String symbol) {
        return getSortByText().contains(symbol);
    }
}
