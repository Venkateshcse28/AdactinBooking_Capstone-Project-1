/**
 * 
 */
package de.chennai.guvi.report;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.utilities.TakeScreenShot;




/**
 * @author Venkatesh
 *
 */
public class ITestListenerImpl extends BaseTest implements ITestListener {

	private static final Logger logger = Logger.getLogger(ITestListenerImpl.class.getSimpleName());

	public static ITestResult result = null;
	@SuppressWarnings("unused")
	private static ExtentReports extent = ExtentReport.reportSetup();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static String isExecutableFlag = null;


	
	 // Need to create these methods, so that they can be called in testcase

	public static void info(String message, Logger logger) {
		try {
			logger.info(message);
			test.createNode(message);
			
			// Below line will print the line number.
//			System.out.println("The Current Line Number is: " + new Throwable().getStackTrace()[0].getLineNumber());
		} catch (Exception error) {
			logger.error("Failed to print info log on report");
			error.printStackTrace();
		}
	}

	public static void warn(String message, Logger logger) {
		try {
			logger.warn(message);
			test.createNode(message);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public static void error(String message, Logger logger) {
		try {
			logger.error(message);
			test.createNode(message);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	public static void skip(String message, Logger logger) {
		try {
			logger.fatal(message);
			test.createNode(message).log(Status.SKIP, message);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public static void info(String message, Logger logger, String path, String ScreenShot_message) {
		try {
			logger.info(message);
			test.createNode(message + "<b><font color=4682B4>" + " ScreenShot Attached " + "</font>&#8595</b>")
					.pass(ScreenShot_message, MediaEntityBuilder.createScreenCaptureFromPath(path).build()).log(Status.PASS, message);
		} catch (Exception error) {
			logger.error("Failed to print info log on report");
			error.printStackTrace();
		}
	}
	
	public static void error(String message, Logger logger, String path, String ScreenShot_message) {
		try {
			logger.error(message);
			test.createNode(message + "<b><font color=4682B4>" + " ScreenShot Attached " + "</font>&#8595</b>")
			.error(ScreenShot_message, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	
	
	public void onTestSuccess(ITestResult result) {
	    try {
	        if (isExecutableFlag != null && isExecutableFlag.equalsIgnoreCase("yes")) {
	            String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful</b>";
	            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
	            
	            if (extentTest.get() != null) {
	                extentTest.get().log(Status.PASS, m);
	            } else {
	                logger.error("ExtentTest is null in onTestSuccess for test: " + result.getMethod().getMethodName());
	            }
	            
	            Assert.assertTrue(true);
	            ExtentReport.extent.flush();
	        } else if (isExecutableFlag == null) {  // Fixed condition
	            String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful</b>";
	            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
	            
	            if (extentTest.get() != null) {
	                extentTest.get().log(Status.PASS, m);
	            } else {
	                logger.error("ExtentTest is null in onTestSuccess for test: " + result.getMethod().getMethodName());
	            }
	            
	            Assert.assertTrue(true);
	            ExtentReport.extent.flush();
	        }
	    } catch (Exception e) {
	        logger.error("Error in onTestSuccess: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	public void onTestFailure(ITestResult result) {
	    try {
	        Throwable throwable = result.getThrowable();
	        String exceptionDetails = Arrays.toString(throwable.getStackTrace());

	        if (throwable instanceof Error) {
	            // Mark the test as "error" in the report
	            extentTest.get().error("<details><summary><b><font color=red>Error Occurred, Click to see details: "
	                    + "</font></b></summary>" + exceptionDetails.replace(",", "<br>") + "</details> \n");

	            String screenshotPath = TakeScreenShot.takeScreenShot(result.getMethod().getMethodName(), PathDirectory.screenShotsPath);
	            extentTest.get().error("<b><font color=red>" + "Attached Screenshot is after error step." + "</font></b>",
	                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	            String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Errored</b>";
	            Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
	            extentTest.get().log(Status.ERROR, m);
	        } else {
	            // Mark the test as "fail" in the report
	            extentTest.get().fail("<details><summary><b><font color=red>Exception Occurred, Click to see details: "
	                    + "</font></b></summary>" + exceptionDetails.replace(",", "<br>") + "</details> \n");

	            String screenshotPath = TakeScreenShot.takeScreenShot(result.getMethod().getMethodName(), PathDirectory.screenShotsPath);
	            extentTest.get().fail("<b><font color=red>" + "Attached Screenshot is after failure step." + "</font></b>",
	                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	            String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Failed</b>";
	            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
	            extentTest.get().log(Status.FAIL, m);
	        }
	    } catch (Exception e) {
	        extentTest.get().fail("Test failed, but encountered an issue while logging failure details.");
	        e.printStackTrace();
	    } finally {
	        ExtentReport.extent.flush();
	    }
	}

	public void onTestSkipped(ITestResult result) {
	    try {
	        String exceptionDetails = Arrays.toString(result.getThrowable().getStackTrace());
	        extentTest.get().skip("<details><summary><b><font color=Tomato>Exception Occurred, Click to see details: "
	                + "</font></b></summary>" + exceptionDetails.replace(",", "<br>") + "</details> \n");

	        String screenshotPath = TakeScreenShot.takeScreenShot(result.getMethod().getMethodName(), PathDirectory.screenShotsPath);
	        extentTest.get().skip("<b><font color=Tomato>" + "Attached Screenshot is after skipping step." + "</font></b>",
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
	        Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
	        extentTest.get().log(Status.SKIP, m);
			Assert.assertTrue(true);
	    } catch (Exception e) {
	        extentTest.get().skip("Test skipped, but encountered an issue while logging skip details.");
	        e.printStackTrace();
	    } finally {
	        ExtentReport.extent.flush(); 
	    }
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext result) {

		
	}

	public void onFinish(ITestContext context) {
	    logger.info("Execution completed on UAT env ...");
	    if (ExtentReport.extent != null) {
	        ExtentReport.extent.flush();
	    }
	    logger.info("Generated Report. . .");
	}


	private static boolean alreadyLogged = false;

	@Override
	public void onTestStart(ITestResult result) {
	    if (!alreadyLogged) {
	        Logger logger = Logger.getLogger(ITestListenerImpl.class);
	        logger.info("Execution started on UAT env ...");
	        alreadyLogged = true;
	    }
	}


	
	

}
