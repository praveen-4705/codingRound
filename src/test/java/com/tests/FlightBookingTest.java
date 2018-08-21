package com.tests;

import com.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BaseTest {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        HomePage homePage = new HomePage(getDriver());
        homePage.searchForFlights("Hyderabad", "Bangalore");
        Assert.assertTrue(homePage.isElementPresent(By.className("searchSummary")), "Unable to search for flights");
    }
}
