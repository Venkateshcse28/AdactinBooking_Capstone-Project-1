package de.chennai.guvi.basetest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import de.chennai.guvi.base.BasePage;
import de.chennai.guvi.base.Page;
import de.chennai.guvi.files.PathDirectory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class.getSimpleName());
    public static WebDriver driver; // Shared WebDriver instance
    private static WebDriverWait wait;
    private static Page base;
    public static Assertion hardAssert;

    public static ExtentHtmlReporter report;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void initialization() {
        if (driver == null) {
            openBrowser(PathDirectory.browserName);
            try {
                driver.get(PathDirectory.url);  // ✅ Only navigate to URL once
                logger.info("Successfully opened the URL.");
                wait = new WebDriverWait(driver, 50);
                base = new BasePage(driver, wait);
                hardAssert = new Assertion();
            } catch (Exception e) {
                logger.error("Failed to load the login page.", e);
            }
        } else {
            logger.info("Driver already initialized. Skipping URL reload.");
            wait = new WebDriverWait(driver, 50); // just make sure wait and base are still set
            base = new BasePage(driver, wait);
            hardAssert = new Assertion();
        }
    }


    public static void openBrowser(String browserType) {
        try {
            if (driver == null) { // Prevent reinitialization of WebDriver
                if ("chrome".equalsIgnoreCase(browserType)) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("profile.password_manager_enabled", false);
                    chromePrefs.put("credentials_enable_service", false);       
                    chromePrefs.put("autofill.profile_enabled", false);         
                    chromePrefs.put("download.default_directory", PathDirectory.downloadFilepath);
                    options.setExperimentalOption("prefs", chromePrefs);

                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    logger.info("Successfully launched Chrome Browser.");
                } else {
                    logger.warn("Invalid browser type: " + browserType + ". Launching Chrome as default.");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
        } catch (Exception e) {
            logger.error("Failed to launch browser.", e);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        initialization();
    }

   
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed after all tests.");
        }
    }

    
   
   


    public static Page getBase() {
        return base;
    }

    public static void setBase(Page base) {
        BaseTest.base = base;
    }
}
