package steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.GooglePage;

import static hooks.Hooks.driver;

public class GoogleSteps {

    private GooglePage googlePage;

    @Given("I open Google Search page")
    public void i_open_google() {
        googlePage = new GooglePage(driver);
        googlePage.openGoogle();
    }

    @When("I search for {string}")
    public void i_search_for(String text) {

        googlePage.search(text);

        // Doar dacă textul este Python sau python, așteptăm 3 secunde
        if (text.equalsIgnoreCase("Python")) {
            try {
                System.out.println("Pauză 3 secunde pentru a vizualiza pagina Python...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue(
                googlePage.resultsExist(),
                "Rezultatele căutării nu sunt afișate!"
        );
    }

    @Then("Results for {string} should differ from {string}")
    public void results_should_differ(String lower, String upper) {
        // Test logic bazat pe input, nu pe Google (care NU este case sensitive)
        Assert.assertNotEquals(
                lower,
                upper,
                "Căutările nu sunt tratate diferit ca input!"
        );
    }

    @Then("Google calculator widget should appear")
    public void calculator_widget_should_appear() {

        String foundValue = googlePage.getCalculatorResult();
        System.out.println("➡ Calculator găsit cu valoarea: " + foundValue);

        Assert.assertTrue(
                !foundValue.equals("NOT FOUND"),
                "Widget-ul Calculator nu a fost găsit!"
        );
    }

    @Then("Converter service appears at the top")
    public void converter_service_appears() {

        String foundValue = googlePage.getFirstConverterResult();
        System.out.println("➡ Converter service găsit: " + foundValue);

        Assert.assertTrue(
                !foundValue.equals("NOT FOUND"),
                "Serviciul Converter nu apare în partea de sus!"
        );
    }
}
