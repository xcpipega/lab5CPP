package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuickViewPage {

    private WebDriver driver;

    // Lista produselor
    private By productContainers = By.cssSelector(".men-thumb-item");

    // Butonul Quick View apare doar după hover
    private By quickViewButton = By.cssSelector(".inner-men-cart-pro .link-product-add-cart");

    public QuickViewPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get("https://loving-hermann-e2094b.netlify.app/womens");
        driver.manage().window().maximize();
    }
    /**
     * Face hover pe produsul indexat și apasă Quick View
     * Returnează URL-ul paginii produsului după accesare.
     */
    public String openQuickViewAndReturnUrl(int index) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Actions actions = new Actions(driver);

        // Așteaptă să apară lista produselor
        List<WebElement> products = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(productContainers));

        WebElement product = products.get(index);

        // Hover pe containerul produsului
        actions.moveToElement(product).perform();

        // Acum butonul Quick View devine vizibil
        WebElement quickView = wait.until(
                ExpectedConditions.visibilityOf(
                        product.findElement(quickViewButton)
                )
        );

        // Click pe Quick View
        quickView.click();

        // așteaptă puțin să se încarce
        wait.until(ExpectedConditions.urlContains("/single"));

        return driver.getCurrentUrl();
    }
}
