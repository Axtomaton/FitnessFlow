package com.estore.api.estoreapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.Model.User;
import com.estore.api.estoreapi.Model.UserLogin;
import com.estore.api.estoreapi.persistence.UserDAO;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;
    
    public UserController(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        LOG.info("POST /user/signup");
        try{
            User[] userlistexisting= userDAO.getUsers();
            
            for(User tmp:userlistexisting){
                    if(tmp.getUsername().equals(user.getUsername())){
                        return new ResponseEntity<>(user,HttpStatus.CONFLICT);
                    }
            }

            userDAO.signup(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        catch(Exception e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/login", consumes="application/json")
    @ResponseBody
    public ResponseEntity<User>login(@RequestBody UserLogin response){
        LOG.info("POST /user/login");
        try{
            User[] existingUsers = userDAO.getUsers();
            
            String givenusername=response.getUsername();
            String givenpassword = response.getPassword();
            long givenPhoneNumber = response.getPhoneNumber();


            for(User existinguser: existingUsers){
                if(existinguser.getUsername().equals(givenusername) && existinguser.getPhoneNumber()== givenPhoneNumber && existinguser.getPassword().equals(givenpassword)){
                    return new ResponseEntity<>(existinguser,HttpStatus.OK);
                }
                else if(existinguser.getUsername().equals(givenusername) && existinguser.getPhoneNumber()== givenPhoneNumber){
                        User updateduser= userDAO.updateUser(new User(givenusername,givenpassword,existinguser.getFirstName(),existinguser.getLastName(),givenPhoneNumber));
                        return new ResponseEntity<>(updateduser,HttpStatus.OK);
                }
            }
            return null;

        }
        catch(Exception e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
