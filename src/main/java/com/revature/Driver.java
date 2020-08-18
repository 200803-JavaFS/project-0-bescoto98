package com.revature;

import java.util.Scanner;
import com.revature.services.*;

public class Driver {

	public static void main(String[] args) {

		Scanner inputs = new Scanner(System.in);
		
		System.out.println("Welcome to Not Wells Fargo™");

		
		while(true) {
					System.out.println("+-------------------------------------+");
					System.out.println("| Menu options: "
					+ "\n| (1) New Customer"
					+ "\n| (2) Returning Customer"
					+ "\n| (3) Employee"
					+ "\n| (4) Admin"
					+ "\n| (5) Exit"
					+ "\n+-------------------------------------+");

			
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
