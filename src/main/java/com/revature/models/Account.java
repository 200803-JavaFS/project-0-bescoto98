package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int accountID;
	private double balance;
	private String type;
	private String status;
	private String approvedBy;
	private String createdOn;
	
	public Account() {
		super();
	}
	
	

	public Account(double balance, String type, String status, String approvedBy, String createdOn) {
		super();
		this.balance = balance;
		this.type = type;
		this.status = status;
		this.approvedBy = approvedBy;
		this.createdOn = createdOn;
	}



	public Account(int accountID, double balance, String type, String status, String approvedBy, String createdOn) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.type = type;
		this.status = status;
		this.approvedBy = approvedBy;
		this.createdOn = createdOn;
	}



	public int getAccountID() {
		return accountID;
	}



	public double getBalance() {
		return balance;
	}



	public String getType() {
		return type;
	}



	public String getStatus() {
		return status;
	}



	public String getApprovedBy() {
		return approvedBy;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public void setType(String type) {
		this.type = type;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Account || " + accountID + "\n\tbalance: $" + balance + "\n\ttype: " + type 
				+ "\n\tstatus: " + status
				+ "\n\tapprovedBy: " + approvedBy + "\n\tcreated on: " + createdOn;
	}
	
	
	
	
	
	
}
