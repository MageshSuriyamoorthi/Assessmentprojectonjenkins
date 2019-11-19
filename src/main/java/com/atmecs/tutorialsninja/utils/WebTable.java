package com.atmecs.tutorialsninja.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.tutorialsninja.reports.Log4j;

/*
 * This method used to handle various dynamic web table 
 * 
 *  @author  Magesh
*/
public class WebTable {
	List<WebElement> rowcount;
	List<WebElement> columncount;
	String rowlist, collist;

	private Log4j log =new Log4j();

//Locator identification for find element and find elements method
	public By locator(String locator) {
		By by = null;
		String[] locators = locator.split(":", 2);

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

//find element method
	public List<WebElement> findelements(WebDriver driver, String locator) { // method to find elements for a locator
		List<WebElement> element = driver.findElements(locator(locator));
		return element;
	}

//find elements method
	public WebElement findelement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(locator(locator));
		return element;
	}

//getting total row count by giving locator
	public List<WebElement> rowcount(WebDriver driver, String locator) {
		rowcount = findelements(driver, locator);
		return rowcount;
	}

//getting total column count by giving locator
	public List<WebElement> columncount(WebDriver driver, String locator) {
		columncount = findelements(driver, locator);
		return columncount;
	}

//getting data by passing locator which containing row and column values
	public String[][] getdata_fromtable(WebDriver driver, String locator) {
		// String[] newlist = new String[rowcount.size()*columncount.size()];
		String[][] newlist = new String[rowcount.size()][columncount.size()];
		// int index = 0;
		for (int row = 1; row <= rowcount.size(); row++) {
			for (int column = 1; column <= columncount.size(); column++) {
				rowlist = locator.replace("rownumber", row + "");
				collist = rowlist.replace("colnumber", column + "");

				String newcalender = findelement(driver, collist).getText();
				System.out.println(newcalender);
				// newlist[index++] = newcalender;
				newlist[row - 1][column - 1] = newcalender;
			}
		}
		return newlist;
	}

	public String new_exactval(String[][] dateArr, int row, int col) { // method to return value from given row col by
																		// the user
		String date = dateArr[row - 1][col - 1];
		return date;
	}

//getting row and column values by passing exact row and column value with locator
	public String get_exactdata(WebDriver driver, String locator, int row, int col) { // method to return calendar value
																						// from given row col by the
																						// user in locator
		rowlist = locator.replace("rownumber", row + "");
		collist = rowlist.replace("colnumber", col + "");

		String newcalender = findelement(driver, collist).getText();

		return newcalender;
	}
}
