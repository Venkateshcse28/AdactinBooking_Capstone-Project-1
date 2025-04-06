/**
 * 
 */
package de.chennai.guvi.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.utilities.JavaScriptExecutorUtil;



public class BasePage extends Page {
	
	private static final Logger logger = Logger.getLogger(BasePage.class.getSimpleName());
	
	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void selectByVisibleText(By locator, String value) {
	    Select dropdown = new Select(getElement(locator));
	    dropdown.selectByVisibleText(value);
	}

	public void doClick(By locator) {
		//JavaScriptExecutorUtil.flash(getElement(locator), driver);
		driver.findElement(locator).click();
		logger.info("Successfully occur click operation.");
	}
	
	public void doSendKeys(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
		logger.info("Successfully occur sendkey operation.");
	}
	
	public void doClear(By locator) {
		driver.findElement(locator).clear();
		logger.info("Successfully occur clear operation.");
	}

	
	//Below function is wrapper function
	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		}catch(Exception e) {
			logger.error("Some error while creating element: " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = driver.findElements(locator);
			return element;
		}catch(Exception e) {
			logger.error("Some error while creating element: " + locator.toString());
			e.printStackTrace();
		}
		return element;
	
	}
	
	@Override
	public void waitForElementPrasent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {
			logger.error("Exception is occurred while waiting for Presence of Element");
			e.printStackTrace();
		}
	}

	@Override
	public void waitForElementVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(Exception e) {
			logger.error("Exception is occurred while waiting for Visibility of Element");
			e.printStackTrace();
		}
	}

	@Override
	public void waitForElementToBeClickable(By locator) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}catch(Exception e) {
			logger.error("Exception is occurred while waiting for Clickable Element");
			e.printStackTrace();
		}
	}

	@Override
	public void waitForElementToBeInVisible(By locator) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}catch(Exception e) {
			logger.error("Exception is occurred while waiting for InVisibility of Element");
			e.printStackTrace();
		}
	}

}
