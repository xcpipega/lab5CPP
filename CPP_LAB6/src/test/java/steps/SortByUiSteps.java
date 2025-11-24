package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SortByUiPage;

public class SortByUiSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final SortByUiPage page = new SortByUiPage(driver);

    private String textBeforeHover;

    @Given("utilizatorul este pe pagina mens2")
    public void user_is_on_mens2_page() {
        page.openMens2();
    }

    @When("utilizatorul se uita la zona Sort By")
    public void user_looks_at_sort_by_area() {
        textBeforeHover = page.getSortByText();
    }

    @Then("nu exista simboluri suplimentare langa Sort By")
    public void no_extra_symbols_near_sort_by() {
        Assert.assertFalse(
                "A fost găsit simbolul '<' în zona Sort By!",
                page.textContainsExtraSymbol("<")
        );
    }

    @When("utilizatorul muta cursorul peste zona Sort By")
    public void user_hovers_sort_by() {
        textBeforeHover = page.getSortByText();
        page.hoverSortByArea();
    }

    @Then("nu apare niciun highlight neobisnuit in zona Sort By")
    public void no_weird_highlight_on_hover() {
        String afterHover = page.getSortByText();
        Assert.assertEquals(
                "Textul din zona Sort By s-a schimbat dupa hover!",
                textBeforeHover,
                afterHover
        );
    }

    @When("utilizatorul deschide meniul Sort By pentru verificarea UI")
    public void user_opens_sort_by_menu() {
        page.openSortByMenu();
    }

    @Then("componenta Filter by Price este vizibila")
    public void filter_by_price_is_visible() {
        Assert.assertTrue(
                "Componenta 'Filter by Price' nu este vizibila!",
                page.isFilterByPriceVisible()
        );
    }

    @When("utilizatorul reincarca pagina mens2")
    public void user_reloads_mens2_page() {
        page.openMens2();
    }

    @Then("simbolul nedorit nu mai este afisat langa Sort By")
    public void unwanted_symbol_not_displayed_after_reload() {
        Assert.assertFalse(
                "Dupa reload, simbolul '<' inca este afisat langa Sort By!",
                page.textContainsExtraSymbol("<")
        );
    }
}
