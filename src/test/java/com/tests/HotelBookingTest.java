package com.tests;

import com.pages.HomePage;
import com.pages.HotelsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseTest {

    @Test
    public void shouldBeAbleToSearchForHotels() {
        HomePage homePage = new HomePage(getDriver());
        HotelsPage hotelsPage = homePage.navigateToHotelsPage();
        hotelsPage.searchForHotels("Indiranagar", "1 room, 2 adults");
        Assert.assertTrue(hotelsPage.isElementPresent(By.id("modifySearchLink")));
    }
}
