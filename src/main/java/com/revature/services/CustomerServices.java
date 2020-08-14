package com.revature.services;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.dao.*;
import com.revature.models.*;

public class CustomerServices{
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO uDAO = new UserDAO();
	private AccountDAO aDAO = new AccountDAO();
	
	private Customer client;
	
	public CustomerServices(int id) {
		System.out.println("Welcome returning customer!");
		login();
	}
	
	private void login() {
		System.out.println("Enter your username:\n");
		String username = inputs.nextLine();
		
		System.out.println("\nEnter your password:\n");
		String password = inputs.nextLine();
		
		User temp = uDAO.findUser(username);
		if(temp.getPassword().equals(password)) {
			client.setPerson(temp);
			client.setAccounts(aDAO.findUserAccounts(temp.getUserID()));
			showMenu();
		}
		else {
			
			System.out.println("Invalid login");
		}
	}

	private void showMenu() {
		
		System.out.println("+-------------------+\nMenu options:\n" +
				"(1) Show My Accounts \n" +
				"(2) Show My Information\n"+
				"(3) Withdraw from Account\n" +
				"(4) Deposit into Account\n" +
				"(5) Transfer money from accounts\n" +
				"(6) Apply for Joint Account" +
				"(7) Close Account");
		
		int answer = inputs.nextInt();
		
		switch (answer) {
			case 1:
				showAccount();
				break;
			case 2: 
				showInfo();
				break;
			case 3:
				changeBalance(true);
				break;
			case 4:
				changeBalance(false);
				break;
			case 5:
				transfer();
				break;
			case 6: 
				applyForJointAccount();
				break;
			case 7:
				closeAccount();
				break;
			default:
				System.out.println("That is not valid input.");
		}
		
	}
	
	private void showAccount() {
		
		for(Account temp : client.getAccounts()) {
			System.out.println(temp);
		}
		
	}
	
	private void showInfo() {
		User temp = client.getPerson();

		System.out.println(temp);
		System.out.println(uDAO.findUserInfo(temp.getUserID()));
		
	}
	
	private void changeBalance(boolean withdraw) {

		// set variables
		Account acctChanged = new Account();
		String transaction;
		
		if(withdraw) {
			transaction = "withdraw";
		}
		else {
			transaction = "deposit";
		}
		
		// pull up account		
		if(client.getAccounts().size() > 1) {
			System.out.println("Enter account number for the account to " + transaction + " from:\n");
			int acct = inputs.nextInt();
			
			for(Account temp : client.getAccounts()) {
				if(temp.getAccountID() == acct) {
					acctChanged = temp;
					break;
				}
			}
		
		}
		else {
			acctChanged = client.getAccounts().get(0);
		}
		
		//check balance
		double curr = acctChanged.getBalance();
		
		System.out.println("Current Balance: " + curr);
		
		System.out.println("How much would you like to "+transaction+"?\n");
		double amnt = inputs.nextDouble();
		
		if(withdraw) {
			if(amnt > curr) {
				System.out.println("There is not enough money in your account for this withdrawal.");
				return;
			}
			else {
				acctChanged.setBalance(curr - amnt);
			}
		}
		else
		{
			acctChanged.setBalance(curr + amnt);
		}
		
		
		aDAO.updateAccount(acctChanged);
		
		// log
		
		System.out.println("Your new balance is: " + acctChanged.getBalance());
		
	}
	
	
	private void transfer() {
		
		// call dao function for transfer so it can be a transaction
		
	}
	
	private void applyForJointAccount() {
		
	}
	
	private void closeAccount() {
		
		Account a = new Account();
		
		if(client.getAccounts().size() == 1) {
			a = client.getAccounts().get(0);
		}
		
		a.setStatus("Closed");
		
		aDAO.updateAccount(a);
		
		//log
		
	}
	
}
