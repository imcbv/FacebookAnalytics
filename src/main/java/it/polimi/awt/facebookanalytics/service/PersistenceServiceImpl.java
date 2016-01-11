package it.polimi.awt.facebookanalytics.service;

import it.polimi.awt.facebookanalytics.dao.UserDAO;
import it.polimi.awt.facebookanalytics.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	UserDAO userDAO;

	@Override
	public void insertUser(User user) {
		userDAO.insertUser(user);

	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);

	}

	@Override
	public void upsertUser(User user) {
		userDAO.upsertUser(user);

	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findUser(String uid) {
		return userDAO.findUser(uid);
	}

}
