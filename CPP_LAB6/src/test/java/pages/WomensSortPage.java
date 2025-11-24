package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomensSortPage {

    private final WebDriver driver;


    private final String url = "https://loving-hermann-e2094b.netlify.app/womens";


    private final By sortDropdownLocator = By.id("country1");


    private final By priceElementsLocator = By.cssSelector(".simpleCart_shelfItem .item_price");

    public WomensSortPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement getSortDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdownLocator));
    }

    // (2 = High-Low, 3 = Low-High)
    public void selectOptionByValue(String value) {
        Select select = new Select(getSortDropdown());
        select.selectByValue(value);
    }

    public List<Double> getPrices() {
        List<WebElement> elements = driver.findElements(priceElementsLocator);
        List<Double> prices = new ArrayList<>();

        for (WebElement el : elements) {
            String text = el.getText().trim();
            // убираем валюту и прочие символы
            text = text.replaceAll("[^0-9.,]", "");
            if (text.isEmpty()) continue;
            text = text.replace(",", ".");
            try {
                prices.add(Double.parseDouble(text));
            } catch (NumberFormatException ignored) {
            }
        }
        return prices;
    }

    public boolean isAscending(List<Double> prices) {
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        return sorted.equals(prices);
    }

    public boolean isDescending(List<Double> prices) {
        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());
        return sorted.equals(prices);
    }
}
