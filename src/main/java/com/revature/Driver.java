package com.revature;

import java.util.Scanner;
import com.revature.services.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		
		Scanner inputs = new Scanner(System.in);
		
		System.out.println("Welcome 2 da bank");
		System.out.println("+-------------------------------------+\n");
		
		System.out.println("Are you a (1) New Customer, (2) Returning Customer, (3) Employee, or (4) Admin?\n +- ");
		
		//send to either CustomerCreation, CustomerUtilities, EmployeeUtilities, or AdminUtilities
		int userType = inputs.nextInt();
		
		switch (userType) {
			case 1:
				CustomerCreation s = new CustomerCreation();
				// display menu
				break;
				
			case 2:
				CustomerServices r = new CustomerServices();
				// send there
				break;
				
			case 3:
				EmployeeServices e = new EmployeeServices();
				// send there
				break;
				
			case 4:
				AdminServices a = new AdminServices();
				// send there
				break;
			default:
				System.out.println("This is not a valid input :<");
				log.error("set up loop");
		}
		
//		System.out.println("Enter username:\n+- ");
//		String username = inputs.nextLine();
//		
//		System.out.println("Enter password:\n+- ");
//		String password = inputs.nextLine();
//		
//		log.info("Username: " + username + " Password: " + password);
		
		
		
		// get type
		
		// print type menu
		
		inputs.close();

	}
	
	private void printCustomerMenu() {
		log.info("user");
	}
	
	private void printEmployeeMenu() {
		log.info("employee");
	}
	
	private void printAdminMenu() {
		log.info("admin");
		
	}

}
