package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Reducem șansa de CAPTCHA / detectare automatizare
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("start-maximized");

        options.addArguments(
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120 Safari/537.36"
        );

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                final byte[] screenshot =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-screenshot");
            } catch (Exception ignored) {}
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
