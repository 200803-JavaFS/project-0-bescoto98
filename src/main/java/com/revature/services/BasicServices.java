package com.revature.services;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.hash.Hashing;
import com.revature.dao.*;
import com.revature.models.*;

public class BasicServices {

	private UserDAO uDAO = new UserDAO();
	private AccountDAO aDAO = new AccountDAO();
	
	public boolean login(int type, String u, String p) {
		try {
			User temp = uDAO.findUser(u);
			
			/** 1. get hashed pw value from database
			 *  2. hash attempt
			 *  3. compare
			 */
			
			String hashedPW = Hashing.sha256().hashString(p, StandardCharsets.UTF_8).toString();
			
			if(temp.getPassword().equals(hashedPW) && temp.getType() == type) {
				return true;
			}
			else {
				System.out.println("Invalid login");
			}
			
		} catch(NullPointerException e) {
			System.out.println("That is not a valid login.");
		}
		
		return false;
	}
	
	public boolean updateInfo(int id) {
		
		Scanner inputs = new Scanner(System.in);
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
		
		info.setUserID(id);
		
		inputs.close();
		if(uDAO.updateUserInfo(info)) {
			System.out.println("Information updated sucessfully");
			return true;
		}
		else {
			System.out.println("Something went wrong. Try again later");
		}
		
		
		return false;
	}
	
	public boolean changeBalance(boolean withdraw,Account a,double amnt) {
		
		if(a.getStatus().equalsIgnoreCase("Pending") || a.getStatus().equalsIgnoreCase("Closed")) {
			return false;
		}
		
		double curr = a.getBalance();
		
		if(withdraw) {
			if(amnt > curr) {
				System.out.println("There is not enough money for this transaction.");
				return false;
			}
			a.setBalance(curr - amnt);
		}
		else {
			a.setBalance(curr + amnt);
		}
		
		aDAO.updateAccount(a);
		
		return true;

	}
	
	public boolean changeAccStatus(int id, boolean approve,String whoApproved) {
		
		Account a = aDAO.findByAcctID(id);
		if(approve) {
			a.setStatus("Open");
		}
		else {
			a.setStatus("Closed");
		}
		a.setApprovedBy(whoApproved);
		
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyy");
		a.setCreatedOn(f.format(d));
		
		return aDAO.updateAccount(a);
	}
	
	public boolean createAccount(int userID, String type) {
		
		Account a = new Account();
		a.setBalance(0.0);
		a.setStatus("Pending");
		a.setType(type);
		
		
		if(aDAO.addAccount(a,userID)) {
			return true;
		}
		return false;
	}

}
