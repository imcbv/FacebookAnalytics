package it.polimi.awt.facebookanalytics.controller;

import it.polimi.awt.facebookanalytics.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Facebook facebook;

	@RequestMapping(method = RequestMethod.GET)
	public String userRedirect(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/";
		}

		userService.loginUser(facebook);

		model.addAttribute("user", userService);
		return "personalProfile";
	}

	@RequestMapping("/friendList")
	public String getFriendList(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/";
		}

		System.out.println("\n\n\n UserController.getFriendList() \n\n\n");

		userService.setFriends(facebook);

		model.addAttribute("user", userService);

		return "friendList";
	}

	@RequestMapping("/commonFriends")
	public String getCommonFriends(
			Model model,
			@RequestParam(value = "userIds[]", required = false) String[] userIds) {

		if (!facebook.isAuthorized()) {
			return "redirect:/";
		}

		userService.setCommonFriends(userIds, facebook);

		model.addAttribute("user", userService);
		return "commonFriends";

	}

	@RequestMapping("/commonFriends/graph")
	public String drawGraph(Model model) {

		if (!facebook.isAuthorized()) {
			return "redirect:/";
		}

		model.addAttribute("user", userService);
		return "graph";

	}

	@RequestMapping("/commonFriends/graph/measures")
	public String getCentralityMeasures(Model model,
			@RequestParam(value = "friendId", required = true) String friendId) {

		if (!facebook.isAuthorized()) {
			return "redirect:/";
		}

		model.addAttribute("user", userService);
		model.addAttribute("friendId", friendId);

		return "centralityMeasures";

	}

}
