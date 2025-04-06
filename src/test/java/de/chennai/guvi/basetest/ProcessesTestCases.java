/**
 * 
 */
package de.chennai.guvi.basetest;

import org.apache.log4j.Logger;

public class ProcessesTestCases {
	
	
	private static final Logger logger = Logger.getLogger(ProcessesTestCases.class.getSimpleName());
	
	
	public static void testCases(String testCaseName) {
		
		switch (testCaseName.toLowerCase()) {

			
	
						
		case "loginpage":
			TestRunner.testCaseList.add(de.chennai.guvi.testcases.LoginPageTest.class);
			logger.info("Login test steps Validation");
			break;
			
		case "bookingpage":
			TestRunner.testCaseList.add(de.chennai.guvi.testcases.SearchHotelTestPage.class);
			logger.info("Enter all booking details");
			break;
			
		case "hotelselect":
			TestRunner.testCaseList.add(de.chennai.guvi.testcases.SelectHotelTestPage.class);
			logger.info("display and select the hotel");
			break;
	
			
		case "paymentdetails":
			TestRunner.testCaseList.add(de.chennai.guvi.testcases.BookHotelTestPage.class);
			logger.info("Payment details filling page");
			break;
	
		case "logout":
			TestRunner.testCaseList.add(de.chennai.guvi.testcases.BookingConfirmationTestPage.class);
			logger.info("Booking Confirmation and Logout page");
			break;
	
	

		}
	}
	
	
	
}
