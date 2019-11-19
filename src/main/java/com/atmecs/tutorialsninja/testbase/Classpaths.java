package com.atmecs.tutorialsninja.testbase;

import java.io.File;

/*Configure the file-paths for data-files and .exe to be loaded 
 * 
 * 
 * @author   Magesh
 */
public class Classpaths {

	public static String Users_Home = System.getProperty("user.dir");

	public static String url_file = Users_Home + File.separator + "config.properties";

	public static String resource_file = Users_Home + File.separator + "src" + File.separator + "test" + File.separator
			+ "resources";

	public static String log4j_file = resource_file + File.separator + "log4j" + File.separator + "log4j.properties";

	public static String Chrome_file = Users_Home + File.separator + "lib" + File.separator + "chromedriver.exe";

	public static String Firefox_file = Users_Home + File.separator + "lib" + File.separator + "geckodriver.exe";

	public static String IE_file = Users_Home + File.separator + "lib" + File.separator + "IEDriverServer.exe";

	public static String extendreport = Users_Home + File.separator + "extendedreport" + File.separator;

	public static String Excel_file = resource_file + File.separator + "testdata" + File.separator + "testdata.xlsx";

	public static String LocHome_file = resource_file + File.separator + "locator" + File.separator
			+ "tutorialninja.properties";

	public static String LocHotSauce_file = resource_file + File.separator + "locator" + File.separator
			+ "hotsauce.properties";
	/*
	 * public static void main(String[] args) { System.out.println(log4j_file); }
	 */
}
