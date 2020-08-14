package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IUserDAO {
	
	public List<User> findAll();
	public int findUserID(String username);
	public User findUser(int id);
	public User findUser(String username);
	public Information findUserInfo(int id);
	public boolean uniqueUsername(String u);
	public boolean addUser(User u, Information i);
	public boolean updateUserInfo(Information i);
	public boolean updateUser(User u);
	public List<User> findSubgroup(int type);
	

}
