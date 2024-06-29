package com.example.demo.restcontrollers;


import java.util.HashMap;
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
		//generally authenticated user is stored in principle object here it is in Oath2Use
		//it is not good idea to return a whole OAuth2User object 
		HashMap<String,Object> hashset = new HashMap<>();
		hashset.put("name", principle.getName());
		hashset.put("avatar_url", principle.getAttribute("avatar_url"));
		return hashset;
		
	}
    
}

