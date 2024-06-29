package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Provider;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    public void storeOauthPostLogin(String username, Provider provider)
    {
        Optional<User> existUser = userRepo.findUserByUsernameAndProvider(username,provider);
        if(existUser.isPresent() == false)
        {
            userRepo.save(new User(username,provider));
            //save user in data base
        }
    }

    
}
