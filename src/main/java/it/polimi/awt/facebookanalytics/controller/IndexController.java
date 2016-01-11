package it.polimi.awt.facebookanalytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

	@Autowired
	private Facebook facebook;

	@RequestMapping("/")
	public String indexRedirect() {
		if (!facebook.isAuthorized()) {
			return "index";
		} else {
			return "redirect:/user";
		}
	}

}