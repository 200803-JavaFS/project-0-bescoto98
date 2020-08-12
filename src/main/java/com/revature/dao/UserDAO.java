package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.*;

public class UserDAO {
	
	// connection variables
	Connection con = null;
	PreparedStatement st = null;
	
	public User findUser(String u, String p) {
	
		User temp = new User(u,p);
		
		try {
			//get connection from utilities
			String sql = "select users.FN, users.LN, users.type, users.idnum from users left join logins on users.idnum = logins.idnum where username=? AND password =?";
			st = con.prepareStatement(sql);
			
			st.setString(1, u);
			st.setString(2, p);
			
			ResultSet result = st.executeQuery();
			
			temp.setFN(result.getString("FN"));
			temp.setLN(result.getString("LN"));
			temp.setType(result.getInt("type"));
			temp.setIdNumber(result.getInt("idnum"));
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
//			closeResources();
			System.out.println("close resources");
		}
		
		return temp;
	}
	
	
	public List<User> getAllCustomers(){
		List<User> customers = new ArrayList<>();
		
		try {
			
			String sql = "select * from users where type=?"; //whatever the type is for customers
			User temp = new User();
			
//			temp.setFN();
			// and so on
			
			customers.add(temp);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
//			closeResources();
			System.out.println("close resources");
		}
		
		return customers;
	}
	
	

}
