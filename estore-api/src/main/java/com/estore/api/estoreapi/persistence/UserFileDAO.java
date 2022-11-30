package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserFileDAO  implements UserDAO{

    private String filename;
    Map<String,User> Users;
    private ObjectMapper objectMapper;

    @Autowired
    public UserFileDAO(@Value("${users.file}")String filename,ObjectMapper objectmapper) throws IOException{
            this.filename=filename;
            this.objectMapper = objectmapper;
            load(); 
    }

    private boolean load() throws IOException{
        try{
        Users = new TreeMap<>();
        User[] userArray = objectMapper.readValue(new File(this.filename), User[].class);
        for(User user: userArray){
            Users.put(user.getUsername(), user);
        }
        return true;
    }
    catch(Exception exception){
        return false;
    }
    }

    private boolean save() throws IOException {
        User[] ProductArray = getUsersArray();
        objectMapper.writeValue(new File(filename),ProductArray);
        return true;
    }
    
    private User[] getUsersArray(){
        ArrayList<User> productarraylist = new ArrayList<>();
        for (User product: Users.values()){
                productarraylist.add(product);
        }
        User[] productArray = new User[productarraylist.size()];
        productarraylist.toArray(productArray);
        return productArray;
    }


    @Override
    public User signup(User user) throws IOException {
            User newUser = new User(user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getCart());
            Users.put(newUser.getUsername(),newUser);
            save();
            return newUser;
    }

    @Override
    public User updateUser(User user) throws IOException {
        if(Users.containsKey(user.getUsername())){
            User actualUser= Users.get(user.getUsername());
            if(actualUser.getPhoneNumber()==user.getPhoneNumber() && actualUser.getFirstName()==user.getFirstName() && actualUser.getLastName()==user.getLastName()){
                Users.put(user.getUsername(),user);
                save();
                return user;
            }
            return null;
        }
        return null;
    }

    @Override
    public User loginUser(String username,String PhoneNumber, String Password) throws IOException {
        if(Users.containsKey(username)){
            User actual = Users.get(username);
            if(actual.getPassword()==Password && actual.getPhoneNumber().equals(PhoneNumber)){
                return actual;
            }
            return null;
        }
        return null;
    }

    @Override
    public User[] getUsers() {
        return getUsersArray();
    }
    
}
