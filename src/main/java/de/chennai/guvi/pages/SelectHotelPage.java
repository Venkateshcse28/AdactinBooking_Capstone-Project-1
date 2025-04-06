package de.chennai.guvi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.utilities.JavaScriptExecutorUtil;
import de.chennai.guvi.utils.LoggerWrapper;

public class SelectHotelPage extends BasePage {

    private final LoggerWrapper logger = new LoggerWrapper();

    public SelectHotelPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By radioSelectHotel = By.id("radiobutton_0");
    private By continueButton = By.id("continue");
    private By bookHotelHeading = By.xpath("//td[contains(text(),'Book A Hotel')]");

    public void selectHotelRadio() {
        try {
            Thread.sleep(1000);
            waitForElementToBeClickable(radioSelectHotel);
            Thread.sleep(1000);
            JavaScriptExecutorUtil.elementHighlightGreen(getElement(radioSelectHotel), driver);
            doClick(radioSelectHotel);
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Error selecting hotel radio button", e);
        }
    }

    public void clickContinue() {
        try {
            Thread.sleep(1000);
            waitForElementToBeClickable(continueButton);
            doClick(continueButton);
        } catch (Exception e) {
            logger.error("Error clicking Continue button", e);
        }
    }

    public boolean isBookHotelHeadingDisplayed() {
        try {
            return getElement(bookHotelHeading).isDisplayed();
        } catch (Exception e) {
            logger.error("Book Hotel heading not found", e);
            return false;
        }
    }
}
