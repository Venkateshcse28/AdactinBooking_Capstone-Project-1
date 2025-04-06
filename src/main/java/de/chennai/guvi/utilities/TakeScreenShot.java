/**
 * 
 */
package de.chennai.guvi.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.chennai.guvi.base.BasePage;



public class TakeScreenShot extends BasePage {
	
	private static final Logger logger = Logger.getLogger(TakeScreenShot.class.getSimpleName());

	public TakeScreenShot(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public static String takeScreenShot(String methodName, String path) {
		if(methodName.contains("")) {
			methodName.replace(" ", "");
		}
		String fileName = getScreenshotName(methodName);
//		String directory = System.getProperty("user.dir") + "ScreenShots";
		
//		String final_path_of_screenShot = PathDirectory.screenShotsPath + fileName;
		
		String final_path_of_screenShot = path + fileName;
		try {
//			Screenshot srcFile = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
//			ImageIO.write(srcFile.getImage(),"PNG", new File(final_path_of_screenShot));
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(final_path_of_screenShot));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return final_path_of_screenShot;
	}
	
	public static String takeSnapShot(String methodName,String path) throws Exception{
		
		if(methodName.contains("")) {
			methodName.replace(" ", "");
		}
		
		String fileName = getScreenshotNames(methodName);

		String final_path_of_screenShot = path + fileName;
		try {
//			Screenshot srcFile = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
//			ImageIO.write(srcFile.getImage(),"PNG", new File(final_path_of_screenShot));
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(final_path_of_screenShot));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return final_path_of_screenShot;
	}
	
	
	
	
	
		public static String getScreenshotName(String methodName) {
			Date date = new Date();
			String fileName = methodName + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
			return fileName;
		}
		
		public static String getScreenshotNames(String methodName) {
			//Date date = new Date();
			String fileName = methodName+".png";
			return fileName;
		}
		
		public static String takeSnapShotcrop(String methodName, String path, int x, int y, int width, int height) throws Exception {
		    
		    if (methodName.contains(" ")) {
		        methodName = methodName.replace(" ", "");
		    }

		    // Get the screenshot file name
		    String fileName = getScreenshotNames(methodName);
		    String finalPathOfScreenshot = path + fileName;

		    try {
		        // Take a screenshot
		        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		       
		        BufferedImage fullImg = ImageIO.read(screenshot);

		        // Crop code
		        BufferedImage croppedImg = fullImg.getSubimage(x, y, width, height);

		        
		        ImageIO.write(croppedImg, "png", new File(finalPathOfScreenshot));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    // Return the path of the saved screenshot
		    return finalPathOfScreenshot;
		}

		

}
