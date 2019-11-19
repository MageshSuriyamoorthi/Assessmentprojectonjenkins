package com.atmecs.tutorialsninja.testbase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.atmecs.tutorialsninja.helper.PropertiesFileReader;
/*
 * This method used to select the browser or grid and
 *  leads to work on that particular browser
 *  
 *  @author   Magesh 
*/
public class TestSuiteGridBase {
	URL url;
	String nodeurl, browser;
	public WebDriver driver;
	public PropertiesFileReader property = new PropertiesFileReader();
	public DesiredCapabilities capability = new DesiredCapabilities();

	public WebDriver gridSelect() {
		browser = property.getLocatorValue("config.browsername");
		String browserName = browser.toUpperCase();
		nodeurl = property.getLocatorValue("node_url");

		switch (browserName) {
		case "CHROME":
			//capability = DesiredCapabilities.chrome();

			capability.setCapability(CapabilityType.BROWSER_NAME, "chrome");

			break;
		case "FIREFOX":
			//capability = DesiredCapabilities.firefox();

			capability.setCapability(CapabilityType.BROWSER_NAME, "firefox");

			break;
		case "INTERNET EXPLORER":
			//capability = DesiredCapabilities.internetExplorer();

			capability.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");

			break;
		}
		capability.setPlatform(Platform.WINDOWS);

		capability.setBrowserName(browser);
		try {
			url = new URL(nodeurl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver = new RemoteWebDriver(url, capability);

		return driver;
		/*
		 * driver.get("www.google.com"); try { Thread.sleep(5000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println("sleep");
		 */
	}

}
