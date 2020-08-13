package com.revature.dao;

import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Information;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtility;

public class UserDAO implements IUserDAO {
	
	

	@Override
	public List<User> findAll() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			List<User> allUsers = new ArrayList<>();
			
			String sql = "select * from users;";
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				User temp = new User();
				
				temp.setFirstName(result.getString("first_name"));
				temp.setLastName(result.getString("last_name"));
				temp.setUsername(result.getString("username"));
				temp.setPassword(result.getString("pass"));
				temp.setUserID(result.getInt("u_id"));
				temp.setType(result.getInt("u_type"));
				
				allUsers.add(temp);
			}
			
			return allUsers;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
		
	}

	@Override
	public User findByUserID(int id) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select * from users where u_id = " + id + ";";
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			if(result.next()) {
				User temp = new User();
				
				temp.setFirstName(result.getString("first_name"));
				temp.setLastName(result.getString("last_name"));
				temp.setUsername(result.getString("username"));
				temp.setPassword(result.getString("pass"));
				temp.setUserID(result.getInt("u_id"));
				temp.setType(result.getInt("u_type"));
				
				return temp;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public boolean addUser(User u) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "insert into users(first_name,last_name,username,pass,u_type) values (?,?,?,?,?);";
			
			PreparedStatement st = conn.prepareCall(sql);
			
			st.setString(1,u.getFirstName());
			st.setString(2,u.getLastName());
			st.setString(3,u.getUsername());
			st.setString(4,u.getPassword());
			st.setInt(5,u.getType());
			
			st.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			String sql = "update users set first_name = ?,last_name = ?,"
					+ "u_type = ?,username = ?,pass = ? where u_id = ?;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,u.getFirstName());
			st.setString(2,u.getLastName());
			st.setInt(3,u.getType());
			st.setString(4,u.getUsername());
			st.setString(5,u.getPassword());
			st.setInt(6,u.getUserID());
			
			st.execute();
			
			return true;
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public Information findInformationByID(int id) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select * from information where u_id=" + id + ";";
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			if(result.next()) {
				Information temp = new Information();
				
				temp.setUserID(result.getInt("u_id"));
				temp.setSsn(result.getString("ssn"));
				temp.setAddress(result.getString("address"));
				temp.setCity(result.getString("city"));
				temp.setState(result.getString("state"));
				temp.setZip(result.getString("zip"));
				temp.setPhone(result.getString("phone"));
				temp.setEmail(result.getString("email"));
				
				return temp;

			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
		
	}

	@Override
	public List<User> findSubgroup(int type) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select * from users where u_type="+type+";";
			
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sql);
			
			List<User> subgroup = new ArrayList<>();
			
			while(result.next()) {
				User temp = new User();
				temp.setFirstName(result.getString("first_name"));
				temp.setLastName(result.getString("last_name"));
				temp.setUsername(result.getString("username"));
				temp.setPassword(result.getString("pass"));
				temp.setUserID(result.getInt("u_id"));
				temp.setType(result.getInt("u_type"));
				subgroup.add(temp);
				
			}
			
			return subgroup;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}
}