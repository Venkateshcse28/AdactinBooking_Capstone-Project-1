/**
 * 
 */
package de.chennai.guvi.files;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Subhajit
 *
 */
public class ConfigFileReader {
	
	
	private static final Logger logger = Logger.getLogger(ConfigFileReader.class.getSimpleName());
	
	  public static Properties prop;

	    //To create single object to Config Properties file through-out the project
	public static Properties getConfigPropObject() {
		if (prop == null) {
			prop = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(new File(PathDirectory.configPath));
				logger.info("Successfully collected the details from config file.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
}