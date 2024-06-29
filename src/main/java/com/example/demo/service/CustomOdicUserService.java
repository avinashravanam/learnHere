package com.example.demo.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.example.demo.utils.GoogleOAuth2User;

@Service
public class CustomOdicUserService extends OidcUserService {

    @Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    
        OidcUser oidcuser = super.loadUser(userRequest);
        return new GoogleOAuth2User(oidcuser);  //change to custom 
    }

    
    
    
}
