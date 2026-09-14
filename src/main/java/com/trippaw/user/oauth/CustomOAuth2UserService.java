package com.trippaw.user.oauth;

import com.trippaw.user.User;
import com.trippaw.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
        throws OAuth2AuthenticationException {

        OAuth2User user = super.loadUser(userRequest);

        System.out.println("=============== Google 사용자 정보 ==================");
        System.out.println("Provider: " + userRequest.getClientRegistration().getRegistrationId());
        System.out.println("Attributes: " +user.getAttributes());
        System.out.println("========================================");

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = (String) user.getAttributes().get("sub");
        String email = (String) user.getAttributes().get("email");
        String name = (String) user.getAttributes().get("name");

        Optional<User> existUser = userService.findByProviderAndProviderId(provider, providerId);

        if(existUser.isEmpty()) {

            User newUser = new User();
            newUser.setProvider(provider);
            newUser.setProviderId(providerId);
            newUser.setName(name);
            newUser.setEmail(email);

            userService.saveUser(newUser);
        }

        return user;
    }
}
