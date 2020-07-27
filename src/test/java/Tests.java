import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Tests {
    private String browser;

    @Before
    public void setUp() {
        browser = System.getProperty("browser");
    }


    @Test
    public void Default() {
        WebDriver wd = WebDriverFactory.create(browser);
        if (wd != null) {
            wd.quit();
        }
    }

    @Test
    public void ChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver wd = WebDriverFactory.create(browser, options);
        if (wd != null) {
            wd.quit();
        }
    }

    @Test
    public void FirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        WebDriver wd = WebDriverFactory.create(browser, options);
        if (wd != null) {
            wd.quit();
        }
    }
}
