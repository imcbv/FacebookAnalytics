package it.polimi.awt.facebookanalytics.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Reference;

@Entity
@Table(name = "Friend")
public class Friend {

	@Id
	private String uid;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Friend> friends;
	@ManyToMany(mappedBy = "friends")
	private List<User> users;
	private String name;
	private String pic_square;
	private String pic_big;

	public Friend() {
		this.name = "";
		this.uid = "";
		this.pic_square = "";
		this.pic_big = "";
		this.friends = new ArrayList<Friend>();
	}

	public Friend(User currentUser, String name, String uid, String pic_square,
			String pic_big) {
		if (this.users == null) {
			users = new ArrayList<User>();
		}
		if (!this.users.contains(currentUser)) {
			this.users.add(currentUser);
		}
		this.name = name;
		this.uid = uid;
		this.pic_square = pic_square;
		this.pic_big = pic_big;
		this.friends = new ArrayList<Friend>();
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getPic_square() {
		return pic_square;
	}

	public void setPic_square(String pic_square) {
		this.pic_square = pic_square;
	}

	public String getPic_big() {
		return pic_big;
	}

	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCommonFriends(User currentUser, Facebook facebook) {

		friends = fetchFriends(currentUser, facebook);
	}

	// Return common friends that are also into the selectedFriends List
	public List<Friend> getSelectedCommonFriends(List<Friend> selectedFriends) {
		List<Friend> selectedCommonFriends = new ArrayList<Friend>();
		Iterator<Friend> friendsIterator = friends.iterator();
		Friend f;
		while (friendsIterator.hasNext()) {
			f = friendsIterator.next();
			if (selectedFriends.contains(f)) {
				selectedCommonFriends.add(f);
			}
		}
		return selectedCommonFriends;
	}

	private List<Friend> fetchFriends(User currentUser, Facebook facebook) {

		List<Friend> fetchedFriends = new ArrayList<Friend>();

		List<Reference> fetchedProfiles = facebook.friendOperations()
				.getMutualFriends(uid);
		Iterator<Reference> fetchedIterator = fetchedProfiles.iterator();
		Reference currentReference;
		Friend commonFriend;

		while (fetchedIterator.hasNext()) {
			currentReference = fetchedIterator.next();
			commonFriend = currentUser.getFriend(currentReference.getId());
			if (commonFriend != null) {
				fetchedFriends.add(commonFriend);
			}
		}

		return fetchedFriends;

	}

	public String getPicture() {
		return pic_square;
	}

	public float getDegreeCentrality(List<Friend> selectedFriends) {
		return getSelectedCommonFriends(selectedFriends).size() + 1;
	}

	public float getNormalizedDegreeCentrality(List<Friend> selectedFriends) {
		float degree = getDegreeCentrality(selectedFriends) / selectedFriends.size();
		return degree;
	}

	public float getClosenessCentrality(User currentUser, List<Friend> selectedFriends) {

		float closeness = 0;
		Iterator<Friend> iterator = currentUser.getFriends().iterator();
		while (iterator.hasNext()) {
			if (getSelectedCommonFriends(selectedFriends).contains(iterator.next())) {
				closeness += 1;
			} else {
				closeness += 2;
			}
		}
		return 1 / (closeness + 1);
	}

	public float getNormalizedClosenessCentrality(User currentUser, List<Friend> selectedFriends) {
		return getClosenessCentrality(currentUser, selectedFriends) * selectedFriends.size();
	}

	public float getBetweennessCentrality(List<Friend> selectedFriends) {
		float score = 0;
		float denom;

		Iterator<Friend> iteratorI = getSelectedCommonFriends(selectedFriends).iterator();
		Iterator<Friend> iteratorJ;
		List<Friend> common;
		Friend friendI;
		Friend friendJ;

		while (iteratorI.hasNext()) {
			iteratorJ = getSelectedCommonFriends(selectedFriends).iterator();
			friendI = iteratorI.next();
			while (iteratorJ.hasNext()) {
				friendJ = iteratorJ.next();
				if (!friendI.equals(friendJ)
						&& !friendI.getSelectedCommonFriends(selectedFriends).contains(friendJ)) {
					common = new ArrayList<Friend>(friendI.getSelectedCommonFriends(selectedFriends));
					common.retainAll(friendJ.getSelectedCommonFriends(selectedFriends));
					denom = (1 + common.size());
					score += 1 / denom;
				}
			}
		}
		return score / 2;
	}

	public float getNormalizedBetweennessCentrality(List<Friend> selectedFriends) {

		float denom = ((selectedFriends.size() - 1) * (selectedFriends.size()) / 2);
		return getBetweennessCentrality(selectedFriends) / denom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Friend f = (Friend) obj;
		return uid.equals(f.getUid());

	}

}
