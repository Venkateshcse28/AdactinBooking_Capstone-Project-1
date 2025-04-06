package de.chennai.guvi.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.pages.SelectHotelPage;
import de.chennai.guvi.report.ITestListenerImpl;
import de.chennai.guvi.utilities.TakeScreenShot;

public class SelectHotelTestPage extends BaseTest {

    private static final Logger logger = Logger.getLogger(SelectHotelTestPage.class.getSimpleName());

    SelectHotelPage selectPage;
    WebDriverWait wait;

    @Test
    public void SelectHotelTest() throws Exception {
        selectPage = getBase().getInstance(SelectHotelPage.class);

        selectPage.selectHotelRadio();
        ITestListenerImpl.info("Successfully selected hotel radio button", logger);

        selectPage.clickContinue();
        ITestListenerImpl.info("Successfully clicked Continue button", logger);

        Assert.assertTrue(selectPage.isBookHotelHeadingDisplayed(), "Book Hotel page not displayed");
        ITestListenerImpl.info("Successfully navigated to Book A Hotel page", logger);

        String screenshot = TakeScreenShot.takeSnapShot("BookHotelPage", PathDirectory.screenShotsPath);
        ITestListenerImpl.info("Book Hotel step screenshot", logger, screenshot, "Click to view");
    }
}