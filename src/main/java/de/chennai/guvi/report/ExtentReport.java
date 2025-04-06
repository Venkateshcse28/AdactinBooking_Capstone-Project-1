/**
 * 
 */
package de.chennai.guvi.report;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import de.chennai.guvi.basetest.BaseTest;
import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.utilities.TakeScreenShot;




 
@SuppressWarnings("deprecation")
public class ExtentReport extends BaseTest implements IReporter {
	
	private static final Logger logger = Logger.getLogger(ExtentReport.class.getSimpleName());

	
//	public static ExtentHtmlReporter report = null;
//	public static ExtentReports extent = null;
//	public static ExtentTest test = null;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	

	@SuppressWarnings("unused")
	private static void buildTestNodes(IResultMap tests, Status status) {

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				
//				test = extent.createTest(result.getClass().getName() + " :: " + result.getMethod().getMethodName());
				
				test = extent.createTest(result.getMethod().getMethodName());
				
				extentTest.set(test);
				
				report.start();
				report.stop();
				

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				
				String methodName = result.getMethod().getMethodName();
				String logText;
				Markup m;
				
				switch(result.getStatus()) {
				
				case ITestResult.SUCCESS:
					logText = "<b>Test Method " + methodName + " Successful</b>";
					m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
					extentTest.get().log(Status.PASS, m);
					Assert.assertTrue(true);
					break;
					
				case ITestResult.FAILURE:
					String exception = Arrays.toString(result.getThrowable().getStackTrace());
					extentTest.get().fail("<details><summary><b><font color=red>Exception Occured, Click to see the details: "
							+ "</font></b></summary>" + exception.replace(",", "<br>") + "</details> \n");
					String path = TakeScreenShot.takeScreenShot(result.getMethod().getMethodName(), PathDirectory.screenShotsPath);
					try {
						extentTest.get().fail("<b><font color=red>" + "ScreenShot of failure" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					}catch (Exception e) {
						extentTest.get().fail("Test Failed, cannot attach screen shot.");
					}
					logText = "<b>Test Method " + methodName + " Failed</b>";
					m = MarkupHelper.createLabel(logText, ExtentColor.RED);
					extentTest.get().log(Status.FAIL, m);
					Assert.assertFalse(false);
					break;
					
				case ITestResult.SKIP:
					logText = "<b>Test Method " + methodName + " Skipped</b>";
					m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
					extentTest.get().log(Status.SKIP, m);
					Assert.assertTrue(true);
					break;
					
				default:
					logText = "<b>Test Method " + methodName + " Skipped</b>";
					m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
					extentTest.get().log(Status.SKIP, m);
					Assert.assertTrue(true);
					break;	
				}
			}
		}
	}

	public static ExtentReports reportSetup() {
		String reportLocation = PathDirectory.extendReportPath;
		report = new ExtentHtmlReporter(reportLocation);	
		report.config().setEncoding("utf-8");
		report.config().setDocumentTitle("Guvi mini Project");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.DARK);		
		logger.info("Extent Report location initialized . . .");

		extent = new ExtentReports();
		extent.attachReporter(report);		
		extent.setSystemInfo("Application :", "BBM Web Client");
		extent.setSystemInfo("Operating System :", System.getProperty("os.name"));
		extent.setSystemInfo("Host Name :", System.getProperty("user.name"));
		extent.setSystemInfo("Java Version :", System.getProperty("java.version"));
		extent.setSystemInfo("Machine Name : ", System.getProperty("machine.name"));
		extent.setSystemInfo("IP Address : ", System.getProperty("machine.address"));
		extent.setSystemInfo("URL : ", PathDirectory.url);
		extent.setSystemInfo("Browser :", PathDirectory.browserName);
		
		
		return extent;
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		
	}
	
}
