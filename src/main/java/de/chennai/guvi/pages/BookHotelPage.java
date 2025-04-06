// BookHotelPage.java
package de.chennai.guvi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.utils.LoggerWrapper;

public class BookHotelPage extends BasePage {

    private final LoggerWrapper logger = new LoggerWrapper();

    public BookHotelPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By billingAddress = By.id("address");
    private By creditCardNo = By.id("cc_num");
    private By creditCardType = By.id("cc_type");
    private By expiryMonth = By.id("cc_exp_month");
    private By expiryYear = By.id("cc_exp_year");
    private By cvvNumber = By.id("cc_cvv");
    private By bookNowButton = By.id("book_now");
    private By orderId = By.id("order_no");

    public void enterFirstName(String fname) {
        doSendKeys(firstName, fname);
    }

    public void enterLastName(String lname) {
        doSendKeys(lastName, lname);
    }

    public void enterBillingAddress(String address) {
        doSendKeys(billingAddress, address);
    }

    public void enterCreditCardNo(String ccno) {
        doSendKeys(creditCardNo, ccno);
    }

    public void selectCreditCardType(String type) {
        selectByVisibleText(creditCardType, type);
    }

    public void selectExpiryMonth(String month) {
        selectByVisibleText(expiryMonth, month);
    }

    public void selectExpiryYear(String year) {
        selectByVisibleText(expiryYear, year);
    }

    public void enterCVVNumber(String cvv) {
        doSendKeys(cvvNumber, cvv);
    }

    public void clickBookNow() {
        doClick(bookNowButton);
    }

    public boolean isOrderIdGenerated() {
        try {
        	  waitForElementVisible(orderId);
            return getElement(orderId).isDisplayed();
        } catch (Exception e) {
            logger.error("Order ID not displayed after booking", e);
            return false;
        }
    }
}
