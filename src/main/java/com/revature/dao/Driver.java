package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public class Driver {

	public static void main(String[] args) {
		UserDAO testUser = new UserDAO();
		
//		List<User> users = testUser.findAll();
//		
//		for(User temp : users) {
//			System.out.println(temp);
//		}

//		User temp = testUser.findByUserID(1);
//		System.out.println(temp);
//		temp.setFirstName("Jane");
//		
//		boolean changed = testUser.updateUser(temp);
//		System.out.println(changed);
		
//		Information testInfo = testUser.findInformationByID(1);
		
//		System.out.println(testInfo);
		
//		List<User> customers = testUser.findAllCustomers();
//		
//		for(User temp : customers) {
//			System.out.println(temp);
//			
//		}
		
		List<User> employees = testUser.findSubgroup(1);
		
		for(User temp : employees) {
			System.out.println(temp);
		}
		
		List<User> customers = testUser.findSubgroup(2);
		
		for(User temp : customers) {
			System.out.println(temp);
		}
		
		
		

	}

}
