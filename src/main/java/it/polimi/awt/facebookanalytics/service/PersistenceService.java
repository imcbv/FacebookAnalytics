package it.polimi.awt.facebookanalytics.service;

import it.polimi.awt.facebookanalytics.model.User;

import java.util.List;

public interface PersistenceService {
	
	public void insertUser(User user);
	public void updateUser(User user);
	public void upsertUser(User user);
	public List<User> findAll();
	public User findUser(String uid);

}
