package com.atmecs.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.atmecs.tutorialsninja.reports.Log4j;
import com.atmecs.tutorialsninja.scriptbase.TutorialsNinjaHomePageBase;
import com.atmecs.tutorialsninja.testbase.TestSuiteBase;
import com.atmecs.tutorialsninja.utils.AssertionAndValidationManager;

public class TutorialsNinjaHomePage extends TestSuiteBase {
	private Log4j log = new Log4j();
	private TutorialsNinjaHomePageBase base = new TutorialsNinjaHomePageBase();
	private AssertionAndValidationManager assertandvalidate = new AssertionAndValidationManager();
	String getactual_title, getexpected_title;

	@BeforeClass
	public void openUrl() {

		//utils.geturl(driver, propertyreader.getLocatorValue("config.url_one"));
		String BaseURL = System.getProperty("customproperty");
		 driver.get(BaseURL);
		utils.maximize(driver);
	}

	@BeforeMethod
	public void validateHomePage() {

		getactual_title = utils.gettitle(driver);

		getexpected_title = assertandvalidate.getdataval("HomePageData", "HomepageTitle", 1);

		assertandvalidate.assertequals(getactual_title, getexpected_title);
	}

	@Test
	public void searchProduct() {

		base.searchProduct(driver, assertandvalidate.getdataval("HomePageData", "SearchList", 1));

		utils.click(driver, propertyreader.getLocatorValue("loc.image.click"));

		base.addCart(driver, assertandvalidate.getdataval("HomePageData", "Quantity", 1));

		base.productValidation(driver, "IphoneValidation");

		utils.click(driver, propertyreader.getLocatorValue("loc.addtocart.button"));

		utils.clear(driver, propertyreader.getLocatorValue("loc.search.textbox"));

		base.searchProduct(driver, assertandvalidate.getdataval("HomePageData", "SearchList", 2));

		utils.click(driver, propertyreader.getLocatorValue("loc.image.click"));

		base.addCart(driver, assertandvalidate.getdataval("HomePageData", "Quantity", 2));

		base.productValidation(driver, "MacBookValidation");

		utils.click(driver, propertyreader.getLocatorValue("loc.addtocart.button"));

		utils.click(driver, propertyreader.getLocatorValue("loc.home.button"));

		utils.click(driver, propertyreader.getLocatorValue("loc.cart.button"));

		base.costValidation(driver, "MacBookValidation");

	}
}
