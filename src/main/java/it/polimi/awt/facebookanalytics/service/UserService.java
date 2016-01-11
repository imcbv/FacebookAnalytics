package it.polimi.awt.facebookanalytics.service;

import it.polimi.awt.facebookanalytics.model.Friend;
import it.polimi.awt.facebookanalytics.model.User;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

public interface UserService {

	public void loginUser(Facebook facebook);

	public void setFriends(Facebook facebook);

	public String getUid();

	public String getName();

	public String getUsername();

	public String getLocation();

	public String getGender();

	public String getLink();

	public List<Friend> getFriends();

	public Friend getFriend(String uid);

	public String getPicture();

	public String getPicture(String dimension);

	public String getPic_big();

	public String getPic_square();

	public void setCommonFriends(String[] userIds, Facebook facebook);

	public List<Friend> getSelectedFriends();

	public List<User> findAll();

	public float getDegreeCentrality(String uid);

	public float getNormalizedDegreeCentrality(String uid);

	public float getBetweennessCentrality(String uid);

	public float getNormalizedBetweennessCentrality(String uid);

	public float getClosenessCentrality(String uid);

	public float getNormalizedClosenessCentrality(String uid);

}
