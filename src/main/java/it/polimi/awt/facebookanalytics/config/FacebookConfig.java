package it.polimi.awt.facebookanalytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableInMemoryConnectionRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.config.annotation.EnableFacebook;

@EnableFacebook(appId="519869694790912", appSecret="2260666924783ee33ad71b9fa96003c7")
@EnableInMemoryConnectionRepository
public class FacebookConfig {

    @Bean
    public FacebookController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new FacebookController(connectionFactoryLocator, connectionRepository);
    }

    @Bean
    public UserIdSource userIdSource() {
        return new UserIdSource() {
            public String getUserId() {
                return "testuser";
            }
        };
    }

}