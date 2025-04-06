/**
 * 
 */
package de.chennai.guvi.utilities;

import java.time.Duration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;

public class WaitUtils extends BasePage{

	private static final Logger logger = Logger.getLogger(WaitUtils.class.getSimpleName());
	
	public WaitUtils(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	//1. These 2 variable we used in TestBase Class for Page Load and Implicit Wait.
		public static long Page_Load_TimeOut = 3;
		public static long Implicit_Wait = 70;
		
		
//		public static By loader_icon = By.className("preloading");
//		public static By loader_icon = By.xpath("//div[@class='preloading']/p[2]");
		private static By loader_icon = By.cssSelector("img[src='resources/bftmicons/loader-squares.gif']");
		private By loader_text = By.xpath("//span[text()='Loading...']");
		
		@SuppressWarnings("deprecation")
		public void loader() {
			try {
//				Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).pollingEvery(2, TimeUnit.SECONDS)
//						.withTimeout(30, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class)
//						.ignoring(NoSuchElementException.class);
//				fluentWait.until(ExpectedConditions.visibilityOf(getElement(loader_icon)));
				
				Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
	                    .ignoring(NoSuchElementException.class);
				wait.until(ExpectedConditions.invisibilityOf(getElement(loader_icon)));
			} catch (Exception error) {
				logger.error("Loader Issue. Loader appears or not :: null");
				error.printStackTrace();
			}
		}
		
	@SuppressWarnings("deprecation")
	public void loaderText() {
		try {
			if (getElement(loader_text).isDisplayed()) {
				logger.info("loaderText value:: " + getElement(loader_text).isDisplayed());
				Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
	                    .ignoring(NoSuchElementException.class);
				wait.until(ExpectedConditions.invisibilityOf(getElement(loader_text)));
			}
		} catch (Exception error) {
			logger.error("Loader text issue.");
			error.printStackTrace();
		}
	}
		
		/**
		 *     Fluent Wait 
		 * @param driver
		 * @param locator
		 */
		
		 //Fluent Wait for Click Operation
	public void waitThenClick(WebDriver driver, By locator) {
		try {
			@SuppressWarnings("deprecation")
			Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
            .ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			// if(getElement(locator).isDisplayed())
			// {
			// doClick(locator);
			// }
		} catch (TimeoutException toe) {
			logger.error("========================" + "\nException from Fluent Wait: " + toe.getMessage()
					+ "\n=======================");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			logger.error("=========================" + "\nException from Fluent Wait: " + e.getMessage()
					+ "\n=======================");
		}
	}
	    
	    //Fluent Wait for till the locator Invisible
	public void waitTillElementInVisible(WebDriver driver, By locator) {
		try {
			@SuppressWarnings("deprecation")
			Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
            .ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			logger.error("Tried to Find:>> " + locator.toString());
			toe.printStackTrace();
		} catch (Exception e) {
			logger.error("Tried to Find:>> " + locator.toString());
			e.printStackTrace();
		}
	}
		
	    //Fluent Wait for till the locator visible
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void waitTillElementVisible(WebDriver driver, By locator) {
		try {
			@SuppressWarnings({ "deprecation", "unchecked" })
			Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
            .ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			logger.error("Tried to Find:>> " + locator.toString());
			toe.printStackTrace();
		} catch (Exception e) {
			logger.error("Tried to Find:>> " + locator.toString());
			e.printStackTrace();
		}
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void waitTillElementInVisible(WebDriver driver, By locator, int waitTime) {
		try {
			@SuppressWarnings("deprecation")
			Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(30))
            .ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			logger.error("Tried to Find:>> " + locator.toString());
			toe.printStackTrace();
		} catch (Exception e) {
			logger.error("Tried to Find:>> " + locator.toString());
			e.printStackTrace();
		}
	}

}
