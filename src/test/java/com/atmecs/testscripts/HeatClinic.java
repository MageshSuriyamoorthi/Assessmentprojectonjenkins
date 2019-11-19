package com.atmecs.testscripts;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.tutorialsninja.helper.MySqlReader;
import com.atmecs.tutorialsninja.reports.Log4j;
import com.atmecs.tutorialsninja.scriptbase.HeatClicnicbase;
import com.atmecs.tutorialsninja.testbase.TestSuiteBase;
import com.atmecs.tutorialsninja.utils.AssertionAndValidationManager;

public class HeatClinic extends TestSuiteBase {
	private Log4j log = new Log4j();
	private AssertionAndValidationManager assertandvalidate = new AssertionAndValidationManager();
	private HeatClicnicbase base = new HeatClicnicbase();
	private MySqlReader DBread = new MySqlReader();
	List<WebElement> list;
	String value, actual, expected;

	/*
	 * It's the method which is used to open the url and maximize the browser.
	 * 
	 */
	@BeforeClass
	public void openUrl() {

		utils.geturl(driver, propertyreader.getLocatorValue("config.url_two"));
		utils.maximize(driver);
		log.info("STEP 1: The Url has opened in the driver and it has been maximized");
	}

	/*
	 * Its the method used to done all scenario actions in the hot sauce website
	 * 
	 * 
	 */
	@Test
	public void homePage() {
		Actions action = new Actions(driver);

		list = utils.findelements(driver, propertyreader.getLocatorValue("loc.menu.alltabs"));
		for (int mouseover = 1; mouseover <= list.size(); mouseover++) {
			WebElement element;
			action.moveToElement(
					element = utils.replace(driver, propertyreader.getLocatorValue("loc.menu.tabs"), mouseover)).build()
					.perform();
			element.click();
			log.info("STEP 2:Listed tabs were verified by clicking and navigating them in order");
		}
		action.moveToElement(utils.findelement(driver, propertyreader.getLocatorValue("loc.merchandise.tab"))).build()
				.perform();
		WebElement element = utils.findelement(driver, propertyreader.getLocatorValue("loc.merchandise.mens"));

		element.click();
		
		log.info("STEP 3:Viewed on Merchandise tab and clicked on mens");

		base.buyingProduct(driver);
		
		log.info("STEP 4:Product Shirt has been ordered and need to give exact size color of the shirt");

		base.checkOut(driver);

		utils.click(driver, propertyreader.getLocatorValue("loc.cartlink.add"));

		utils.sendkey(driver, propertyreader.getLocatorValue("loc.cartlink.update"), Keys.DELETE);

		// value = assertandvalidate.getdataval("Heatclinic", "CartValidation", 8);
		value = DBread.db("assignment", "heatclinic", "Cartvalidation", 5);
		
		utils.sendkeys(driver, propertyreader.getLocatorValue("loc.cartlink.add"), value);

		wait.ThreadWait(2000);

		utils.click(driver, propertyreader.getLocatorValue("loc.cartlink.update"));

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.price"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 5);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.totalprice"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 7);

		assertandvalidate.assertequals(actual, expected);

	}
}
