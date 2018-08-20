package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(linkText = "Your trips")
    private WebElement yourTrips;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInPage navigateToSignInPage() {
        yourTrips.click();
        return new SignInPage(driver);
    }

    public HotelsPage navigateToHotelsPage() {
        hotelLink.click();
        return new HotelsPage(driver);
    }
}
