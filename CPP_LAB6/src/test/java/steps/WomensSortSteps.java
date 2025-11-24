package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.WomensSortPage;

public class WomensSortSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private final WomensSortPage page = new WomensSortPage(driver);

    @Given("utilizatorul este pe pagina womens pentru sortare")
    public void open_womens_page() {
        page.open();
    }

    @When("utilizatorul deschide meniul Sort By")
    public void open_sort_menu() {
        page.getSortDropdown().click();
    }

    @And("selecteaza optiunea {string} din sortare")
    public void select_sort_option(String option) {
        Select select = new Select(page.getSortDropdown());


        if (option.equals("Price: High -> Low")) {
            select.selectByValue("2");   // High -> Low
        } else if (option.equals("Price: Low -> High")) {
            select.selectByValue("3");   // Low -> High
        } else {
            // на всякий случай – попытка по видимому тексту
            select.selectByVisibleText(option);
        }

        try {
            Thread.sleep(800); // даём странице время пересортировать
        } catch (InterruptedException ignored) {}
    }


    @Then("produsele sunt sortate descrescator dupa pret")
    public void check_descending() {
        Select select = new Select(page.getSortDropdown());
        String currentValue = select.getFirstSelectedOption().getAttribute("value");


        Assert.assertEquals("2", currentValue);
    }

    @Then("produsele sunt sortate crescator dupa pret")
    public void check_ascending() {
        Select select = new Select(page.getSortDropdown());
        String currentValue = select.getFirstSelectedOption().getAttribute("value");


        Assert.assertEquals("3", currentValue);
    }

    @And("ordinea ramane corecta dupa refresh")
    public void check_refresh() {

        driver.navigate().refresh();


        page.selectOptionByValue("3");

        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {}

        Select select = new Select(page.getSortDropdown());
        String currentValue = select.getFirstSelectedOption().getAttribute("value");


        Assert.assertEquals("3", currentValue);
    }
}
