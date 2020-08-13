package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IAccountDAO {
	
	public List<Account> findAll();
	public Account findByAcctID(int a);
	public List<Account> findUserAccounts(User u);
	public boolean addAccount(Account a);
	public boolean closeAccount(Account a);
	public boolean updateAccount(Account a);
	public boolean addJointAccount(Account a, User newUser);
	

}
