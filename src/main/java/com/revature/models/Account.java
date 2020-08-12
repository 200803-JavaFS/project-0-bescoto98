package com.revature.models;

public class Account {
	
	private String FN;
	private String LN;
	private String username;
	private String password;
	private int accountNum;
	private double balance;
	private int idNumber;
	private String acctType;
	
	public Account() {
		this.FN = null;
		this.LN = null;
		this.username = null;
		this.password = null;
		this.accountNum = 0;
		this.balance = 0;
		this.idNumber = 0;
	}
	
	public Account(int id) {
		this.idNumber = id;
		this.FN = null;
		this.LN = null;
		this.username = null;
		this.password = null;
		this.accountNum = 0;
		this.balance = 0;
	}
	
	public Account(String u, String p) {
		this.username = u;
		this.password = p;
		this.accountNum = 0;
		this.balance = 0;
		this.FN = null;
		this.LN = null;
		this.idNumber = 0;
	}

	public String getFN() {
		return FN;
	}

	public String getLN() {
		return LN;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public double getBalance() {
		return balance;
	}

	public int getIdNumber() {
		return idNumber;
	}
	
	public String getAcctType() {
		return acctType;
	}
	
	public void setAcctType(String a) {
		this.acctType = a;
	}

	public void setFN(String fN) {
		FN = fN;
	}

	public void setLN(String lN) {
		LN = lN;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	

}
