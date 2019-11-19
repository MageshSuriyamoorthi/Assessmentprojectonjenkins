package com.atmecs.tutorialsninja.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.atmecs.tutorialsninja.reports.Log4j;
/*
 * Method to read data files from database and prints with matching column
 * 
 * 
 * @author   Magesh.S
 */

public class DatabaseReader {
	private Log4j log = new Log4j();
	ResultSet res = null;
	Statement stmt = null;
	Connection con = null;
	String expectedvalue;
	public String db(String databaseName, String tableName, String columnname, int rowno) {

		try {
			DriverManager.registerDriver(new com.atmecs.tutorialsninja.helper.DatabaseDriver());
			//log.info("Driver loaded");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://ATMECSINLT-084\\SQL2012:1433;database=" + databaseName + ";integratedSecurity=true;";
			con = DriverManager.getConnection(connectionUrl);
			//log.info("connection established");
		} catch (Exception exception) {
			log.info("connection with database has interrupted with exception " + exception);
		}
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from " + tableName);
		} catch (SQLException exception) {
			log.info("exception arised while creating " + exception);
		}
		try {
			while (res.next()) {
				String getAllIndex = res.getString(1);
				int row = Integer.parseInt(getAllIndex); 	    // IF we inplied in "TC-001" means continue with getting them
															    // in string value and validate with our given testdata
															    // value
				if (row == rowno) { 						    // row and row value are part of integer value so == method is used for string
															    // .equals() method is used for adderess comarision
					expectedvalue = res.getString(columnname);

				}
			}
		} catch (SQLException exception) {
			log.info(exception + " arised while getting the value from the database.");
		}

		try {
			con.close();
			res.close();
		} catch (SQLException exception) {
			log.info(exception + " arised while closing the database.");
		}
		return expectedvalue;
	}
	
	/*
	 * public static void main(String[] args) { DatabaseReader db=new
	 * DatabaseReader(); String svalue=db.db("master","student1", "dept", 3);
	 * System.out.println(svalue); }
	 */ 
}
