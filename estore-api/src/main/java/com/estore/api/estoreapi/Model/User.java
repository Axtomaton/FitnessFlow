package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String FirstName;
    private String LastName;
    private long PhoneNumber;
    private String username;
    private String password;

    public User(@JsonProperty("Username") String username, @JsonProperty("Password") String password, @JsonProperty("FirstName") String FirstName, @JsonProperty("LastName") String LastName,
    @JsonProperty("phoneNumber")long PhoneNumber){
        this.FirstName=FirstName;
        this.LastName=LastName;
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
    public String getFirstName(){
        return this.FirstName;
    }
    public String getLastName(){
        return this.LastName;
    }
    public long getPhoneNumber(){
        return this.PhoneNumber;
    }
}
