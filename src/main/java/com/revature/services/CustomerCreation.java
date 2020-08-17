package com.revature.services;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.models.*;
import com.revature.dao.*;

import com.revature.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerCreation {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO uDAO = new UserDAO();
	private AccountDAO aDAO = new AccountDAO();

	public CustomerCreation() {
		System.out.println("Welcome, new customer!");
		createUsername();	
	}

	private void createUsername() {
		
		User u = new User();
		
		System.out.println("Enter your first name:\t");
		u.setFirstName(inputs.nextLine());
		
		System.out.println("Enter your last name:\t");
		u.setLastName(inputs.nextLine());
	
		String username;
		// username validation
		while(true) {
			System.out.println("Enter a username that includes digits and is at least five characters long:\nEx:user55\n\t");
			username = inputs.nextLine();
			username = username.toLowerCase();
			
			Pattern uNamePattern = Pattern.compile("^[a-z0-9]{5,20}");
			Matcher usernameM = uNamePattern.matcher(username);
			
			if(usernameM.matches() && uDAO.uniqueUsername(username)) {
				u.setUsername(username);
				break;
			}
			else {
				System.out.println("Invalid username");
			}
		}
		
		while(true) {
			// password code
			System.out.println("Enter a password at least five characters long:\n\t");
			String password = inputs.nextLine();
			
			if(password.length()<5) {
				System.out.println("That password is too short");
			}
			else {
				u.setPassword(password);
				//hashing function
				break;
			}
		}
		
		u.setType(2);
		
		System.out.println("+----------------------------------+"
				+ "\n| Enter your personal information to make an account:");
		
		Information info = new Information();
		
		System.out.println("| Enter your social security numer:\t");
		info.setSsn(inputs.nextLine());
		
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
		
		if(uDAO.addUser(u,info)) {
			log.info("user created:" + u + info);
			createAccount(uDAO.findUserID(username));	
		}
		else {
			System.out.println("There was an error saving your information, please try again later");
			log.error("unable to create user:" + u + info);
		}
	}

	private void createAccount(int userID) {
		Account a = new Account();
		a.setBalance(0.0);
		a.setStatus("Pending");
		//created on
		
		System.out.println("Would you like to make a Checking account or Savings account?\n");
		
		
		while(true) {
			String type = inputs.nextLine();
			
			if(type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings")) {
				a.setType(type);
				break;
			}
			else {
				System.out.println("That is not a valid option");
			}
		}
		
		if(aDAO.addAccount(a,userID)) {
			System.out.println("Your request to create an account has been sent. Check back later once it has been approved.");
		}
		else {
			System.out.println("There was an error processing your account information, please try again later.");
			log.error("Unable to add account: " + a);
		}
		
		
	}

}
