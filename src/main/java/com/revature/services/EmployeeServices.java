package com.revature.services;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.dao.*;
import com.revature.models.*;

public class EmployeeServices{
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO uDAO = new UserDAO();
	private AccountDAO aDAO = new AccountDAO();
	private User currentUser = new User();
	private BasicServices service = new BasicServices();
	
	public EmployeeServices() {
		System.out.println("Welcome, employee!");
		signIn();
	}
	
	private void signIn() {
		System.out.println("Enter your username: ");
		String username = inputs.nextLine();
		
		System.out.println("\nEnter your password: ");
		String password = inputs.nextLine();
		
		if(service.login(1,username,password)) {
			currentUser = uDAO.findUser(username);
			showMenu();
		}
		else {
			System.out.println("Login attempt failed.");
		}
	}

	
	private void showMenu() {
		
		boolean going = true;
		while(going) {
			System.out.println("+-------------------------------------+\n" +
					"Menu options:\n" +
					"(1) Show Approved Users Information \n" +
					"(2) Show My Information\n"+
					"(3) Update My Information\n" +
					"(4) View Pending Accounts\n" +
					"(5) Approve Pending Account\n" +
					"(6) Deny Pending Account\n" +
					"(7) Logout" +
					"\n+-------------------------------------+\n| "
					);
			
			int answer = inputs.nextInt();
			
			switch (answer) {
				case 1:
					showApprovedUserInfo();
					break;
				case 2:
					showEmployeeInfo();
					break;
				case 3:
					updateInfo();
					break;
				case 4:
					showPendingAccounts();
					break;
				case 5:
					changeAccountStatus(true);
					break;
				case 6:
					changeAccountStatus(false);
					break;
				case 7:
					going = false;
					break;
				default:
					System.out.println("Invalid output");
			}
		}
	
	}

	private void changeAccountStatus(boolean approve) {
		System.out.println("Enter the account number:\n");
		int acct = inputs.nextInt();
		
		String whoApproved = currentUser.getFirstName() + " " + currentUser.getLastName();
		
		if(service.changeAccStatus(acct,approve,whoApproved)) {
			Account a = aDAO.findByAcctID(acct);
			System.out.println(a);
			log.info("Account changed \napproved: " + approve + "\n account ID: " + a.getAccountID() + " by userID: " + currentUser.getUserID());
		}
		else
		{
			System.out.println("There was an error with this request.");
			log.error("unable to approve account with id: " + acct);
		}
		
		
	}

	private void showPendingAccounts() {
		
		List<Account> pending = aDAO.findPendingAccounts();
		
		for(Account temp : pending) {
			System.out.println(temp);
		}
		
		
	}

	private void updateInfo() {
		System.out.println("+-------------------------------------+\nCurrent user information:\n");
		showEmployeeInfo();
		
		service.updateInfo(currentUser.getUserID());
		
	}

	private void showEmployeeInfo() {
		System.out.println(currentUser);
		Information i = uDAO.findUserInfo(currentUser.getUserID());
		System.out.println(i);
		
	}

	private void showApprovedUserInfo() {
		List<Customer> approved = aDAO.findEApprovedAccts(currentUser.getFirstName(),currentUser.getLastName());
		
		for(Customer c : approved) {
			System.out.println(c);
		}
		
	}
}
