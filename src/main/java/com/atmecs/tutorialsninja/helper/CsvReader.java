package com.atmecs.tutorialsninja.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.atmecs.tutorialsninja.reports.Log4j;
/*
 * Method to read csv data files and prints with matching single column
 * 
 * 
 * @author Magesh
 */
public class CsvReader {
	private Log4j log =new Log4j();
	FileInputStream fileinputstream = null;
	String newtext;
	String colname, csvvalue = null;
	String[] csvarr;

	public String reader(String filepath, String columnname, int columnIndex, int rowIndex) {
		try {
			fileinputstream = new FileInputStream(filepath);
		} catch (FileNotFoundException fileexception) {
			log.info(fileexception + " arised during loading of the file");
		}
		Scanner scan = new Scanner(fileinputstream);
		newtext = scan.nextLine();
		csvarr = newtext.split(",");
		colname = csvarr[columnIndex].replace(" ", "");
		while (scan.hasNextLine()) {
			newtext = scan.nextLine();
			csvarr = newtext.split(",");
			if (csvarr[0].equals(rowIndex + "")) {
				if (colname.equalsIgnoreCase(columnname)) {
					csvvalue = csvarr[columnIndex].replace(" ", "");
					break;
				} else {
					log.info("give correct column name and column index value");
				}
			}
		}
		return csvvalue;
	}

	/*
	 * public static void main(String[] args) throws FileNotFoundException {
	 * CsvReader read = new CsvReader(); read.reader(
	 * "D:\\training\\eclipse-workspace\\Project\\src\\main\\resources\\com\\atmecs\\application\\datavalues\\datavalues.csv",
	 * "name", 1);
	 * 
	 * }
	 */
}