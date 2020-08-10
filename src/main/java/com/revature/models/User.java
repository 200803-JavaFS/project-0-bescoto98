package com.revature.models;

public class User {
	
	private String FN;
	private String LN;
	private int type;
	private String username;
	private String password;
	
	public User() {
		this.FN = null;
		this.LN = null;
		this.type = 0;
		this.username = null;
		this.password = null;
	}
	
	public User(String f, String l, int t)
	{
		this.FN = f;
		this.LN = l;
		this.type = t;
		this.username = null;
		this.password = null;
	}
	
	public User(String f, String l, int t, String u, String p)
	{
		this.FN = f;
		this.LN = l;
		this.type = t;
		this.username = u;
		this.password = p;
	}
	
	
	/** robust getters  **/
	
	// return full name
	public String getName() {
		return FN.concat(" "+LN);
	}
	
	// return type of user
	public int getType() {
		return type;
	}
	
	// return username & password combo
	public String getCredentials() {
		return username.concat(password);
	}
	
	
	public boolean setCredentials(String u, String p)
	{
		this.username = u;
		this.password = p;
		return true;
	}
	

}
