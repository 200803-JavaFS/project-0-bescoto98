package com.revature.models;

public class User {
	
	private String FN;
	private String LN;
	private int type;
	private String username;
	private String password;
	private int idNumber;
	
	public User() {
		this.idNumber = 0;
		this.FN = null;
		this.LN = null;
		this.type = 4;
		this.username = null;
		this.password = null;
	}
	
	public User(String u, String p) {
		this.idNumber = 0;
		this.FN = null;
		this.LN = null;
		this.type = 4;		
		this.username = u;
		this.password = p;
	}
	

	public String getFN() {
		return FN;
	}

	public String getLN() {
		return LN;
	}

	public int getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public int getIdNumber() {
		return idNumber;
	}

	public void setFN(String fN) {
		FN = fN;
	}

	public void setLN(String lN) {
		LN = lN;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setIdNumber(int i) {
		this.idNumber = i;
	}
	
	

	

}
