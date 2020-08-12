package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.*;


public class AccountDAO {
	
	Connection con = null;
	PreparedStatement st = null;
	
	public Account findAccount(String u, String p) {
		
		Account temp = new Account(u,p);
		
		try {
			
			String sql = "select * from logins left join accounts on logins.idnum = accounts.idnum left join users on logins.idnum = users.idnum where username=? AND password=?"; 
			
			
			st = con.prepareStatement(sql);
			
			st.setString(1, u);
			st.setString(2, p);
			
			ResultSet result = st.executeQuery();
			
			temp.setFN(result.getString("FN"));
			temp.setLN(result.getString("LN"));
			temp.setAccountNum(result.getInt("acctid"));
			temp.setBalance(result.getInt("balance"));
			temp.setIdNumber(result.getInt("idnum"));
			temp.setAcctType(result.getString("type"));
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			//closeResources
			System.out.println("close resources");
		}
		
		return temp;
	}
	
	
	public Boolean deposit(Account a, double amnt) {
		
		try {
				amnt = amnt + a.getBalance();
				String sql = "update accounts set balance=? where idnum=?";
				
				st = con.prepareStatement(sql);
				
				st.setDouble(1,amnt);
				st.setInt(2,a.getIdNumber());
				
				st.executeQuery();
				
				if(st.executeUpdate() != 0) {
					return true;
				}
				else {
					return false;
				}
					
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
//			closeResources();
			System.out.println("close resources");
		}
		
		return false;
	}
	
	public Boolean withdraw(Account a, double amnt) {
		
		if(amnt > a.getBalance()) {
			return false;
		}
		
		try {
			amnt = amnt - a.getBalance();
			String sql = "update accounts set balance=? where idnum=?";
			
			st = con.prepareStatement(sql);
			
			st.setDouble(1,amnt);
			st.setInt(2,a.getIdNumber());
			
			st.executeQuery();
			
			if(st.executeUpdate() != 0) {
				return true;
			}
			else {
				return false;
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
	//		closeResources();
			System.out.println("close resources");
		}
		
		return true;
		
	}

	public Boolean transfer(Account from, Account to, double amnt) {
		
		// check that from has enough
		if(amnt > from.getBalance()) {
			return false;
		}
	
		// update values
		
		try {
				// remove amount from & update 
				String sql = "update accounts set balance=? where idnum=?";
				
				st = con.prepareStatement(sql);
				
				st.setDouble(1,amnt);
				st.setInt(2,from.getIdNumber());
				
				// execute update
			
				// add amnt to & update
				sql = "update account set balance=? where idnum=?";
				
				st = con.prepareStatement(sql);
				
				st.setDouble(1,amnt);
				st.setInt(2,to.getIdNumber());
			
				// transaction log ig
			
				
				
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
	//		closeResources();
			System.out.println("close resources");
		}
		
		return true;
	}
	
	
	
	public List<Account> getAllAccounts(){ // do check beforehand
		
		List<Account> accts = new ArrayList<>();
		
		// get connection
		
		try {
			
			String sql = "select * from accounts";
			st = con.prepareStatement(sql);
			
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				Account temp = new Account();
				temp.setFN(result.getString("FN"));
				temp.setLN(result.getString("LN"));
				temp.setUsername(result.getString("username"));
				temp.setPassword(result.getString("password"));
				temp.setAccountNum(result.getInt("acctid"));
				temp.setBalance(result.getDouble("balance"));
				temp.setIdNumber(result.getInt("idnum"));
				
				
				accts.add(temp);
			}
			
			result.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
//			closeResources();
			System.out.println("close resources");
		}

		
		return accts;
	}
}
