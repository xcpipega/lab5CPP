package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.QuickViewPage;

public class QuickViewSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final QuickViewPage page = new QuickViewPage(driver);
    private final Actions actions = new Actions(driver);

    private String firstUrl;
    private String secondUrl;

    @Given("utilizatorul este pe pagina womens pentru Quick View")
    public void user_is_on_womens_page_for_quick_view() {
        page.open();
    }

    @When("utilizatorul deschide Quick View pentru primul produs")
    public void user_opens_quick_view_first_product() {
        firstUrl = page.openQuickViewAndReturnUrl(0);
    }

    @When("utilizatorul deschide Quick View pentru al doilea produs")
    public void user_opens_quick_view_second_product() {
        secondUrl = page.openQuickViewAndReturnUrl(1);
    }

    @Then("URL-urile paginilor de produs sunt diferite")
    public void product_urls_are_different() {
        assert !firstUrl.equals(secondUrl) : "Quick View trimite pe aceeași pagină pentru ambele produse!";
    }
}
