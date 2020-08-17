/**
 * to do: approve accounts
 */

package com.revature.services;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public EmployeeServices() {
		System.out.println("Welcome, employee!");
		login();
	}
	
	private void login() {
		System.out.println("Enter your username:\n");
		String username = inputs.nextLine();
		
		System.out.println("\nEnter your password:\n");
		String password = inputs.nextLine();
		
		try {
			User temp = uDAO.findUser(username);
			if(temp.getPassword().equals(password) && temp.getType() == 1) {
				currentUser = temp;
			}
			else {
				System.out.println("Invalid login");
			}
		} catch(NullPointerException e) {
			System.out.println("That is not a valid login.");
		}
		
		showMenu();
		
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
					"(6) Logout" +
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
					approveAccounts();
					break;
				case 6:
					going = false;
					break;
				default:
					System.out.println("Invalid output");
			}
		}
	
	}

	private void approveAccounts() {
		System.out.println("Enter the account number of the account you wish to approve:\n");
		int acct = inputs.nextInt();
		
		Account a = aDAO.findByAcctID(acct);
		a.setStatus("Open");
		String whoApproved = currentUser.getFirstName() + " " + currentUser.getLastName();
		a.setApprovedBy(whoApproved);
		
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyy");
		a.setCreatedOn(f.format(d));
		
		if(aDAO.updateAccount(a))
		{
			System.out.println(a);
			log.info("Account approved:\n" + a);
		}
		else {
			System.out.println("There was an error, try again later.");
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
//		System.out.println("Select value to change:"
//				+ "(1) Address\n"
//				+ "(2) City \n"
//				+ "(3) State\n"
//				+ "(4) ZIP\n"
//				+ "(5) Phone number\n"
//				+ "(6) Email address\n"			
//				);
//		
//		int choice = inputs.nextInt();
//		
//		switch(choice) {
//			case 1:
//		}
		Information info = new Information();
		System.out.println("| Enter your street address:\t");
		info.setAddress(inputs.nextLine());
		
		System.out.println("| Enter the city:\t");
		info.setCity(inputs.nextLine());
		
		System.out.println("| Enter your state:\t");
		info.setState(inputs.nextLine());
		
		System.out.println("| Enter the ZIP code:\t");
		info.setZip(inputs.nextLine());
		
		// validate phone number
		System.out.println("| Enter your phone number:\t");
		String phone = inputs.nextLine();
		phone = phone.replaceAll("\\D","");
		info.setPhone(phone);
		
		// validate email
		// help w/ regular expressions found here https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		while(true) {
			System.out.println("| Enter your email address:\t");
			String email = inputs.nextLine();
			
			Pattern emPattern = Pattern.compile("^(.+)@(.+)$");
			Matcher passwordM = emPattern.matcher(email);
			
			if(passwordM.matches()) {
				info.setEmail(email);
				break;
			}
			else {
				System.out.println("Invalid email");
			}
			
		}
		
		if(uDAO.updateUserInfo(info)) {
			System.out.println("Information updated sucessfully");
			showEmployeeInfo();
		}
		else {
			System.out.println("Something went wrong. Try again later");
		}
		
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
