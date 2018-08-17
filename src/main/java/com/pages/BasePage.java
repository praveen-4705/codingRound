package com.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
