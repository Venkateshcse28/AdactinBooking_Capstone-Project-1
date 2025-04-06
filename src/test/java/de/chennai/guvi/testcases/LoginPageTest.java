package de.chennai.guvi.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.ExcelDataProvider;
import de.chennai.guvi.pages.LoginPage;
import de.chennai.guvi.report.ITestListenerImpl;

public class LoginPageTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(LoginPageTest.class.getSimpleName());

    @DataProvider(name = "data-provider")
    private Object[][] dataProvider() {
        return ExcelDataProvider.getTestData("Login");
    }

    LoginPage loginPage;
    WebDriverWait wait;

    @Test(dataProvider = "data-provider")
    public void testLogin(String Username, String Password) throws Exception {
    	BaseTest.test = extent.createTest("Adactiin Bookig Application");
        ITestListenerImpl.extentTest.set(BaseTest.test);
        loginPage = getBase().getInstance(LoginPage.class);

        Assert.assertTrue(loginPage.isLoginButtonTextValid("Login"), "Login button text does not match");


        // Valid Login Test
        loginPage.enterUsername(Username);
        ITestListenerImpl.info("Entered valid username", logger);
        Thread.sleep(1500);

        loginPage.enterPassword(Password);
        ITestListenerImpl.info("Entered valid password", logger);
        Thread.sleep(1500);

        loginPage.clickLogin();
        ITestListenerImpl.info("Clicked login button with valid credentials", logger);
        Thread.sleep(1500);

        Assert.assertTrue(loginPage.isHotelHeadingDisplayed(), "Hotel heading not found after login please check");
        ITestListenerImpl.info("Successfully logged in and hotel heading validated", logger);
    }
}
