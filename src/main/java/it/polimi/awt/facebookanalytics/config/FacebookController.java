package it.polimi.awt.facebookanalytics.config;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;


public class FacebookController extends ConnectController{
	
	public FacebookController(
			ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@Override
	protected String connectedView(String providerId) {
		return "redirect:/";
	}
	
	@Override
	protected String connectView(String providerId) {
		return "redirect:/";
	}
	
}
