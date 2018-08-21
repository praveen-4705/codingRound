package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(linkText = "Your trips")
    private WebElement yourTrips;

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "OneWay")
    private WebElement oneWay;

    @FindBy(id = "FromTag")
    private WebElement fromStation;

    @FindBy(id = "ToTag")
    private WebElement toStation;

    @FindBy(id = "DepartDate")
    private WebElement departDate;

    @FindBy(xpath = "//td[@data-handler = 'selectDay']/a")
    private List<WebElement> selectDay;

    @FindBy(id = "SearchBtn")
    private WebElement btnSearch;

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

    public void searchForFlights(String fromLocation, String toLocation) {
        fromStation.clear();
        fromStation.sendKeys(fromLocation);
        waitForElement(By.xpath(AUTO_COMPLETE_XPATH)).click();

        toStation.clear();
        toStation.sendKeys(toLocation);
        waitForElement(By.xpath(AUTO_COMPLETE_XPATH)).click();

        departDate.click();
        waitFor(2000);
        selectDay.get(2).click();

        btnSearch.click();
        waitFor(5000);
    }
}
