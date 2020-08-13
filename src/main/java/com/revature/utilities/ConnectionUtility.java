package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	
	public static Connection getConnection() throws SQLException {
	
		try{
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://javafs.cleuzgwokgxs.us-east-2.rds.amazonaws.com:5432/banking";
		String username = "postgres";
		String password = "rootroot";
		
		return DriverManager.getConnection(url, username, password);
	}
}
