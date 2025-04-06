
package de.chennai.guvi.utilities;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;

public class JavaScriptExecutorUtil extends BasePage {
	
	private static final Logger logger = Logger.getLogger(JavaScriptExecutorUtil.class.getSimpleName());

	public JavaScriptExecutorUtil(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public static String getValue(WebDriver driver, String javaScripts) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		String text = (String) jsExecutor.executeScript(javaScripts);  
		logger.info("get the value. ");
		return text;
	}
	
	// Scroll to the particular element
	public static void scrollIntoView(WebElement element, WebDriver driver) {
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception error) {
			logger.error("Unable to do scroll operation.");
		}
	}
	
	public static void waitForPageLoad(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("return document.readyState").equals("complete"); 
	}
	
	public void openNewTab(WebDriver driver, String url) {
		try{
			JavascriptExecutor js = ((JavascriptExecutor) driver);
		    js.executeScript("window.open('about:blank','_blank');");
		    for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			    }
		    driver.get(url);
		}catch(Exception error){
			logger.error("Unable Open New Browser Tab.");
		 }
	}
	
	// Highlighting The element with green
	public static void elementHighlightGreen(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid green'", element);
		logger.info("Executing Highlighting operation.");
	}
	
	// Highlighting The element with red
	public static void elementHighlightRed(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		logger.info("Executing Highlighting operation.");
	}
	
	public static void flash(WebElement element, WebDriver driver) {
		String backGroundColor= element.getCssValue("");
		for(int i = 0; i<10 ; i++) {
			changeColor("rgb(0,200,0)", element, driver);
			changeColor(backGroundColor, element, driver);
		}	
	}
	
	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			logger.log(Level.WARN, "Interrupted!", e);
		    Thread.currentThread().interrupt();
		}
	}
	
	public void scrollDownVertically(WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		logger.info("scrolling down vertically...");
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically(WebDriver driver){
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		logger.info("scrolling up vertically...");
		js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	
}
