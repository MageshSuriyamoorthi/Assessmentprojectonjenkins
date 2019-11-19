package com.atmecs.tutorialsninja.scriptbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.atmecs.tutorialsninja.helper.MySqlReader;
import com.atmecs.tutorialsninja.helper.PropertiesFileReader;
import com.atmecs.tutorialsninja.reports.Log4j;
import com.atmecs.tutorialsninja.utils.AssertionAndValidationManager;
import com.atmecs.tutorialsninja.utils.UtilityFiles;
import com.atmecs.tutorialsninja.utils.WaitsandSimpleAlert;

public class HeatClicnicbase {
	private Log4j log =new Log4j();
	private PropertiesFileReader propertyreader = new PropertiesFileReader();
	public WaitsandSimpleAlert wait = new WaitsandSimpleAlert();
	public UtilityFiles utils = new UtilityFiles();
	private AssertionAndValidationManager assertandvalidate = new AssertionAndValidationManager();
	private MySqlReader DBread=new MySqlReader();

	String actual, expected;

	public void movetoelement(WebDriver driver, String locator) {
		log.info("Tabs need to check and clicked to verify it travers ");
		Actions action = new Actions(driver);

		action.moveToElement(utils.findelement(driver, locator)).build().perform();
		log.info("Tabs were clicking and traversing correctly");

	}

	public void buyingProduct(WebDriver driver) {
		log.info("Traversing to merchandise tab and selecting product");
		actual = utils.expectedresult(driver, propertyreader.getLocatorValue("loc.viewing.mens"), 0, 12);
		//expected = assertandvalidate.getdataval("HeatClinic", "Validation", 1);
		expected =DBread.db("assignment", "heatclinic", "heatClinicValidation", 4);

		assertandvalidate.assertequals(actual, expected);

		utils.click(driver, propertyreader.getLocatorValue("loc.buynow.habanero"));

		utils.click(driver, propertyreader.getLocatorValue("loc.select.redcolor"));

		utils.click(driver, propertyreader.getLocatorValue("loc.select.malesize"));

		String name = assertandvalidate.getdataval("HeatClinic", "Validation", 2);

		utils.sendkeys(driver, propertyreader.getLocatorValue("loc.select.personalizedname"), name);

		utils.click(driver, propertyreader.getLocatorValue("loc.select.buynow"));
		
		log.info("Product has been ordered ");
	}

	public void checkOut(WebDriver driver) {
		log.info("");
		utils.click(driver, propertyreader.getLocatorValue("loc.select.cartlink"));

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.pname"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 1);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.psize"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 2);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.ppersonname"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 3);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.pcolor"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 4);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.price"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 5);

		assertandvalidate.assertequals(actual, expected);

		actual = utils.getActual(driver, propertyreader.getLocatorValue("loc.cartlink.totalprice"));
		expected = assertandvalidate.getdataval("HeatClinic", "CartValidation", 6);

		assertandvalidate.assertequals(actual, expected);

	}

}
