package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IAccountDAO {
	
	public List<Account> findAll();
	public Account findByAcctID(int a);
	public List<Account> findUserAccounts(int uID);
	public List<Account> findPendingAccounts();
	public List<Customer> findEApprovedAccts(String fn, String ln);
	public boolean addAccount(Account a, int uID);
	public boolean transferMoney(Account a, Account b, double amnt);
	public boolean updateAccount(Account a);
	public boolean addJointAccount(int accID, int userID);
	

}
