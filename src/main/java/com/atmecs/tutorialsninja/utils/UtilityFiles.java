package com.atmecs.tutorialsninja.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.atmecs.tutorialsninja.helper.ExcelReader;
import com.atmecs.tutorialsninja.reports.Log4j;
/*
 * This method used to handle various selenium commands for the script.
 * 
 *  @author  Magesh
*/
public class UtilityFiles {
	private Log4j log =new Log4j();
	private ExcelReader datareader = new ExcelReader();
//method for by locator
	public By locator(String locator) {
		By by = null;
		String[] locators = locator.split(":",2);

		switch (locators[0].toUpperCase()) {
		case "ID":
			by = By.id(locators[1]);
			break;
		case "NAME":
			by = By.name(locators[1]);
			break;
		case "CLASSNAME":
			by = By.className(locators[1]);
			break;
		case "LINKTEXT":
			by = By.linkText(locators[1]);
			break;
		case "PARTIAL":
			by = By.partialLinkText(locators[1]);
			break;
		case "XPATH":
			by = By.xpath(locators[1]);
			break;
		case "CSS":
			by = By.cssSelector(locators[1]);
			break;
		default:
			log.info("Give correct locator");
		}
		return by;
	}
//method for find element
	public WebElement findelement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(locator(locator));
		return element;
	}
	public List<WebElement> findelements(WebDriver driver, String locator) {
		List<WebElement> element = driver.findElements(locator(locator));
		return element;
	}
//method to get title from the webpage
	public String gettitle(WebDriver driver) {
		String getval = driver.getTitle();
		log.info("Got title from URL");
		return getval;
	}
//method to open url in drivers
	public void geturl(WebDriver driver, String string) {
		driver.get(string);
		log.info("URL opened");
	}
//method to maximize the website
	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
		log.info("Browser Maximized");
	}
//method for clicking a button
	public WebElement click(WebDriver driver, String locatorValue) {
		log.info("Click is progressed");
		WebElement value = findelement(driver, locatorValue);
		try {
			value.click();
		} catch (Exception exception) {
			log.info(exception + "arose");
		}
		log.info("click is done");
		return value;
	}
//method to enter a text from excel sheet
	public WebElement entertext(WebDriver driver, String locatorValue, String sheetname, String columnname, int text) {
		log.info("Entering text into text box");
		WebElement value = findelement(driver, locatorValue);
		value.sendKeys(datareader.getCellDataByColumnName(sheetname, columnname, text));
		log.info(text + " is entered");
		return value;
	}
//method to pass any keys in script
	public WebElement clear(WebDriver driver, String locator) {
		log.info("Entering text into text box");
		WebElement value = findelement(driver, locator);
		value.clear();
		return value;
	}
	public WebElement sendkey(WebDriver driver, String locator, Keys keys ) {
		log.info("Entering text into text box");
		WebElement value = findelement(driver, locator);
		value.sendKeys(keys);
		log.info(keys + " is entered");
		return value;
	}

//method to pass simple text in a script
	public WebElement sendkeys(WebDriver driver, String locator, String text) {
		log.info("Entering text into text box");
		WebElement value = findelement(driver, locator);
		value.sendKeys(text);
		log.info(text + " is entered");
		return value;
	}
//method to select a dropdown
	public void dropdown(WebDriver driver, String locatorValue, String text) {
		log.info("Dropdown method starts to perform");
		Select value = new Select(findelement(driver, locatorValue));
		value.selectByVisibleText(text);
		log.info("Dropdown method done");
	}
//method to scroll through a page
	public void scroll(WebDriver driver) {
		log.info("Scrolls performed");
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("window.scrollTo(0,650)");
		log.info("Scroll done");
	}
//method to scroll to view a particular element
	public String scrolltoview(WebDriver driver, String locatorValue) {
		log.info("Scroll to view starts to perform");
		WebElement element = findelement(driver, locatorValue);
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].scrollIntoView();", element);
		javascript.executeScript("window.scrollTo(0,-500)");
		log.info("Scroll to view done");
		return locatorValue;
	}
//method to get text from a particular locator
	public String getActual(WebDriver driver, String locatorValue) {
		log.info(" Getting text from data");
		String getstring = findelement(driver, locatorValue).getText();
		return getstring;
	}
//method to check whether is displaying or not
	public boolean isDisplayed(WebDriver driver, String locator) {
		boolean isdisplayed = false;
		log.info("Validating the element is displaying or not");
		isdisplayed = findelement(driver, locator).isDisplayed();
		if (isdisplayed = true) {
			log.info("Element is present");
			return isdisplayed;
		} else {
			log.info("Element isn't present");
			return false;
		}
	}
//method to get text of particular index
	public String expectedresult(WebDriver driver, String locator, int beginindex, int endindex) {
		log.info("Text split action performs");
		String name = findelement(driver, locator).getText().substring(beginindex, endindex);
		return name;
	}
//method to handle dynamic get text
	public String replaceforgettext(WebDriver driver, String locator, String newChar) {
		log.info("Getting Text present in an element");
		String newLocator = locator.replace("", newChar);
		String value = findelement(driver, newLocator).getText();
		log.info("The text is " + value);
		return value;
	}
	public WebElement replace(WebDriver driver, String locator, int mousehover) {
		String mousemove=mousehover+"";
		String newLocator = locator.replace("*", mousemove);
		WebElement value = driver.findElement(locator(newLocator));
		return value;
	}
//mouse hover method
	public boolean mouseHoverTotab(WebDriver driver, String locatorValue) {
		// logg.info("Performing movehover");
		WebElement webElement = findelement(driver, locatorValue);
		Actions action = new Actions(driver);
		action.moveToElement(webElement);
		// logg.info("mouse movehovered");
		return true;
	}
//method to convert list into webelement
	public List<String> convertListFromWebElement(List<WebElement> list) {
		// logg.info("Converting list performs");
		List<String> textList = new ArrayList<String>();
		if (list.size() > 0) {
			for (WebElement element : list) {
				textList.add(element.getText());
			}
		}
		log.info("List converted");
		return textList;
	}
}
