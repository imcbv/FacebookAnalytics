package it.polimi.awt.facebookanalytics.service;

import it.polimi.awt.facebookanalytics.model.Friend;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FqlResult;
import org.springframework.social.facebook.api.FqlResultMapper;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {
	public FacebookService() {
	}

	private static class ProfileImageObject {
		public String profileImage;
	}

	public String returnImagePath(Facebook facebook, Friend f) {
		List<ProfileImageObject> results = facebook.fqlOperations().query(
				"SELECT pic_square FROM user WHERE uid=" + f.getUid(),
				new FqlResultMapper<ProfileImageObject>() {
					public ProfileImageObject mapObject(FqlResult result) {
						ProfileImageObject status = new ProfileImageObject();
						status.profileImage = result.getString("pic_square");
						return status;
					}
				});

		return results.get(0).profileImage;
	}
	
	public String returnImagePath(Facebook facebook) {
		List<ProfileImageObject> results = facebook.fqlOperations().query(
				"SELECT pic FROM user WHERE uid=me()",
				new FqlResultMapper<ProfileImageObject>() {
					public ProfileImageObject mapObject(FqlResult result) {
						ProfileImageObject status = new ProfileImageObject();
						status.profileImage = result.getString("pic");
						return status;
					}
				});

		return results.get(0).profileImage;
	}
	public String returnImagePath(Facebook facebook, final String dimension) {
		List<ProfileImageObject> results = facebook.fqlOperations().query(
				"SELECT pic_"+dimension+" FROM user WHERE uid=me()",
				new FqlResultMapper<ProfileImageObject>() {
					public ProfileImageObject mapObject(FqlResult result) {
						ProfileImageObject status = new ProfileImageObject();
						status.profileImage = result.getString("pic_"+dimension);
						return status;
					}
				});

		return results.get(0).profileImage;
	}
}
