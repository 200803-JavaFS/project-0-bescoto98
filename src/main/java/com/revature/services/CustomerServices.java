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
	private BasicServices service = new BasicServices();
	
	private Customer client = new Customer();
	
	public CustomerServices() {
		System.out.println("Welcome returning customer!");
		signIn();
	}
	
	private void signIn() {
		System.out.println("Enter your username: ");
		String username = inputs.nextLine();
		
		System.out.println("\nEnter your password: ");
		String password = inputs.nextLine();
		
		if(service.login(2,username,password)) {
			client.setPerson(uDAO.findUser(username));
			client.setAccounts(aDAO.findUserAccounts(client.getPerson().getUserID()));
			showMenu();
		}
		else {
			System.out.println("Login attempt failed.");
		}
		
	}

	private void showMenu() { 
		boolean going = true;
		
		while(going)
		{
			System.out.println("+-------------------------------------+");
			System.out.println("| Menu options:\n" +
					"| (1) Show My Accounts\n" +
					"| (2) Show My Information\n"+
					"| (3) Withdraw from Account\n" +
					"| (4) Deposit into Account\n" +
					"| (5) Transfer money from accounts\n" +
					"| (6) Apply for Joint Account\n" +
					"| (7) Open a new account\n" +
					"| (8) Logout"
					+ "\n+-------------------------------------+");
			
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
					openAccount();
					break;
				case 8:
					going = false;
					break;
				default:
					System.out.println("That is not valid input.");
			}
		}
		
	}
	
	private void openAccount() {
		System.out.println("Would you like to make a Checking account or Savings account?\n");
		while(true) {
			String type = inputs.nextLine();
			
			if(type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings")) {
				
				if(service.createAccount(client.getPerson().getUserID(),type)) {
					System.out.println("Your request to open an account has been created.");
					log.info("account request by userID: " + client.getPerson().getUserID());
				}
				return;
			}
			else {
				System.out.println("That is not a valid option, try again.");
			}
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
		
		Account acctChanged = new Account();
		
		String transaction;
		if(withdraw) {
			transaction = "withdrawal";
		}
		else
		{
			transaction = "deposit";
		}
		// pull up account		
		if(client.getAccounts().size() > 1) {
			System.out.println("Enter account number for the " + transaction + ":\n");
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
		
		System.out.println("Current Balance: " + acctChanged.getBalance());
		
		System.out.println("Amount?");
		double amnt = inputs.nextDouble();
		
		if(service.changeBalance(withdraw,acctChanged,amnt)) {
			if(withdraw) {
				log.info("balance changed withdrawal of amount $" + amnt + 
						" for accountID: " + acctChanged.getAccountID() + 
						" by userID: " + client.getPerson().getUserID());
			}
			else {
				log.info("balance changed deposit of amount $" + amnt + 
						" for accountID: " + acctChanged.getAccountID() + 
						" by userID: " + client.getPerson().getUserID());
			}
			System.out.println("Your new balance is: " + acctChanged.getBalance());
		}
		else {
			System.out.println("There was an error, try again later.");
		}
		
	}
	
	
	private void transfer() {
		
		// if user has more than one, check which from, else
		Account from = new Account();
		if(client.getAccounts().size() > 1) {
			System.out.println("Enter account number for the account to transfer from:\n");
			int acct = inputs.nextInt();
			
			for(Account temp : client.getAccounts()) {
				if(temp.getAccountID() == acct) {
					from = temp;
					break;
				}
			}
		
		}
		else {
			from = client.getAccounts().get(0);
		}
		
		System.out.println("Enter account number to transfer to:\n");
		int toAcct = inputs.nextInt();
		
		Account to = aDAO.findByAcctID(toAcct);
		
		System.out.println("Enter amount to transfer from Account #" + from.getAccountID() 
			+ " to Account #" + to.getAccountID());
		double amnt = inputs.nextDouble();
	
		if(amnt > from.getBalance()) {
			System.out.println("There is not enough money for this transaction.");
			return;
		}
		
		if(aDAO.transferMoney(from,to,amnt)) {
			System.out.println("Transfer successful! Current balance: " + (from.getBalance() - amnt));
			log.info("transfer occured from account: " + from.getAccountID() + " to account: " + to.getAccountID()
					+ " amount: " + amnt + " by userID:" + client.getPerson().getUserID());
		}
		else {
			System.out.println("Unable to process this request. Please try again later.");
		}

	}
	
	private void applyForJointAccount() {
		
		System.out.println("Enter the account number for the joint account: ");
		int joint = inputs.nextInt();
		
		int userID = client.getPerson().getUserID();
		
		if(aDAO.addJointAccount(joint,userID)) {
			System.out.println("Joint account opened.");
			log.info("joint acccount opened Account: " + joint + " client ID: " + userID);
		}
	}
	
	
}
