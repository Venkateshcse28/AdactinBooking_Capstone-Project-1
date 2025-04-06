package de.chennai.guvi.basetest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import de.chennai.guvi.files.PathDirectory;
import de.chennai.guvi.report.ITestListenerImpl;


@SuppressWarnings("rawtypes")
public class TestRunner {


	private static final Logger logger = Logger.getLogger(TestRunner.class.getSimpleName());
	
	/**
	 * @param args
	 */
	
	private static String name = "";
	private static TestNG testNg;
	private static ITestListenerImpl report;
	private static TestListenerAdapter testListenerAdapter;
	public static ArrayList<Class> testCaseList = new ArrayList<Class>();

	public static void testSuite(String suiteName, String testName, ArrayList<Class> className) {
		XmlSuite loginSuite = new XmlSuite();
		loginSuite.setName(suiteName);

		XmlTest first_test = new XmlTest();
		first_test.setName(testName);
		first_test.setSuite(loginSuite);

		List<XmlClass> fistlogin_classes = new ArrayList<XmlClass>();
		for (Class class_Name : className) {
			fistlogin_classes.add(new XmlClass(class_Name));
		}
		first_test.setXmlClasses(fistlogin_classes);

		loginSuite.addTest(first_test);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(loginSuite);
		testNg.setXmlSuites(suites);

	}
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			System.out.println(TestRunner.class.getResourceAsStream(PathDirectory.log4jFile));
			PropertyConfigurator.configure(TestRunner.class.getResourceAsStream(PathDirectory.log4jFile));
			name = PathDirectory.testCaseName;
			report = new ITestListenerImpl();
			testNg = new TestNG();
			testListenerAdapter = new TestListenerAdapter();
			testNg.addListener(testListenerAdapter);

			if (name.contains(",")) {
				String testCaseName[] = name.split(",");
				for (int i = 0; i < testCaseName.length; i++) {
					ProcessesTestCases.testCases(testCaseName[i].trim());
				}
				testSuite("Guvi Main Capstone Test Automation Project", "AdactinHotel Booking Application", testCaseList);
				testNg.addListener((ITestListener) report);
				logger.info("TestCase/TestCases is/are loadded for execution.");
				testNg.run();
			} else {
				ProcessesTestCases.testCases(name);
				testSuite("Guvi Main Capstone Test Automation Project", "AdactinHotel Booking Application", testCaseList);
				testNg.addListener((ITestListener) report);
				logger.info("TestCase is loadded for execution.");
				testNg.run();
			}

		} catch (Exception error) {
			logger.error(
					"Failed to execute test cases.Please check configuration details or Test_data sheet name or Test_data parameters.");
			error.printStackTrace();
		}
	}
}
