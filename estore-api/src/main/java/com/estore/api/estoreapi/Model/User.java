package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    private String password;

    public User(@JsonProperty("Username") String name, @JsonProperty("Password") String password){
        this.username = name;
        this.password = password;
    }

    public String getName(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
