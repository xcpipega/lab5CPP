package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.GooglePage;

import static hooks.Hooks.driver;

public class GoogleSteps {

    private GooglePage googlePage;
    private String initialUrl;
    private String initialTitle;

    @Given("I open Google India home page")
    public void i_open_google_india_home_page() {
        googlePage = new GooglePage(driver);
        googlePage.openGoogleIndia();

        initialUrl = googlePage.getCurrentUrl();
        initialTitle = googlePage.getTitle();
    }

    @Then("Google India home page is displayed")
    public void google_india_home_page_is_displayed() {
        Assert.assertTrue(
                googlePage.isHomePage(),
                "Pagina Google India nu s-a deschis corect!"
        );
    }

    // Generic search
    @When("I search for {string}")
    public void i_search_for(String text) {
        googlePage.search(text);
    }

    // 2) Numărul de rezultate
    @Then("results are shown on first page")
    public void results_are_shown_on_first_page() {
        int count = googlePage.getResultsCount();
        System.out.println("Număr rezultate pe prima pagină: " + count);

        // Verificăm doar că există cel puțin un rezultat
        Assert.assertTrue(
                count > 0,
                "Nu a fost găsit niciun rezultat pe prima pagină!"
        );
    }


    // 3) Empty search
    @When("I press Search with empty query")
    public void i_press_search_with_empty_query() {
        googlePage.submitEmptySearch();
    }

    @Then("Google home page should remain opened")
    public void google_home_page_should_remain_opened() {
        String currentUrl = googlePage.getCurrentUrl();
        String currentTitle = googlePage.getTitle();

        Assert.assertEquals(
                currentTitle,
                initialTitle,
                "Titlul paginii s-a schimbat după căutare goală!"
        );
        Assert.assertEquals(
                currentUrl,
                initialUrl,
                "URL-ul s-a schimbat după căutare goală!"
        );
    }

    // 4) Irrelevant query
    @When("I search for an irrelevant query")
    public void i_search_for_an_irrelevant_query() {
        // ceva foarte random, ca să fie "irelevant"
        googlePage.search("pthon");
    }

    @Then("\"Did you mean\" suggestion should be displayed")
    public void did_you_mean_suggestion_should_be_displayed() {
        Assert.assertTrue(
                googlePage.didYouMeanVisible(),
                "\"Did you mean\" nu a fost afișat pentru căutarea irelevantă!"
        );
    }
}
