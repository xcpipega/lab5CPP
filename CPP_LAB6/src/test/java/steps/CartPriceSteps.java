package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CartPricePage;

public class CartPriceSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final CartPricePage page = new CartPricePage(driver);

    private double pagePrice;
    private double cartPrice;

    @Given("utilizatorul este pe pagina womens cu un produs vizibil")
    public void user_is_on_womens_page() {
        page.openWomens();
    }

    @When("utilizatorul noteaza pretul primului produs de pe pagina")
    public void user_notes_first_product_price() {
        pagePrice = page.getFirstProductPrice();
    }

    @When("utilizatorul adauga primul produs in cos")
    public void user_adds_first_product_to_cart() {
        page.addFirstProductToCart();
    }

    @When("utilizatorul acceseaza cosul de cumparaturi")
    public void user_opens_cart() {
        page.openCart();
        cartPrice = page.getCartSubtotal();
    }

    @Then("pretul produsului din cos este egal cu pretul de pe pagina")
    public void cart_price_equals_page_price() {
        Assert.assertEquals(
                "Pretul din cos nu este egal cu pretul de pe pagina!",
                pagePrice,
                cartPrice,
                0.01
        );
    }
}
