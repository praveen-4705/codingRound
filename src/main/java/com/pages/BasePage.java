package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object with locators and methods that are common for all pages of ClearTrip application. */
public class BasePage {

    public static String AUTO_COMPLETE_XPATH = "//ul[@class = 'autoComplete'][contains(@style, 'display: block')]/li/a";

    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /** Waits for the provided {@code durationInMilliSeconds}.*/
    void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /** Waits for the element to be present and visible*/
    WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /** Returns true if the element is present. */
    public boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }
}
