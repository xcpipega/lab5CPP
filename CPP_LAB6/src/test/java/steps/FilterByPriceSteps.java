package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import pages.WomensPage;

import java.time.Duration;

public class FilterByPriceSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final WomensPage womensPage = new WomensPage(driver);
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    private String valueAfterLeftMove;
    private String valueAfterRightMove;
    private String valueAfterFastMoves;

    @Given("utilizatorul este pe pagina womens")
    public void user_is_on_womens_page() {
        womensPage.open();
    }

    @And("componenta {string} este vizibilă")
    public void filter_component_is_visible(String componentName) {
        Assert.assertTrue(
                "Filter by Price nu este vizibil",
                womensPage.getFilterByPriceSection().isDisplayed()
        );
    }

    @When("mut cursorul stang al sliderului spre stanga")
    public void move_left_slider_left() {
        WebElement left = womensPage.getLeftHandle();
        String oldValue = womensPage.getPriceRangeValue();

        Actions actions = new Actions(driver);
        actions.clickAndHold(left)
                .moveByOffset(-40, 0)
                .release()
                .perform();

        wait.until(valueChangedFrom(oldValue));

        valueAfterLeftMove = womensPage.getPriceRangeValue();
        Assert.assertFalse("Valoarea după mișcarea stângă este goală",
                valueAfterLeftMove.isEmpty());
    }

    @And("mut cursorul drept al sliderului spre dreapta")
    public void move_right_slider_right() {
        WebElement right = womensPage.getRightHandle();
        String oldValue = womensPage.getPriceRangeValue();

        Actions actions = new Actions(driver);
        actions.clickAndHold(right)
                .moveByOffset(40, 0)
                .release()
                .perform();

        wait.until(valueChangedFrom(oldValue));

        valueAfterRightMove = womensPage.getPriceRangeValue();
        Assert.assertFalse("Valoarea după mișcarea dreaptă este goală",
                valueAfterRightMove.isEmpty());
    }

    @And("mut de doua ori rapid sliderul")
    public void move_slider_fast_twice() {
        WebElement right = womensPage.getRightHandle();
        String old = womensPage.getPriceRangeValue();

        Actions actions = new Actions(driver);

        actions.clickAndHold(right).moveByOffset(-20, 0).release().perform();
        wait.until(valueChangedFrom(old));
        String first = womensPage.getPriceRangeValue();

        actions.clickAndHold(right).moveByOffset(20, 0).release().perform();
        wait.until(valueChangedFrom(first));
        String second = womensPage.getPriceRangeValue();

        valueAfterFastMoves = second;

        Assert.assertFalse("Valoarea după mișcările rapide este goală",
                valueAfterFastMoves.isEmpty());
    }

    @Then("valoarea intervalului de pret se actualizeaza dupa fiecare miscare")
    public void value_updates_after_each_move() {
        Assert.assertNotNull(valueAfterLeftMove);
        Assert.assertNotNull(valueAfterRightMove);
        Assert.assertNotNull(valueAfterFastMoves);
    }

    private ExpectedCondition<Boolean> valueChangedFrom(String oldValue) {
        return d -> {
            String current = womensPage.getPriceRangeValue();
            return !current.equals(oldValue) && !current.isEmpty();
        };
    }
}
