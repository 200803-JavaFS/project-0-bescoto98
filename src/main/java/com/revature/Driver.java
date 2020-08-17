package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Customer;
import com.revature.models.Information;
import com.revature.services.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
//		UserDAO s = new UserDAO();
//		AccountDAO a = new AccountDAO();
//		Information i = s.findUserInfo();
//		i.setState("NY");
//		System.out.println(s.updateUserInfo(i));
//			
//		List<Customer> c = a.findEApprovedAccts("Steve","Jacobs");
//		for(Customer temp : c) {
//			System.out.println(temp);
//		}
		Scanner inputs = new Scanner(System.in);
		
		System.out.println("Welcome 2 da bank");
		System.out.println("+-------------------------------------+");
		
		while(true) {
			System.out.println("| Menu options: "
					+ "\n| (1) New Customer"
					+ "\n| (2) Returning Customer"
					+ "\n| (3) Employee"
					+ "\n| (4) Admin"
					+ "\n| (5) Exit"
					+ "\n+-------------------------------------+");
			
			System.out.print("| # ");
			
			int userType = inputs.nextInt();
			
			switch (userType) {
				case 1:
					CustomerCreation s = new CustomerCreation();
					break;
				case 2:		
					CustomerServices r = new CustomerServices();
					break;
				case 3:
					EmployeeServices e = new EmployeeServices();
					break;
				case 4:
					AdminServices a = new AdminServices();
					break;
				case 5:
					inputs.close();
					System.out.println("Goodbye!");
					System.exit(0);
				default:
					System.out.println("This is not a valid input.");
			}
		}
		
		

	}


}
