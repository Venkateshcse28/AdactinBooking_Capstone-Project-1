package de.chennai.guvi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.utils.LoggerWrapper;

public class LoginPage extends BasePage {

    private final LoggerWrapper logger = new LoggerWrapper();

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    private By errorMessage = By.xpath("//b[contains(text(),'Invalid Login details or Your Password might have expired.')]");
    private By hotelHeading = By.xpath("//td[contains(text(),'Welcome to Adactin Group of Hotels')]");

    public void enterUsername(String username) {
        try {
            waitForElementToBeClickable(usernameField);
            doClear(usernameField);
            doSendKeys(usernameField, username);
        } catch (Exception e) {
            logger.error("Error entering username", e);
        }
    }

    public void enterPassword(String password) {
        try {
            waitForElementToBeClickable(passwordField);
            doClear(passwordField);
            doSendKeys(passwordField, password);
        } catch (Exception e) {
            logger.error("Error entering password", e);
        }
    }

    public void clickLogin() {
        try {
            waitForElementToBeClickable(loginButton);
            doClick(loginButton);
        } catch (Exception e) {
            logger.error("Error clicking login button", e);
        }
    }

    public boolean isLoginButtonTextValid(String expectedText) {
        try {
            return getElement(loginButton).getAttribute("value").trim().equalsIgnoreCase(expectedText);
        } catch (Exception e) {
            logger.error("Error validating login button text", e);
            return false;
        }
    }

    public boolean isErrorMessageDisplayed(String expectedText) {
        try {
            return getElement(errorMessage).getText().trim().equalsIgnoreCase(expectedText);
        } catch (Exception e) {
            logger.error("Error validating error message", e);
            return false;
        }
    }

    public boolean isHotelHeadingDisplayed() {
        try {
            return getElement(hotelHeading).isDisplayed();
        } catch (Exception e) {
            logger.error("Hotel heading not found", e);
            return false;
        }
    }
}
