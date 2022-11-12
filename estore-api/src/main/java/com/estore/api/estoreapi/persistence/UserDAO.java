package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.Model.User;

public interface UserDAO {
    
    User signup(User user) throws IOException;
    User updateUser(User user) throws IOException;
    User loginUser(String username,String PhoneNumber, String Password) throws IOException;
    User[] getUsers() throws IOException;
}
