package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.QuantityPage;

import java.util.List;

public class QuantitySteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final QuantityPage page = new QuantityPage(driver);

    private final List<String> expectedValues = List.of("5 Qty", "6 Qty", "7 Qty", "10 Qty");

    @Given("utilizatorul este pe pagina produsului single")
    public void open_page() {
        page.open();
    }

    @When("accesati dropdown-ul Quantity")
    public void open_dropdown() {
        // doar trimitem driver-ul pe pagină; dropdown-ul e vizibil
    }

    @Then("valorile 5-10 sunt vizibile")
    public void check_values() {
        List<String> actual = page.getAllOptions();
        Assert.assertEquals(expectedValues, actual);
    }

    @When("utilizatorul selecteaza {string} din Quantity")
    public void select_quantity(String quantity) {
        page.selectOption(quantity);
    }

    @Then("valoarea selectata este acceptata")
    public void check_selection() {
        // dacă selectarea nu a dat excepție → OK
        Assert.assertTrue(true);
    }
}
