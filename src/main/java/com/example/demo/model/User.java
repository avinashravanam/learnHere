package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    private String username;
    
    @Enumerated(EnumType.STRING)
    private Provider provider;

    public User()
    {

    }

    public User(String username, Provider provider)
    {
        this.username = username;
        this.provider = provider;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Provider getProvider()
    {
        return this.provider;
    }

    public void setProvider(Provider provider)
    {
           this.provider = provider;
    }
 
}
