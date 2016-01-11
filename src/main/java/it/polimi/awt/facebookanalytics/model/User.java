package it.polimi.awt.facebookanalytics.model;

import it.polimi.awt.facebookanalytics.dao.UserDAOImpl;
import it.polimi.awt.facebookanalytics.service.FacebookService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FqlResult;
import org.springframework.social.facebook.api.FqlResultMapper;



@Entity
@Table(name = "User")
public class User {
	
	// Set the max number of friends to fetch
	public static final int LIMIT = 100;

	@Id
	private String uid;
	private String name;
	private String username;
	private String location;
	private String gender;
	private String link;
	private String pic_big;
	private String pic_square;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Friend> friends;

	public User() {
		uid = "";
		name = "";
		username = "";
		location = "";
		gender = "";
		link = "";
		pic_big = "";
		pic_square = "";
	}

	public User(Facebook facebook, FacebookService facebookService) {
		uid = facebook.userOperations().getUserProfile().getId();
		name = facebook.userOperations().getUserProfile().getName();
		username = facebook.userOperations().getUserProfile().getUsername();
		if (facebook.userOperations().getUserProfile().getLocation() != null)
			location = facebook.userOperations().getUserProfile().getLocation()
					.getName();
		else
			location = "";
		gender = facebook.userOperations().getUserProfile().getGender();
		link = facebook.userOperations().getUserProfile().getLink();
		pic_big = facebookService.returnImagePath(facebook);
		pic_square = facebookService.returnImagePath(facebook, "square");
		friends = new ArrayList<Friend>();

	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getLocation() {
		return location;
	}

	public String getGender() {
		return gender;
	}

	public String getLink() {
		return link;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public Friend getFriend(String userId) {
		Iterator<Friend> iterator = friends.iterator();
		Friend currentFriend;
		while (iterator.hasNext()) {
			currentFriend = iterator.next();
			if (currentFriend.getUid().equals(userId)) {
				return currentFriend;
			}
		}
		return null;
	}

	public String getPicture() {
		return pic_square;
	}

	public String getPicture(String dimension) {
		if (dimension.compareTo("big") == 0)
			return pic_big;
		else
			return pic_square;
	}

	public String getUid() {
		return uid;
	}

	public String getPic_big() {
		return pic_big;
	}

	public String getPic_square() {
		return pic_square;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}

	public void setPic_square(String pic_square) {
		this.pic_square = pic_square;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setFriends(Facebook facebook) {
		friends = fetchFriends(facebook);
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	private List<Friend> fetchFriends(Facebook facebook) {
		final User user = this;
		List<Friend> results = facebook
				.fqlOperations()
				.query("SELECT pic_big,pic_square,uid,name FROM user WHERE uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) LIMIT "+LIMIT,
						new FqlResultMapper<Friend>() {
							public Friend mapObject(FqlResult result) {
								Friend status = new Friend(user, result
										.getString("name"), result
										.getString("uid"), result
										.getString("pic_square"), result
										.getString("pic_big"));
								return status;
							}
						});

		return results;
	}

}
