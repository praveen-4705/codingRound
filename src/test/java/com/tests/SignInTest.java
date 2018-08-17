package com.tests;

import com.pages.HomePage;
import com.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        HomePage homePage = new HomePage(getDriver());
        SignInPage signInPage = homePage.navigateToSignInPage();
        signInPage.loginWithoutEnteringAnyDetails();
        Assert.assertTrue(signInPage.getInvalidLoginMessage().contains("There were errors in your submission"));
    }
}
