package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogin {

    private String PhoneNumber;
    private String username;
    private String password;

    public UserLogin(@JsonProperty("Username") String username, @JsonProperty("Password") String password, @JsonProperty("phoneNumber")String PhoneNumber){
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
    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
}
