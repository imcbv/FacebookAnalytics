package it.polimi.awt.facebookanalytics.service;

import it.polimi.awt.facebookanalytics.model.Friend;
import it.polimi.awt.facebookanalytics.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private PersistenceService persistenceService;
	
	@Autowired
	private FacebookService facebookService;

	private User user;
	private List<Friend> selectedFriends;

	public void loginUser(Facebook facebook) {

		User temp = findUser(facebook.userOperations().getUserProfile().getId());

		user = new User(facebook, facebookService);

		if (temp != null) {
			user.setFriends(temp.getFriends());
		}

		upsertUser();
	}

	public void setFriends(Facebook facebook) {
		System.out.println("\n\n\n UserService.setFriends() \n\n\n");
		user.setFriends(facebook);
		upsertUser();
	}

	public String getUid() {
		return user.getUid();
	}

	public String getName() {
		return user.getName();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public String getLocation() {
		return user.getLocation();
	}

	public String getGender() {
		return user.getGender();
	}

	public String getLink() {
		return user.getLink();
	}

	public List<Friend> getFriends() {
		return user.getFriends();
	}

	public Friend getFriend(String uid) {
		return user.getFriend(uid);
	}

	public String getPicture() {
		return user.getPicture();
	}

	public String getPicture(String dimension) {
		return user.getPicture(dimension);
	}

	public String getPic_big() {
		return user.getPic_big();
	}

	public String getPic_square() {
		return user.getPic_square();
	}

	public void setCommonFriends(String[] userIds, Facebook facebook) {
		List<Friend> friends = new ArrayList<Friend>();

		if (userIds != null) {
			for (int i = 0; i < userIds.length; i++) {
				friends.add(getFriend(userIds[i]));
			}
			selectedFriends = friends;
		} else {
			selectedFriends = getFriends();
		}

		Iterator<Friend> iterator = selectedFriends.iterator();
		while (iterator.hasNext()) {
			iterator.next().setCommonFriends(user, facebook);
		}

		updateUser();
	}

	public List<Friend> getSelectedFriends() {
		return selectedFriends;
	}

	private void updateUser() {
		persistenceService.updateUser(user);
	}

	private void upsertUser() {
		persistenceService.upsertUser(user);
	}

	public List<User> findAll() {
		return persistenceService.findAll();
	}

	private User findUser(String uid) {
		return persistenceService.findUser(uid);
	}

	public float getDegreeCentrality(String uid) {
		return getFriend(uid).getDegreeCentrality(selectedFriends);
	}

	public float getNormalizedDegreeCentrality(String uid) {
		return getFriend(uid).getNormalizedDegreeCentrality(selectedFriends);
	}

	public float getBetweennessCentrality(String uid) {
		return getFriend(uid).getBetweennessCentrality(selectedFriends);
	}

	public float getNormalizedBetweennessCentrality(String uid) {
		return getFriend(uid).getNormalizedBetweennessCentrality(selectedFriends);
	}

	public float getClosenessCentrality(String uid) {
		return getFriend(uid).getClosenessCentrality(user, selectedFriends);
	}

	public float getNormalizedClosenessCentrality(String uid) {
		return getFriend(uid).getNormalizedClosenessCentrality(user, selectedFriends);
	}

}
