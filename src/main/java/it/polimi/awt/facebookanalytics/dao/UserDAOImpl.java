package it.polimi.awt.facebookanalytics.dao;

import it.polimi.awt.facebookanalytics.model.User;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void insertUser(User user) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(user);
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void updateUser(User user) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.update(user);
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void upsertUser(User user) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.saveOrUpdate(user);
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public List<User> findAll() {
		Session s = sessionFactory.getCurrentSession();
		List<User> users = s.createQuery("from User").list();
		s.close();
		return users;
	}

	@Transactional
	public User findUser(String uid) {
		Session s = sessionFactory.openSession();
		User user = (User) s.get(User.class, uid);
		s.close();
		return user;

	}

}
