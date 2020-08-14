package com.revature.dao;

import java.util.List;

import java.util.ArrayList;
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
	public User findUser(int id) {
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
	public User findUser(String username) {
		return findUser(findUserID(username));
	}
	
	@Override
	public int findUserID(String username) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select u_id from users where username = ?;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,username);
			
			ResultSet result = st.executeQuery(sql);
			
			if(result.next()) {
				
				return result.getInt("u_id");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
	}

	@Override
	public boolean addUser(User u, Information i) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "begin; " + 
					" insert into users(first_name,last_name,username,pass,u_type) values (?,?,?,?,?);" + 
					" insert into information(u_id,ssn,address,city,state,zip,phone,email)" +
					" values ((select max(u_id) from users),?,?,?,?,?,?,?);" +
					" commit;";
			PreparedStatement st = conn.prepareStatement(sql);
			
			int indx = 0;
			
			st.setString(++indx,u.getFirstName());
			st.setString(++indx,u.getLastName());
			st.setString(++indx,u.getUsername());
			st.setString(++indx,u.getPassword());
			st.setInt(++indx,u.getType());
			
			st.setString(++indx,i.getSsn());
			st.setString(++indx,i.getAddress());
			st.setString(++indx,i.getCity());
			st.setString(++indx,i.getState());
			st.setString(++indx,i.getZip());
			st.setString(++indx,i.getPhone());
			st.setString(++indx,i.getEmail());
			
			
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
	public Information findUserInfo(int id) {
		
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

	@Override
	public boolean updateUserInfo(Information i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uniqueUsername(String u) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select count(*) from users where username = " + u + ";";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet result = st.executeQuery(sql);
			
			if(result.next()) {
				
				if(result.getInt("count") > 0) {
					return false;
				}
				else {
					return true;
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}


}