package it.polimi.awt.facebookanalytics.dao;

import java.util.List;

import it.polimi.awt.facebookanalytics.model.User;

public interface UserDAO {
	
	public void insertUser(User user);
	public void updateUser(User user);
	public void upsertUser(User user);
	public List<User> findAll();
	public User findUser(String uid);

}
