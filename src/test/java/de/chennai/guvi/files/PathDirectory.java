/**
 * 
 */
package de.chennai.guvi.files;

import java.io.File;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PathDirectory {

	static String directory = getDirectoryPath();
	
	public static String getDirectoryPath() {
		String str = FileSystems.getDefault().getPath(".").toAbsolutePath().toString() ;
	    if (str.charAt(str.length()-1)=='.'){
	        str = str.replace(str.substring(str.length()-1), "");
	        return str;
	    } else{
	        return str;
	    }
	}
	
	public static String configPath = directory+"src"+File.separator+"test"+File.separator+"java"+File.separator+"de"+File.separator+"chennai"+File.separator+"guvi"+File.separator+"files"+File.separator+"config.properties";
	public static String exceldata = directory+"src"+File.separator+"test"+File.separator+"java"+File.separator+"de"+File.separator+"chennai"+File.separator+"guvi"+File.separator+"files"+File.separator+"Guvi.xlsx";		

	
	static LocalDateTime timeObj = LocalDateTime.now();
    static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    public static String formattedDate = timeObj.format(myFormatObj);
	
	
	// Report and Screen shot
	public static String screenShotsPath = "./";
	public static String reportName = ConfigFileReader.getConfigPropObject().getProperty("Test_Report_Name").trim();
	public static String browserName = ConfigFileReader.getConfigPropObject().getProperty("browser").trim();
	//public static String testReportName = reportName+"_"+browserName;
	public static String testReportName = reportName+"-"+formattedDate+"-"+browserName;
	public static String extendReportPath = "./"+testReportName+".html";
	public static String log4jFile = "log4j.properties";
	public static String testCaseName = ConfigFileReader.getConfigPropObject().getProperty("TestCase_Name").trim();
	public static String url = ConfigFileReader.getConfigPropObject().getProperty("url").trim();

	public static String downloadFilepath = directory +"Downloads";

}
