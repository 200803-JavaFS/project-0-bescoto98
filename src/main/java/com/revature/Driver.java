package com.revature;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.common.hash.Hashing;
import com.revature.services.*;

public class Driver {

	public static void main(String[] args) {

		Scanner inputs = new Scanner(System.in);
		
		System.out.println("Welcome to Not Wells Fargo™");
		System.out.println("+-------------------------------------+");
		
//		String[] passes = {"password","longerpassword","thebiggestpasswordicanthinkofgoddamnthatslong"};
//		
//		for(String s : passes) {
//			String sha256hex = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
//			System.out.println(s + "\n" + s.length());
//			System.out.println(sha256hex + "\n" + sha256hex.length());
//		}
		
//		String hashedPW = Hashing.sha256().hashString("root", StandardCharsets.UTF_8).toString();
//		System.out.println(hashedPW);
		
		
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
