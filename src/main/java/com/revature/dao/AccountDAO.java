package com.revature.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.*;
import com.revature.utilities.ConnectionUtility;

public class AccountDAO implements IAccountDAO {

	@Override
	public List<Account> findAll() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			List<Account> allAccounts = new ArrayList<>();
			
			String sql = "select * from accounts;";
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				
				Account temp = new Account();
				
				temp.setAccountID(result.getInt("a_id"));
				temp.setBalance(result.getDouble("balance"));
				temp.setType(result.getString("a_type"));
				temp.setStatus(result.getString("status"));
				temp.setApprovedBy(result.getString("approved_by"));
				temp.setCreatedOn(result.getString("created_on"));
				
				allAccounts.add(temp);
				
			}
			
			return allAccounts;
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public Account findByAcctID(int a) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
		
			String sql = "select * from accounts where a_id=" + a + ";";
		
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			if(result.next()) {
				Account temp = new Account();
				
				temp.setAccountID(result.getInt("a_id"));
				temp.setBalance(result.getDouble("balance"));
				temp.setType(result.getString("a_type"));
				temp.setStatus(result.getString("status"));
				temp.setApprovedBy(result.getString("approved_by"));
				temp.setCreatedOn(result.getString("created_on"));
				
				return temp;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Account> findUserAccounts(int uID) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "select a_id,balance,a_type,status,approved_by,created_on from accounts " + 
					"left join acctxref on accounts.a_id = acctxref.account_id where acctxref.user_id=" + uID +";";
			List<Account> userAccounts = new ArrayList<>();
			
			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				
				Account temp = new Account();
				
				temp.setAccountID(result.getInt("a_id"));
				temp.setBalance(result.getDouble("balance"));
				temp.setType(result.getString("a_type"));
				temp.setStatus(result.getString("status"));
				temp.setApprovedBy(result.getString("approved_by"));
				temp.setCreatedOn(result.getString("created_on"));
				
				userAccounts.add(temp);
				
			}
			
			return userAccounts;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public boolean addAccount(Account a, int userID) {
	
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "begin;"+
			" insert into accounts(balance,a_type,status,approved_by,created_on) values (?,?,?,?,?);"+
			" insert into acctxref(user_id, account_id) values (?,(select max(a_id) from accounts));"+
			" commit;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setDouble(1,a.getBalance());
			st.setString(2,a.getType());
			st.setString(3,a.getStatus());
			st.setString(4,a.getApprovedBy());
			st.setString(5,a.getCreatedOn());
			st.setInt(6,userID);
			
			st.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public boolean updateAccount(Account a) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "update accounts set balance= ?, a_type= ?, status= ?, approved_by= ?, created_on= ? where a_id=?;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setDouble(1,a.getBalance());
			st.setString(2,a.getType());
			st.setString(3,a.getStatus());
			st.setString(4,a.getApprovedBy());
			st.setString(5,a.getCreatedOn());
			st.setInt(6,a.getAccountID());
			
			st.execute();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public boolean addJointAccount(int accID, int userID) {
		
		try(Connection conn = ConnectionUtility.getConnection()){
		
			String sql = "insert into acctxref(account_id,user_id) values (?,?);";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1,accID);
			st.setInt(2,userID);
			
			st.execute();
			
			return true;
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public boolean transferMoney(Account from, Account to, double amnt) {

		//should come in already verified that amounts are good
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			String sql = "begin;" +
					" update accounts set balance= ? where a_id=?;" +
					" update accounts set balance= ? where a_id=?;" +
					" commit;";
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setDouble(1,from.getBalance() - amnt);
			st.setInt(2,from.getAccountID());
			
			st.setDouble(3,to.getBalance() + amnt);
			st.setInt(4,to.getAccountID());
			
			st.execute();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return false;
	}

	@Override
	public List<Account> findPendingAccounts() {
		
		try(Connection conn = ConnectionUtility.getConnection()){
			
			List<Account> pendingAccts = new ArrayList<>();
			String sql = "select * from accounts where status = 'Pending';";

			Statement st = conn.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				
				Account temp = new Account();
				
				temp.setAccountID(result.getInt("a_id"));
				temp.setBalance(result.getDouble("balance"));
				temp.setType(result.getString("a_type"));
				temp.setStatus(result.getString("status"));
				temp.setApprovedBy(result.getString("approved_by"));
				temp.setCreatedOn(result.getString("created_on"));
				
				pendingAccts.add(temp);
				
			}
			
			return pendingAccts;
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public List<Customer> findEApprovedAccts(String fn, String ln) {
		try(Connection conn = ConnectionUtility.getConnection()){
			
			List<Customer> employeeCustomers = new ArrayList<>();
			
			String sql = "select u_id,first_name,last_name,username,a_id,balance,a_type,status,created_on "
					+ "from acctxref left join users on users.u_id = acctxref.user_id "
					+ "left join accounts on accounts.a_id = acctxref.account_id "
					+ "where approved_by =?;";

			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,fn.concat(" " + ln));
			
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				
				Customer client = new Customer();
				
				User person = new User();
				
				Account temp = new Account();
				
				person.setUserID(result.getInt("u_id"));
				person.setFirstName(result.getString("first_name"));
				person.setLastName(result.getString("last_name"));
				person.setUsername(result.getString("username"));
				
				
				temp.setAccountID(result.getInt("a_id"));
				temp.setBalance(result.getDouble("balance"));
				temp.setType(result.getString("a_type"));
				temp.setStatus(result.getString("status"));
				temp.setCreatedOn(result.getString("created_on"));
				
				List<Account> s = new ArrayList<>();
				s.add(temp);
				client.setAccounts(s);

				client.setPerson(person);

				employeeCustomers.add(client);
				
			}
			
			return employeeCustomers;
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}

}
