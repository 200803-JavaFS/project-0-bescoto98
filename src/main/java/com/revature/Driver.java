package com.revature;

import java.util.Scanner;
import com.revature.dao.*;
import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {
		
		Scanner inputs = new Scanner(System.in);
		
		System.out.println("Welcome 2 da bank");
		System.out.println("+-------------------------------------+\n");
		
		System.out.println("Enter username:\n+- ");
		String username = inputs.nextLine();
		
		System.out.println("Enter password:\n+- ");
		String password = inputs.nextLine();
		
		System.out.println("Username: " + username + " Password: " + password);
		
		get logger
		
		// get type
		
		// print type menu
		
		inputs.close();

	}
	
	private void printCustomerMenu() {
		System.out.println("user");
	}
	
	private void printEmployeeMenu() {
		System.out.println("employee");
	}
	
	private void printAdminMenu() {
		System.out.println("admin");
		
	}

}
