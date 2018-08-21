package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/* Page object with locators and methods for Sign In page. */
public class SignInPage extends BasePage {

    @FindBy(id = "SignIn")
    private WebElement signInLink;

    @FindBy(id = "signInButton")
    private WebElement btnSignIn;

    @FindBy(id = "errors1")
    private WebElement textError;

    @FindBy(id = "modal_window")
    private WebElement modalFrame;


    SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Login without entering the details. */
    public void loginWithoutEnteringAnyDetails() {
        signInLink.click();
        driver.switchTo().frame(modalFrame);
        btnSignIn.click();
    }

    /** Gets invalid login message. */
    public String getInvalidLoginMessage() {
        return textError.getText().trim();
    }
}
