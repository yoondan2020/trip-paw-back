package com.trippaw.user.oauth;

import com.trippaw.security.JwtTokenProvider;
import com.trippaw.user.User;
import com.trippaw.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    public  OAuth2AuthenticationSuccessHandler(UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("Authentication: " + authentication);
        System.out.println("Principal: " + authentication.getPrincipal());

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        String provider = oauthToken.getAuthorizedClientRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");

        Optional<User> user = userService.findByProviderAndProviderId(provider, providerId);

        if(!user.isEmpty()){
            User tripPawUser = user.get();
            System.out.println("TripPaw User ID: " + tripPawUser.getId());

            String jwt = jwtTokenProvider.createToken(tripPawUser.getId());
            System.out.println("jwt: " + jwt);

            response.getWriter().write(jwt);
        }


    }
}
