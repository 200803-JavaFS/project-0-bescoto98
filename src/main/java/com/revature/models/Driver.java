package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	
	public static void main(String[] args) {
		
		User tim = new User();
		tim.setFirstName("Tim");
		tim.setLastName("Jungle");
		tim.setType(3);
		tim.setUsername("coolJim44");
		tim.setPassword("3kittens");
		tim.setUserID(8989);
		
		User george = new User(20,"George","Stevenson","awesomeG00","password",3);
		
//		System.out.println(tim);
//		System.out.println(george);

		
		Account timsAccount = new Account(56,90.89,"Checking","Open","Worker1","02-06-1995");
		Account georgesAccount = new Account();
		
		georgesAccount.setAccountID(99);
		georgesAccount.setBalance(66.45);
		georgesAccount.setStatus("Pending");
		georgesAccount.setApprovedBy("Worker1");
		georgesAccount.setCreatedOn("06-15-2006");
		georgesAccount.setType("Savings");
		
		
//		System.out.println(timsAccount);
//		System.out.println(georgesAccount);
		
		Customer married = new Customer(tim);
		
		List<Account> acs = new ArrayList<>();
		
		acs.add(timsAccount);
		acs.add(georgesAccount);
		
		married.setAccounts(acs);
		
		System.out.println(married);
		
		
	}
	

}
