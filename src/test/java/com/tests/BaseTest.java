package com.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("prefs", prefs);
        ChromeDriverManager.getInstance().setup();
        driver.set(new ChromeDriver(chromeOptions));

        driver.get().get("https://www.cleartrip.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        getScreenshot(getDriver(), result.getName());
        getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    /** Gets the screenshot.*/
    private static void getScreenshot(WebDriver driver, String screenshotName) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = "./test-output/" + screenshotName + ".jpeg";
        try {
            FileUtils.copyFile(sourceFile, new File(destinationPath));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
