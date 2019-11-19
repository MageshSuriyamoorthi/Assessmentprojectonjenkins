package com.atmecs.tutorialsninja.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.atmecs.tutorialsninja.testbase.Classpaths;
/*Configure the log4j file path and to display output messages.
 * 
 * 
 * @author   Magesh
 */
public class Log4j {

	Logger logger = null;

	public Log4j() {
		getlogger();
		logger = Logger.getLogger(Log4j.class.getName());
	}

	public void getlogger() {
		PropertyConfigurator.configure(Classpaths.log4j_file);
	}

	public String info(String message) {
		logger.info(message);
		return message;
	}
}
