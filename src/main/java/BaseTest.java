import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--start-maximized");
        ChromeDriverManager.getInstance().setup();
        driver.set(new ChromeDriver(chromeOptions));

        driver.get().get("https://www.cleartrip.com/");
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown(ITestResult result) {
        // Close the driver
        getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
