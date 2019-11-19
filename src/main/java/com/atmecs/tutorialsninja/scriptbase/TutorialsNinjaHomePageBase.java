package com.atmecs.tutorialsninja.scriptbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.atmecs.tutorialsninja.helper.PropertiesFileReader;
import com.atmecs.tutorialsninja.reports.Log4j;
import com.atmecs.tutorialsninja.utils.AssertionAndValidationManager;
import com.atmecs.tutorialsninja.utils.UtilityFiles;
import com.atmecs.tutorialsninja.utils.WaitsandSimpleAlert;

public class TutorialsNinjaHomePageBase {
	String getactual_title, getexpected_title;
	String actual, expected;

	private Log4j log =new Log4j();
	private PropertiesFileReader propertyreader = new PropertiesFileReader();
	public WaitsandSimpleAlert wait = new WaitsandSimpleAlert();
	public UtilityFiles utils = new UtilityFiles();
	private AssertionAndValidationManager assertandvalidate = new AssertionAndValidationManager();

	public void searchProduct(WebDriver driver, String sendkeys) {
		utils.sendkeys(driver, propertyreader.getLocatorValue("loc.search.textbox"), sendkeys);

		utils.click(driver, propertyreader.getLocatorValue("loc.search.button"));

		utils.scroll(driver);

	}

	public void addCart(WebDriver driver, String sendkeys) {
		utils.click(driver, propertyreader.getLocatorValue("loc.addtocart.textbox"));
		utils.sendkey(driver, propertyreader.getLocatorValue("loc.addtocart.textbox"), Keys.BACK_SPACE);
		wait.ThreadWait(2000);
		utils.sendkeys(driver, propertyreader.getLocatorValue("loc.addtocart.textbox"), sendkeys);

	}

	public void productValidation(WebDriver driver, String columnname) {
		if (columnname.equals("MacBookValidation")) {
			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.availabilityformac"));
			expected = assertandvalidate.getdataval("HomePageData", columnname, 1);
			assertandvalidate.assertequals(actual, expected);

			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.descriptionformac"));
			expected = assertandvalidate.getdataval("HomePageData", columnname, 4);
			assertandvalidate.assertequals(actual, expected);

		} else {
			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.availability"));
			expected = assertandvalidate.getdataval("HomePageData", columnname, 1);
			assertandvalidate.assertequals(actual, expected);

			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.description"));
			expected = assertandvalidate.getdataval("HomePageData", columnname, 4);
			assertandvalidate.assertequals(actual, expected);

		}
		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.price"));
		expected = assertandvalidate.getdataval("HomePageData", columnname, 2);
		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product.extax"));
		expected = assertandvalidate.getdataval("HomePageData", columnname, 3);
		assertandvalidate.assertequals(actual, expected);
	}

	public void costValidation(WebDriver driver, String columnname) {

		if (columnname.equals("MacBookValidation")) {

			wait.ThreadWait(4000);

			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.overall.price"));
			expected = assertandvalidate.getdataval("HomePageData", "overallprice", 3);
			assertandvalidate.assertequals(actual, expected);

			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product2.cart"));
			expected = assertandvalidate.getdataval("HomePageData", "overallprice", 2);
			assertandvalidate.assertequals(actual, expected);

			utils.click(driver, propertyreader.getLocatorValue("loc.product2.click"));
			wait.ThreadWait(3000);
			utils.click(driver, propertyreader.getLocatorValue("loc.cart.button"));

			actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.product1.cart"));
			expected = assertandvalidate.getdataval("HomePageData", "overallprice", 4);
			assertandvalidate.assertequals(actual, expected);
		}
		else {
			log.info("No column called as macBook air is present");
			
		}
	}

}
