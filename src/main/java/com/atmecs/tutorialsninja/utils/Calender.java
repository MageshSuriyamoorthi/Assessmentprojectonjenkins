package com.atmecs.tutorialsninja.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.atmecs.tutorialsninja.reports.Log4j;
/*
 * This method used to calculating the time difference from present date 
 * and used to validate with test data.
 * 
 *  @author  Magesh
*/
public class Calender {
	Date date = new Date();
	private Log4j log = new Log4j();
	Date currentdate;
	Date altereddate;
	int getjDate = 0;
	String newMonth = null;
	String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public String daysDifferenceWithActual(String blogerDate) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
		String differenceinDays = null;
		try {
			String actualDate = simpleDate.format(date);
			String gettingDate = monthConversiontoNumeric(blogerDate);
			System.out.println(gettingDate);
			SimpleDateFormat simpledateOne = new SimpleDateFormat("dd-MM-yyyy");

			currentdate = simpledateOne.parse(actualDate);
			altereddate = simpledateOne.parse(gettingDate);
			System.out.println(altereddate);

			long totaldifference = Math.abs(currentdate.getTime() - altereddate.getTime());
			long differenceDates = totaldifference / (24 * 60 * 60 * 1000);
			differenceinDays = Long.toString(differenceDates);
		} catch (Exception exception) {
			log.info(exception + " Arised in finding date difference");
		}
		return differenceinDays;
	}

	public String monthConversiontoNumeric(String date) {
		String dateArray[] = date.split("-");
		for (int getiDate = 0; getiDate < monthArray.length; getiDate++) {
			if (dateArray[1].equalsIgnoreCase(monthArray[getiDate])) {
				getjDate = getiDate + 1;
				break;
			}
		}
		if (getjDate!=0 && getjDate < 10)
			newMonth = "0" + getjDate;
		else if (getjDate >= 10 && getjDate < 13)
			newMonth = "" + getjDate;
		else
			log.info("Month isn't matched");

		String blogDate = dateArray[0] + "-" + newMonth + "-" + dateArray[2];
		return blogDate;
	}

	public static void main(String[] args) {
		Calender cal = new Calender();
		String dateDifference = cal.daysDifferenceWithActual("01-Decembe-2018");
		System.out.println(dateDifference);
	}

}
