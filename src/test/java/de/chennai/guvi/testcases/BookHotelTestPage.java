package de.chennai.guvi.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.ExcelDataProvider;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.pages.BookHotelPage;
import de.chennai.guvi.report.ITestListenerImpl;
import de.chennai.guvi.utilities.TakeScreenShot;

public class BookHotelTestPage extends BaseTest {

    private static final Logger logger = Logger.getLogger(BookHotelTestPage.class.getSimpleName());


    @DataProvider(name = "data-provider")
    private Object[][] dataProvider() {
        return ExcelDataProvider.getTestData("BookHotels");
    }
    BookHotelPage bookPage;

    @Test(dataProvider = "data-provider")
    public void BookHotelTest(String fname, String lname, String address, String ccNum, String ccType,
                               String expMonth, String expYear, String cvv) throws Exception {

        bookPage = getBase().getInstance(BookHotelPage.class);

        bookPage.enterFirstName(fname);
        ITestListenerImpl.info("Entered First Name - <b><font color=yellow>" + fname + "</font></b>", logger);

        bookPage.enterLastName(lname);
        ITestListenerImpl.info("Entered Last Name - <b><font color=yellow>" + lname + "</font></b>", logger);

        bookPage.enterBillingAddress(address);
        ITestListenerImpl.info("Entered Billing Address - <b><font color=yellow>" + address + "</font></b>", logger);

        bookPage.enterCreditCardNo(ccNum);
        ITestListenerImpl.info("Entered Credit Card No - <b><font color=yellow>" + ccNum + "</font></b>", logger);

        bookPage.selectCreditCardType(ccType);
        ITestListenerImpl.info("Selected Credit Card Type - <b><font color=yellow>" + ccType + "</font></b>", logger);

        bookPage.selectExpiryMonth(expMonth);
        ITestListenerImpl.info("Selected Expiry Month - <b><font color=yellow>" + expMonth + "</font></b>", logger);

        bookPage.selectExpiryYear(expYear);
        ITestListenerImpl.info("Selected Expiry Year - <b><font color=yellow>" + expYear + "</font></b>", logger);

        bookPage.enterCVVNumber(cvv);
        ITestListenerImpl.info("Entered CVV Number - <b><font color=yellow>" + cvv + "</font></b>", logger);

        bookPage.clickBookNow();
        ITestListenerImpl.info("Clicked Book Now Button", logger);

        Assert.assertTrue(bookPage.isOrderIdGenerated(), "Booking failed or Order ID not generated");

        String screenshot = TakeScreenShot.takeSnapShot("BookingSuccess", PathDirectory.screenShotsPath);
        ITestListenerImpl.info("Booking Confirmation Screenshot", logger, screenshot, "Click to view");
    }
}
