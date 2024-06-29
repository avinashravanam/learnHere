package com.example.demo.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.example.demo.model.Provider;

public class GoogleOAuth2User implements OidcUser, CustomUser{

    private OidcUser oidcUser;

    public GoogleOAuth2User(OidcUser oidcUser)
    {
        this.oidcUser = oidcUser;
    }
    @Override
    public Map<String, Object> getAttributes() {

        return this.oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.oidcUser.getAuthorities();
    }

    @Override
    public String getName() {

        return this.oidcUser.getAttribute("email");
    }

    @Override
    public Map<String, Object> getClaims() {
       return this.oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {

        return this.oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        
        return this.oidcUser.getIdToken();
    }

    @Override
    public Provider getProvider() {
        return Provider.GOOGLE;
    }

   



  
    
}
