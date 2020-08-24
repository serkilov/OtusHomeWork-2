import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Tests {
    private String browser;
    private static Logger logger = LogManager.getLogger(Tests.class);

    @Before
    public void setUp() {
        browser = System.getProperty("browser");
    }


    @Test
    public void Default() {
        logger.info("Running Default browser test");
        WebDriver wd = WebDriverFactory.create(browser);
        if (wd != null) {
            wd.quit();
        }
    }

    @Test
    public void ChromeOptions() {
        logger.info("Running ChromeOptions test");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver wd = WebDriverFactory.create(browser, options);
        if (wd != null) {
            wd.quit();
        }
    }

    @Test
    public void FirefoxOptions() {
        logger.info("Running FirefoxOptions test");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        WebDriver wd = WebDriverFactory.create(browser, options);
        if (wd != null) {
            wd.quit();
        }
    }
}
