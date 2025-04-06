/**
 * 
 */
package de.chennai.guvi.utilities;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;


public class SeleniumUtils extends BasePage {
	
	private static final Logger logger = Logger.getLogger(SeleniumUtils.class.getSimpleName());

	public SeleniumUtils(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	// Drag & Drop
	public void dragAndDropOperation(WebElement DragFrom, WebElement DropAt) {
		Actions action = new Actions(driver);
		action.dragAndDrop(DragFrom, DropAt).build().perform();
	}
	
	public void pageDownAction() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Space Keyword Button
	public void keySpaceAction() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.SPACE).build().perform();
	}
	
	// Key Down
	public void keyDownAction() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.DOWN).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {
			logger.error("Getting error during keyDownAction operation.");
			e.printStackTrace();
		}
	}
	
	// Key Escape
	public void keyEscapeAction() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			logger.error("Getting error during keyESCAPEAction operation.");
			e.printStackTrace();
		}
	}

	// Right Click
	public void rightClickElement(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.contextClick(element).perform();
		} catch (Exception e) {
			logger.log(Level.WARN,"Getting error during rightClickElement operation", e);
			Thread.currentThread().interrupt();
		}
	}
	
	
	// Right Click
	public void moveToElement(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
		} catch (Exception e) {
			logger.error("Getting error during rightClickElement operation.");
			e.printStackTrace();
		}
	}
	
	// Enter Operation
	public void enterButtonAction() {
		try {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {
			logger.error("Getting error during rightClickElement operation.");
			e.printStackTrace();
		}
	}
	
	// JavaScript alert handling
	public void acceptingJSAlert() {
		try {
			Thread.sleep(1000);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			// Store the alert text in a variable
			String text = alert.getText();
			logger.info("Fetching the alert message from popup :: " + text);
			// Press the OK button
			alert.accept();
		} catch (Exception e) {
			logger.log(Level.WARN,"Getting error during ClickElement operation", e);
		    Thread.currentThread().interrupt();

		}
	}
	
	public void switchDriverToAlreadyOpenTab(String browserTitle) {
				try {
			for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				//String browserTabTitle = driver.getTitle();
				String browserTabTitle = driver.getCurrentUrl();
				if (browserTabTitle.contains(browserTitle)) {
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
