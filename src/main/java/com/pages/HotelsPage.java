package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HotelsPage extends BasePage {

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(id = "CheckInDate")
    private WebElement checkInDate;

    @FindBy(id = "CheckOutDate")
    private WebElement checkOutDate;

    @FindBy(xpath = "//td[@data-handler = 'selectDay']/a")
    private List<WebElement> selectDay;

    HotelsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchForHotels(String location, String travellers) {
        new Actions(driver).sendKeys(localityTextBox, location).perform();
        waitFor(2000);
        waitForElement(By.xpath(AUTO_COMPLETE_XPATH)).click();
        checkInDate.click();
        waitFor(2000);
        selectDay.get(1).click();
        checkOutDate.click();
        waitFor(2000);
        selectDay.get(2).click();
        new Select(travellerSelection).selectByVisibleText(travellers);
        searchButton.click();
    }
}
