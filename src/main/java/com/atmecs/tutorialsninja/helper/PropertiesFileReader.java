package com.atmecs.tutorialsninja.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.atmecs.tutorialsninja.reports.Log4j;
import com.atmecs.tutorialsninja.testbase.Classpaths;

/*
 * method to getting locator from property file and as well as dynamic locator values from property file
 * 
 * 
 * @author Magesh
*/public class PropertiesFileReader {
	Properties prop;
	private Log4j log =new Log4j();
	public PropertiesFileReader() {
		List<String> propsFiles = Arrays.asList(Classpaths.url_file,Classpaths.LocHome_file,Classpaths.LocHotSauce_file);
		prop = new Properties();

		for (String filePath : propsFiles) {
			try {
				prop.load(new FileInputStream(new File(filePath)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getLocatorValue(String elements) {
		String data = prop.getProperty(elements);
		return data;
	}

	public String getnewlocator(WebDriver driver, String Xpath, String replacingtext, String expectedtext) {
		log.info("Replacing old locator");
		String newlocator = getLocatorValue(Xpath).replace(replacingtext, expectedtext);
		log.info("Locator has been replaced");
		return newlocator;
	}

}
