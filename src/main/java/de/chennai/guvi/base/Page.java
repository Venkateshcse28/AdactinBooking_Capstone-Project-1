/**
 * 
 */
package de.chennai.guvi.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Page {
	
	private static final Logger logger = Logger.getLogger(Page.class.getSimpleName());
	
	public static WebDriver driver;
	public static WebDriverWait wait; 
	
	
	@SuppressWarnings("static-access")
	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public abstract WebElement getElement(By locator);
	
	public abstract void waitForElementPrasent(By locator);
	
	public abstract void waitForElementVisible(By locator);
	
	public abstract void waitForElementToBeClickable(By locator);
	
	public abstract void waitForElementToBeInVisible(By locator);

	
	//Java Generic Concept
	@SuppressWarnings("static-access")
	public <TestPage extends BasePage> TestPage getInstance(Class<TestPage> pageClass) {

		try {
			return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver,
					this.wait);
		} catch (Exception error) {
			logger.error("Failed to get Page class object.");
			error.printStackTrace();
			return null;
		}
	}
	
}
