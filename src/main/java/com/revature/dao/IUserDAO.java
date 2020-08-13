package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IUserDAO {
	
	public List<User> findAll();
	public User findByUserID(int id);
	public boolean addUser(User u);
	public boolean updateUser(User u);
	public Information findInformationByID(int id);
	public List<User> findSubgroup(int type);
	

}
