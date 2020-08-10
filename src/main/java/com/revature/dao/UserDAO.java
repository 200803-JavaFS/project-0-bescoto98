package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.*;

public class UserDAO {
	
	// connection variables
	Connection con = null;
	PreparedStatement st = null;
	
	public User findUser(String u, String p) {
	
		User temp = new User(u,p);
		
		try {
//			getConnection
			String sql = "select idnum from logins where username=? AND password=?";
			st = con.prepareStatement(sql);
			
			st.setString(1, u);
			st.setString(2, p);
	
			// return idnum from username & password
			ResultSet result = st.executeQuery();
		
			temp.setIdNumber(result.getInt("idnum"));
			
			sql = "select * from users where idnum=?";
			
			st = con.prepareStatement(sql);
			st.setInt(1, temp.getIdNumber());
			
			result = st.executeQuery();
			
			temp.setFN(result.getString("FN"));
			temp.setLN(result.getString("LN"));
			temp.setType(result.getInt("type"));
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
//			closeResources();
			System.out.println("close resources");
		}
		
		return temp;
	}
	
	

}
