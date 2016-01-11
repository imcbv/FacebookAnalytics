package it.polimi.awt.facebookanalytics.dao;

import java.util.List;

import it.polimi.awt.facebookanalytics.model.Friend;

public interface FriendDAO {

	public void insertFriend(Friend friend);
	public void insertFriends(List<Friend> friends);
	public void updateFriend(Friend friend);
	public void updateFriends(List<Friend> friends);
	public void upsertFriends(List<Friend> friends);
	public List<Friend> findAll();
	
}
