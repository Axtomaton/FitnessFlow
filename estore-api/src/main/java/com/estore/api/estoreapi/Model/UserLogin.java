package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogin {

    private long PhoneNumber;
    private String username;
    private String password;

    public UserLogin(@JsonProperty("Username") String username, @JsonProperty("Password") String password, @JsonProperty("phoneNumber")long PhoneNumber){
        this.PhoneNumber=PhoneNumber;
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public long getPhoneNumber(){
        return this.PhoneNumber;
    }
}
