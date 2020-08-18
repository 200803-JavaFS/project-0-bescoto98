package com.revature.services;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.models.*;
import com.revature.dao.*;
import com.google.common.hash.Hashing;
import com.revature.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerCreation {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	Scanner inputs = new Scanner(System.in);
	private UserDAO uDAO = new UserDAO();
	private BasicServices service = new BasicServices();

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
	
		while(true) {
			System.out.println("Enter a username that is at least five characters long:\n\t");
			username = inputs.nextLine();
			username = username.toLowerCase();
			
			if(username.length() > 4 && uDAO.uniqueUsername(username)) {
				u.setUsername(username);
				break;
			}
			else {
				System.out.println("Invalid username");
			}
		}
		
		while(true) {
			System.out.println("Enter a password at least five characters long:\n\t");
			String password = inputs.nextLine();
			
			if(password.length()<4) {
				System.out.println("That password is too short");
			}
			else {
				
				// https://javadoc.io/doc/com.google.guava/guava/13.0/com/google/common/hash/Hashing.html
				String hashedPW = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
				u.setPassword(hashedPW);
				break;
			}
		}
		
		u.setType(2);
		
		System.out.println("+----------------------------------+"
				+ "\n| Enter your personal information to make an account:");
		
		Information info = new Information();
		
		while(true) {
			System.out.println("| Enter your social security numer:\t");
			String ssn = inputs.nextLine();
			
			if(ssn.length() != 9) {
				System.out.println("That is not a valid SSN.");
			}
			else {
				info.setSsn(ssn);
				break;
			}
		}
		
		System.out.println("| Enter your street address:\t");
		info.setAddress(inputs.nextLine());
		
		System.out.println("| Enter the city:\t");
		info.setCity(inputs.nextLine());
		
		System.out.println("| Enter your state:\t");
		info.setState(inputs.nextLine());
		
		System.out.println("| Enter the ZIP code:\t");
		info.setZip(inputs.nextLine());
		
		// remove special characters from phone number
		System.out.println("| Enter your phone number:\t");
		String phone = inputs.nextLine();
		phone = phone.replaceAll("\\D","");
		info.setPhone(phone);
		
		// validate email
		// help w/ regular expressions found here 
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
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
			log.info("user created: " + u + info);
			createAccount(uDAO.findUserID(username));	
		}
		else {
			System.out.println("There was an error saving your information, please try again later");
			log.error("unable to create user:" + u + info);
		}
	}

	private void createAccount(int userID) {
		
		System.out.println("Would you like to make a Checking account or Savings account?\n");
		while(true) {
			String type = inputs.nextLine();
			
			if(type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("savings")) {
				
				if(service.createAccount(userID,type)) {
					System.out.println("Your request to open an account has been created.");
					log.info("account request by userID: " + userID);
					return;
				}
				else {
					System.out.println("Your request could not be completed at this time.");
					log.error("unable to create account for userID: " + userID);
				}
			}
			else {
				System.out.println("That is not a valid option");
			}
		}
	}

}
