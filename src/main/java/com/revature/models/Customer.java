package com.revature.models;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User person;
	private List<Account> accounts;

	
	public Customer() {
		super();
	}
	
	public Customer(User person) {
		super();
		this.person = person;
	}

	public Customer(User person, List<Account> accounts) {
		super();
		this.person = person;
		this.accounts = accounts;
	}

	public User getPerson() {
		return person;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setPerson(User person) {
		this.person = person;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder customerInfo = new StringBuilder("Customer:" + person.getUserID());
		
		customerInfo.append(", Name: " + person.getFirstName() + " " + person.getLastName() + ", Accounts: ");
		
		for(Account a: accounts) {
			customerInfo.append("\n\t" + a.toString());
		}
		
		return customerInfo.toString();
	}
	
//	public void addAccount(Account a) {
//		accounts.add(a);
//	}
	
	

}
