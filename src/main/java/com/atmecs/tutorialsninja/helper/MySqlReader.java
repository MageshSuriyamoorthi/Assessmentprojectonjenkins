package com.atmecs.tutorialsninja.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlReader {

	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;
	String expectedvalue;

	public String db(String databaseName, String tableName, String columnname, int rowno) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/" + databaseName + "?user=root&password=1996");

			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			rs = stmt.executeQuery("select * from " + tableName);
			while (rs.next()) {
				String getAllIndex = rs.getString(1);
				int row = Integer.parseInt(getAllIndex); // IF we inplied in "TC-001" means continue with getting them
															// value
				if (row == rowno) { // row and row value are part of integer value so == method is used for string
									// .equals() method is used for adderess comarision
					expectedvalue = rs.getString(columnname);
				}

			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expectedvalue;
	}

	public static void main(String[] args) {
		MySqlReader DB = new MySqlReader();
		String s = DB.db("sys", "student", "studentLastName", 1);
		System.out.println(s);
	}

}
