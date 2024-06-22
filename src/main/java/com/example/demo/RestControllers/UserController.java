package com.example.demo.RestControllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principle)
	{
		
		//it is not good idea to return a whole OAuth2User object 
		return Collections.singletonMap("name", principle.getAttribute("name"));
	}
    
}

