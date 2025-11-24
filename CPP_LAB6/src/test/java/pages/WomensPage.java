package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WomensPage {

    private final WebDriver driver;
    private final String baseUrl = "https://loving-hermann-e2094b.netlify.app/womens";

    public WomensPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(baseUrl);
    }

    public WebElement getFilterByPriceSection() {
        return driver.findElement(By.id("slider-range"));
    }

    private List<WebElement> getSliderHandles() {
        return driver.findElements(By.cssSelector("#slider-range .ui-slider-handle"));
    }

    public WebElement getLeftHandle() {
        return getSliderHandles().get(0);
    }

    public WebElement getRightHandle() {
        return getSliderHandles().get(1);
    }

    public String getPriceRangeValue() {
        return driver.findElement(By.id("amount"))
                .getAttribute("value") // ВАЖНО: value, не text
                .trim();
    }
}
