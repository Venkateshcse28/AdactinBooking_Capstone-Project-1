package de.chennai.guvi.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.pages.BookingConfirmationPage;
import de.chennai.guvi.report.ITestListenerImpl;
import de.chennai.guvi.utilities.TakeScreenShot;

public class BookingConfirmationTestPage extends BaseTest {

    private static final Logger logger = Logger.getLogger(BookingConfirmationTestPage.class.getSimpleName());

    BookingConfirmationPage confirmPage;

    @Test
    public void verifyBookingConfirmation() throws Exception {
        confirmPage = getBase().getInstance(BookingConfirmationPage.class);

        String orderId = confirmPage.getOrderID();
        Assert.assertNotNull(orderId, "Order ID was not generated.");
        ITestListenerImpl.info("Order ID generated: <b><font color=yellow>" + orderId + "</font></b>", logger);

        String screenshot = TakeScreenShot.takeSnapShot("BookingConfirmation", PathDirectory.screenShotsPath);
        ITestListenerImpl.info("Booking confirmation screenshot", logger, screenshot, "Click to view");

        confirmPage.clickLogout();
        ITestListenerImpl.info("Successfully clicked Logout", logger);
        Thread.sleep(3000);
    }
}
