package com.atmecs.tutorialsninja.reports;

import java.io.IOException;

import com.atmecs.tutorialsninja.testbase.Classpaths;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/*
 * This method will configure and it will get the overall report.
 * 
 * 
 * @author   Magesh
 */
public class ExtentReport {

	static ExtentHtmlReporter reporter = new ExtentHtmlReporter(Classpaths.extendreport + "project.html");
	static ExtentReports extent = new ExtentReports();

	public void reportLog(String testname, String Failuremsg) {
		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest(testname);
		logger.log(Status.INFO, testname);
		logger.log(Status.PASS, testname);
		try {
			logger.pass("passed script", MediaEntityBuilder.createScreenCaptureFromPath("./Snapshot/pass").build());
		} catch (IOException exception) {
			logger.info(exception + " arised while taking snapshot.");
		}
		logger.log(Status.FAIL, Failuremsg);
		try {
			logger.fail("failed script", MediaEntityBuilder.createScreenCaptureFromPath("./Snapshot").build());
		} catch (IOException exception) {
			logger.info(exception + " arised while taking snapshot.");
		}
		extent.flush();
	}
}
