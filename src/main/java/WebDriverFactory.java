import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static BrowserType stringToBrowserType(String name) {
        String lowWebDriverName = name.replaceAll("\'","").toLowerCase();
        BrowserType webDriver;
        try {
            webDriver = BrowserType.valueOf(lowWebDriverName);
        } catch ( IllegalArgumentException e ) {
            throw new IllegalArgumentException("Wrong type:" + name);
        }
        return webDriver;
    }

    public static WebDriver defaultChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        logger.info("Returning default Chrome");
        return driver;
    }

    public static WebDriver defaultFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        logger.info("Returning default Firefox");
        return driver;
    }

    public static WebDriver chromeWithOptions(ChromeOptions options) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(options);
        logger.info("Returning Chrome with options");
        return driver;
    }

    public static WebDriver firefoxWithOptions(FirefoxOptions options) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver = new FirefoxDriver();
        logger.info("Returning Firefox with options");
        return driver;
    }

    public static WebDriver create(String webDriverName) {
        BrowserType webDriver = stringToBrowserType(webDriverName);
        switch (webDriver) {
            case chrome:
                WebDriver cdriver = defaultChrome();
                return cdriver;
            case firefox:
                WebDriver fdriver = defaultFirefox();
                return fdriver;
            default:
                throw new IllegalArgumentException("Wrong type:" + webDriverName);
        }
    }

    public static WebDriver create(String webDriverName, ChromeOptions options) {
        BrowserType webDriver = stringToBrowserType(webDriverName);
        switch (webDriver) {
            case chrome:
                WebDriver cdriver = chromeWithOptions(options);
                return cdriver;
            case firefox:
                logger.info("Incompatible parameters: Firefox + ChromeOptions");
                WebDriver fdriver = defaultFirefox();
                return fdriver;
            default:
                throw new IllegalArgumentException("Wrong type:" + webDriverName);
        }
    }

    public static WebDriver create(String webDriverName, FirefoxOptions options) {
        BrowserType webDriver = stringToBrowserType(webDriverName);
        switch (webDriver) {
            case chrome:
                logger.info("Incompatible parameters: Chrome + FirefoxOptions");
                WebDriver cdriver = defaultChrome();
                return cdriver;
            case firefox:
                WebDriver fdriver = firefoxWithOptions(options);
                return fdriver;
            default:
                throw new IllegalArgumentException("Wrong type:" + webDriverName);
        }
    }
}