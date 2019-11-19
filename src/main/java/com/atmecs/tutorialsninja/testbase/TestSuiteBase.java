package com.atmecs.tutorialsninja.testbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.atmecs.tutorialsninja.helper.PropertiesFileReader;
import com.atmecs.tutorialsninja.utils.UtilityFiles;
import com.atmecs.tutorialsninja.utils.WaitsandSimpleAlert;;
/*
 * This method used to select the browser or grid and
 *  leads to work on that particular browser
 *  
 *  @author   Magesh 
*/
public class TestSuiteBase {
	public Logger log = Logger.getLogger("config files");
	public PropertiesFileReader propertyreader = new PropertiesFileReader();
	public WaitsandSimpleAlert wait = new WaitsandSimpleAlert();
	private TestSuiteGridBase invoke = new TestSuiteGridBase();
	public UtilityFiles utils=new UtilityFiles();
	
	String geturlstring;
	public String browser;
	public WebDriver driver;

	@BeforeClass
	public void browserSelect() {

		geturlstring = propertyreader.getLocatorValue("config.runthrough");
		if (geturlstring.equalsIgnoreCase("browser")) {
			String browserName = propertyreader.getLocatorValue("config.browsername");

			browser = browserName.toUpperCase();

			switch (browser) {
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", Classpaths.Chrome_file);
				driver = new ChromeDriver();
				break;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", Classpaths.Firefox_file);
				driver = new FirefoxDriver();
				break;
			case "INTERNET EXPLORER":
				System.setProperty("webdriver.ie.driver", Classpaths.IE_file);
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
				driver = new InternetExplorerDriver(ieCapabilities);
				break;
			default:
				log.info("Driver name need to given correctly ");
			}
			if (driver != null) {
				log.info(browser + " DriverInitiated");
			}

		} else if (geturlstring.equalsIgnoreCase("grid")) {

			driver = invoke.gridSelect();
		}
		wait.implicitWait(driver, 20);
		wait.pageLoadTimeout(driver, 100);
	}

	@AfterClass
	public void browserQuit() {
		driver.close();
	}

}
