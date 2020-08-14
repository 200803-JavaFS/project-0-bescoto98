package com.revature.services;

import com.revature.dao.*;
import com.revature.models.*;


public class BasicServices {
	
	public UserDAO uDAO = new UserDAO();
	public AccountDAO aDAO = new AccountDAO();
	
	public void showAccount(int id) {
		System.out.println(aDAO.findByAcctID(id));
	}
	
	public void showUser(int id) {
		System.out.println(uDAO.findUser(id));
	}
	
	public void showUserInfo(int id) {
		System.out.println(uDAO.findUserInfo(id));
	}
	
	public void withdraw(Account a) {
		aDAO.updateAccount(a);
	}
	
	public void deposit(Account a) {
		aDAO.updateAccount(a);
	}
	
	public void transfer(Account a, Account b, int amnt) {
		
	}

}
