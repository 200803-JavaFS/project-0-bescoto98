package com.revature.services;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.Driver;
import com.revature.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerCreation {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO userTool = null;
	private AccountDAO acctTool = null;

	public CustomerCreation() {
		System.out.println("Welcome, new customer!");
		makeUser();
	}
		
	private void makeUser() {
		
		//first name & last name
		System.out.println("Enter your first name:\t");
		String f = inputs.nextLine();
		
		System.out.println("Enter your last name:\t");
		String l = inputs.nextLine();
		
		System.out.println("Enter your social security numer:\t");
		String ssn = inputs.nextLine();
		
		System.out.println("Enter your street address:\t");
		String st = inputs.nextLine();
		
		System.out.println("Enter the city:\t");
		String city = inputs.nextLine();
		
		System.out.println("Enter the ZIP code:\t");
		String zip = inputs.nextLine();
		
		// validate phone number
		System.out.println("Enter your phone number:\t");
		String phone = inputs.nextLine();
		phone = phone.replaceAll("\\D","");
		int pNum = Integer.parseInt(phone);
		
		// validate email
		// help w/ regular expressions found here https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		System.out.println("Enter your email address:\t");
		String email = inputs.nextLine();
		
		Pattern emPattern = Pattern.compile("^(.+)@(.+)$");
		Matcher m = emPattern.matcher(email);
		
		if(!m.matches()) {
			log.error("invalid email");
		}
		
		//userdao
		//getnewidnum() returns max +1
		// tack info insertion into user dao?
		//createuser(f,l,ssn,st,city,zip,pnum,email,type
		
		// insert into users, then information, then logins, then approvals once approved, accounts
		
		createUsername(0);
	}
	
	
	private void createUsername(int id) {
		
		//will handle validating username & password
		
		// username code
		System.out.println("Enter a username that includes digits and is at least five characters long:\nEx:user55\n\t");
		String username = inputs.nextLine();
		username = username.toLowerCase();
		
		Pattern uNamePattern = Pattern.compile("^[a-z0-9]{5,20}");
		Matcher m = uNamePattern.matcher(username);
		
		
		if(!m.matches()) {
			log.error("invalid email");
		}
		
		// password code
		System.out.println("Enter a password at least five characters long:\n\t");
		String password = inputs.nextLine();
		
		if(password.length()<5) {
			log.error("password too short");
		}
		
		// hashing function ?
		
		//insert into logins
		
		//send to customer class
		
	}

}
