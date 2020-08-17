package com.revature.services;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.dao.AccountDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Account;
import com.revature.models.Information;
import com.revature.models.User;

public class AdminServices {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO uDAO = new UserDAO();
	private AccountDAO aDAO = new AccountDAO();
	private User currentUser = new User();
	private BasicServices service = new BasicServices();
	
	public AdminServices() {
		System.out.println("Welcome, admin!");
		signIn();
	}
	
	private void signIn() {
		System.out.println("Enter your username:\n");
		String username = inputs.nextLine();
		
		System.out.println("\nEnter your password:\n");
		String password = inputs.nextLine();
		
		currentUser = service.login(0,username,password);
		if(currentUser != null) {
			showMenu();
		}
	}

	private void showMenu() {
		boolean going = true;
		int indx;
		while(going) {
			indx = 1;
			System.out.println("+-------------------------------------+\n" +
					"Menu options:\n" +
					"("+ (indx++) +") Show My Information\n"+
					"("+ (indx++) +") Update My Information\n" +
					"("+ (indx++) +") View Pending Accounts\n" +
					"("+ (indx++) +") Approve Pending Account\n" +
					"("+ (indx++) +") Deny Pending Account\n" +
					"("+ (indx++) +") Withdraw from Account\n" +
					"("+ (indx++) +") Deposit into Account\n" +
					"("+ (indx++) +") Transfer money from accounts\n" +
					"("+ (indx++) +") Cancel Account\n" +
					"("+ (indx++) +") Logout" +
					"\n+-------------------------------------+\n| "
					);
			
			int answer = inputs.nextInt();
			
			switch (answer) {
				case 1:
					showInfo();
					break;
				case 2:
					updateInfo();
					break;
				case 3:
					pendingAccts();
					break;
				case 4:
					changeAcctStatus(true);
					break;
				case 5:
					changeAcctStatus(false);
					break;
				case 6:
					changeBalance(true);
					break;
				case 7:
					changeBalance(false);
					break;
				case 8:
					transfer();
					break;
				case 9:
					cancelAccount();
					break;
				case 10:
					going = false;
					break;
				default:
					System.out.println("Invalid output");
			}
		}
	
		
	}

	private void cancelAccount() {
		System.out.println("Enter account ID:");
		int acct = inputs.nextInt();
		
		Account a = aDAO.findByAcctID(acct);
		
		a.setStatus("Closed");
		if(aDAO.updateAccount(a)) {
			System.out.println("Account successfully closed.");
			System.out.println(a);
			//log
		}
		else
		{
			System.out.println("There was an error processing this request.");
			//log
		}
	
		
	}

	private void transfer() {
		System.out.println("Enter the account ID for the account to transfer from: ");
		int fromID = inputs.nextInt();
		
		System.out.println("Enter the account ID for the account to transfer to: ");
		int toID = inputs.nextInt();
		
		System.out.println("Enter amount: ");
		double amnt = inputs.nextDouble();
		
		Account a = aDAO.findByAcctID(fromID);
		Account b = aDAO.findByAcctID(toID);
		if(a.getBalance() < amnt) {
			System.out.println("There are not enough funds for this.");
			return;
		}
		
		if(amnt < 0) {
			System.out.println("Error.");
			return;
		}
		
		if(aDAO.transferMoney(a,b,amnt)) {
			System.out.println("Transaction successful.");
			// log
		}
		else {
			System.out.println("There was an error with this transaction, try again later.");
			log.error("unable to transfer" + a + b + "$" + amnt);
		}
		
	}

	private void changeBalance(boolean b) {
		System.out.println("Enter the account ID: ");
		int id = inputs.nextInt();
		
		Account a = aDAO.findByAcctID(id);
		
		System.out.println("Enter the amount: ");
		double amnt = inputs.nextDouble();
		
		if(service.changeBalance(b,a,amnt)) {
			//log
		}
		else {
			System.out.println("There was an error. Please try again later.");
		}
		
		
	}

	private void changeAcctStatus(boolean b) {
		System.out.println("Enter the account number:\n");
		int acct = inputs.nextInt();
		
		String whoApproved = currentUser.getFirstName() + " " + currentUser.getLastName();
		
		if(service.changeAccStatus(acct,b,whoApproved)) {
			Account a = aDAO.findByAcctID(acct);
			System.out.println(a);
			log.info("Account changed:\n" + a);
		}
		else
		{
			System.out.println("There was an error with this request.");
			log.error("unable to approve account with id: " + acct);
		}
		
	}

	private void pendingAccts() {
		List<Account> pending = aDAO.findPendingAccounts();
		
		for(Account temp : pending) {
			System.out.println(temp);
		}
		
	}

	private void updateInfo() {
		System.out.println("+-------------------------------------+\nCurrent user information:\n");
		showInfo();
		
		service.updateInfo(currentUser.getUserID());
		
	}

	private void showInfo() {
		System.out.println(currentUser);
		Information i = uDAO.findUserInfo(currentUser.getUserID());
		System.out.println(i);
		
	}

}
