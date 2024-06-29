package com.example.demo.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.model.Provider;


public class CustomOAuth2User  implements OAuth2User, CustomUser {

    private OAuth2User oAuth2User;

    public CustomOAuth2User(OAuth2User oAuth2User)
    {   
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
       
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
      
        return oAuth2User.getAttribute("login");   //not all accouts having name attibute some having null
    }

    @Override
    public Provider getProvider() {

         return Provider.GITHUB;
    }
    
    
}
