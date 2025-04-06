package de.chennai.guvi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.utils.LoggerWrapper;

public class SearchHotelPage extends BasePage {

    private final LoggerWrapper logger = new LoggerWrapper();

    public SearchHotelPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By location = By.id("location");
    private By hotels = By.id("hotels");
    private By roomType = By.id("room_type");
    private By numberOfRooms = By.id("room_nos");
    private By checkInDate = By.id("datepick_in");
    private By checkOutDate = By.id("datepick_out");
    private By adultsPerRoom = By.id("adult_room");
    private By childrenPerRoom = By.id("child_room");
    private By searchButton = By.id("Submit");
    private By resetButton = By.id("Reset");
    private By selectHotelHeading = By.xpath("//td[contains(text(),'Select Hotel')]");

    public void selectLocation(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(location, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectLocation", e);
        }
    }

    public void selectHotel(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(hotels, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectHotel", e);
        }
    }

    public void selectRoomType(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(roomType, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectRoomType", e);
        }
    }

    public void selectNumberOfRooms(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(numberOfRooms, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectNumberOfRooms", e);
        }
    }

    public void enterCheckInDate(String date) {
        try {
            Thread.sleep(1000);
            doClear(checkInDate);
            doSendKeys(checkInDate, date);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in enterCheckInDate", e);
        }
    }

    public void enterCheckOutDate(String date) {
        try {
            Thread.sleep(1000);
            doClear(checkOutDate);
            doSendKeys(checkOutDate, date);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in enterCheckOutDate", e);
        }
    }

    public void selectAdultsPerRoom(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(adultsPerRoom, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectAdultsPerRoom", e);
        }
    }

    public void selectChildrenPerRoom(String value) {
        try {
            Thread.sleep(1000);
            selectByVisibleText(childrenPerRoom, value);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in selectChildrenPerRoom", e);
        }
    }

    public void clickSearch() {
        try {
            Thread.sleep(1000);
            doClick(searchButton);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in clickSearch", e);
        }
    }

    public void clickReset() {
        try {
            Thread.sleep(1000);
            doClick(resetButton);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted in clickReset", e);
        }
    }

    public boolean isSelectHotelHeadingDisplayed() {
        try {
            return getElement(selectHotelHeading).isDisplayed();
        } catch (Exception e) {
            logger.error("Select Hotel heading not found", e);
            return false;
        }
    }
}