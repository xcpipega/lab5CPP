package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPricePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String womensUrl = "https://loving-hermann-e2094b.netlify.app/womens";

    // цена первого товара на странице
    private final By firstProductPrice = By.cssSelector(".item_price");

    // кнопка Add to cart для первого товара
    private final By firstAddToCartButton =
            By.xpath("(//input[@type='submit' and @value='Add to cart'])[1]");

    // кнопка открытия мини-корзины (иконка тележки)
    private final By cartButton = By.cssSelector("button.w3view-cart");

    // блок Subtotal в мини-корзине
    private final By cartSubtotal = By.cssSelector(".minicart-subtotal");

    public CartPricePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public void openWomens() {
        driver.get(womensUrl);
        driver.manage().window().maximize();
    }

    // считываем цену первого товара на странице
    public double getFirstProductPrice() {
        WebElement priceEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstProductPrice)
        );
        String text = priceEl.getText().trim(); // "$130.99"
        return parsePrice(text);
    }

    // жмём Add to cart для первого товара
    public void addFirstProductToCart() {
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstAddToCartButton)
        );
        addBtn.click();
    }

    // открываем мини-корзину
    public void openCart() {
        WebElement cartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cartButton)
        );
        cartBtn.click();
    }

    // считываем Subtotal из корзины
    public double getCartSubtotal() {
        WebElement subtotalEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartSubtotal)
        );
        String text = subtotalEl.getText(); // "Subtotal: $320.99 USD"
        // вытащим из строки первую сумму вида $XXX.XX
        String numberPart = text.replaceAll(".*\\$(\\d+\\.\\d+).*", "$1");
        return Double.parseDouble(numberPart);
    }

    private double parsePrice(String text) {
        // "$130.99" -> 130.99
        String number = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(number);
    }
}
