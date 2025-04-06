package de.chennai.guvi.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.ExcelDataProvider;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.pages.LoginPage;
import de.chennai.guvi.pages.SearchHotelPage;
import de.chennai.guvi.report.ITestListenerImpl;
import de.chennai.guvi.utilities.TakeScreenShot;

public class SearchHotelTestPage extends BaseTest {

    private static final Logger logger = Logger.getLogger(SearchHotelTestPage.class.getSimpleName());

    @DataProvider(name = "data-provider")
    private Object[][] dataProvider() {
        return ExcelDataProvider.getTestData("Booking");
    }

    SearchHotelPage searchPage;
    LoginPage loginPage;
    WebDriverWait wait;

    @Test(dataProvider = "data-provider")
    public void SearchHotelTest(String Location, String Hotel, String RoomType,
                                 String NoOfRooms, String CheckInDate, String CheckOutDate,
                                 String AdultsPerRoom, String ChildrenPerRoom) throws Exception {

        searchPage = getBase().getInstance(SearchHotelPage.class);

        searchPage.selectLocation(Location);
        ITestListenerImpl.info("Selected Location - <b><font color=yellow>" + Location + "</font></b>", logger);

        searchPage.selectHotel(Hotel);
        ITestListenerImpl.info("Selected Hotel - <b><font color=yellow>" + Hotel + "</font></b>", logger);

        searchPage.selectRoomType(RoomType);
        ITestListenerImpl.info("Selected Room Type - <b><font color=yellow>" + RoomType + "</font></b>", logger);

        searchPage.selectNumberOfRooms(NoOfRooms);
        ITestListenerImpl.info("Selected Number of Rooms - <b><font color=yellow>" + NoOfRooms + "</font></b>", logger);

        searchPage.enterCheckInDate(CheckInDate);
        ITestListenerImpl.info("Entered Check-In Date - <b><font color=yellow>" + CheckInDate + "</font></b>", logger);

        searchPage.enterCheckOutDate(CheckOutDate);
        ITestListenerImpl.info("Entered Check-Out Date - <b><font color=yellow>" + CheckOutDate + "</font></b>", logger);

        searchPage.selectAdultsPerRoom(AdultsPerRoom);
        ITestListenerImpl.info("Selected Adults per Room - <b><font color=yellow>" + AdultsPerRoom + "</font></b>", logger);

        searchPage.selectChildrenPerRoom(ChildrenPerRoom);
        ITestListenerImpl.info("Selected Children per Room - <b><font color=yellow>" + ChildrenPerRoom + "</font></b>", logger);

        searchPage.clickSearch();
        ITestListenerImpl.info("Clicked on Search Button", logger);

        Assert.assertTrue(searchPage.isSelectHotelHeadingDisplayed(), "Select Hotel page not displayed");
        ITestListenerImpl.info("Successfully Navigated to Select Hotel Page", logger);

        String screenshot = TakeScreenShot.takeSnapShot("SelectHotelPage", PathDirectory.screenShotsPath);
        ITestListenerImpl.info("Hotel search executed - Screenshot", logger, screenshot, "To view please click on the screenshot");
    }
}