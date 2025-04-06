package de.chennai.guvi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.utilities.JavaScriptExecutorUtil;
import de.chennai.guvi.utils.LoggerWrapper;

public class BookingConfirmationPage extends BasePage {

    private final LoggerWrapper logger = new LoggerWrapper();

    public BookingConfirmationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By orderIdField = By.id("order_no");
    private By logoutButton = By.id("logout");

    public String getOrderID() {
        try {
            waitForElementVisible(orderIdField);
            JavaScriptExecutorUtil.scrollIntoView(getElement(orderIdField), driver);
            JavaScriptExecutorUtil.elementHighlightGreen(getElement(orderIdField), driver);
            Thread.sleep(1500);
            return getElement(orderIdField).getAttribute("value");
        } catch (Exception e) {
            logger.error("Unable to fetch Order ID", e);
            return null;
        }
    }

    public void clickLogout() {
        try {
            waitForElementToBeClickable(logoutButton);
            Thread.sleep(1500);
            JavaScriptExecutorUtil.elementHighlightGreen(getElement(logoutButton), driver);
            Thread.sleep(1000);
            doClick(logoutButton);
            Thread.sleep(1500);
        } catch (Exception e) {
            logger.error("Error clicking logout button", e);
        }
    }
}
