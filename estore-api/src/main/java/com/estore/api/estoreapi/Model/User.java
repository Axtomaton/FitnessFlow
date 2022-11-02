package com.estore.api.estoreapi.Model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String FirstName;
    private String LastName;
    private long PhoneNumber;
    private String username;
    private String password;
    private ArrayList<Integer> Cart;

    public User(@JsonProperty("Username") String username, @JsonProperty("Password") String password, @JsonProperty("firstName") String FirstName, @JsonProperty("lastName") String LastName,
    @JsonProperty("phoneNumber")long PhoneNumber, @JsonProperty("Cart")ArrayList<Integer>Cart){
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.PhoneNumber=PhoneNumber;
        this.username = username;
        this.password = password;
        this.Cart = Cart;
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
    public ArrayList<Integer> getCart(){
        return this.Cart;
    }
}
