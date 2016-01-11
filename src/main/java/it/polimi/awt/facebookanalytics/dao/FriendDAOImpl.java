package it.polimi.awt.facebookanalytics.dao;

import it.polimi.awt.facebookanalytics.model.Friend;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void insertFriend(Friend friend) {

		Session s = sessionFactory.openSession();
		s.beginTransaction();

		s.save(friend);

		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void insertFriends(List<Friend> friends) {
		Session s = sessionFactory.openSession();
		Iterator<Friend> f = friends.iterator();
		s.beginTransaction();

		while (f.hasNext()) {
			s.save(f.next());
		}
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void updateFriend(Friend friend) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();

		s.update(friend);

		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void updateFriends(List<Friend> friends) {
		Session s = sessionFactory.openSession();
		Iterator<Friend> f = friends.iterator();
		s.beginTransaction();

		while (f.hasNext()) {
			s.update(f.next());
		}
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public void upsertFriends(List<Friend> friends) {
		Session s = sessionFactory.openSession();
		Iterator<Friend> f = friends.iterator();
		s.beginTransaction();

		while (f.hasNext()) {
			s.saveOrUpdate(f.next());

		}
		s.getTransaction().commit();
		s.close();
	}

	@Transactional
	public List<Friend> findAll() {
		Session s = sessionFactory.getCurrentSession();
		List<Friend> friends = s.createQuery("from Friend").list();
		s.close();
		return friends;
	}

}
