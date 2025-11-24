package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class QuantityPage {

    private final WebDriver driver;
    private final String url = "https://loving-hermann-e2094b.netlify.app/single";

    private final By quantityDropdown = By.id("country1");

    public QuantityPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public List<String> getAllOptions() {
        Select select = new Select(driver.findElement(quantityDropdown));
        List<WebElement> options = select.getOptions();

        List<String> result = new ArrayList<>();
        for (WebElement op : options) {
            result.add(op.getText().trim());
        }
        return result;
    }

    public void selectOption(String value) {
        Select select = new Select(driver.findElement(quantityDropdown));
        select.selectByVisibleText(value);
    }
}
