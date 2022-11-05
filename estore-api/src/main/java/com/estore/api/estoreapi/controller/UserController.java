package com.estore.api.estoreapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.Model.User;
import com.estore.api.estoreapi.Model.UserLogin;
import com.estore.api.estoreapi.persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
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
            String givenPhoneNumber = response.getPhoneNumber();
            
            for(User existinguser: existingUsers){
                if(existinguser.getUsername().equals(givenusername) && existinguser.getPhoneNumber().equals(givenPhoneNumber) && existinguser.getPassword().equals(givenpassword)){
                    System.out.println("Returning actual user");
                    return new ResponseEntity<>(existinguser,HttpStatus.OK);
                }
                else if(existinguser.getUsername().equals(givenusername) && existinguser.getPhoneNumber().equals(givenPhoneNumber)){
                        User updateduser= userDAO.updateUser(new User(givenusername,givenpassword,existinguser.getFirstName(),existinguser.getLastName(),givenPhoneNumber,
                        existinguser.getCart()));
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
    

    @GetMapping("/addToCart")
    @ResponseBody
    public void addToCart(@RequestParam("Username") String username, @RequestParam("ProductID") int ProductID) throws IOException{
        LOG.info("POST /user/addToCart");
        User[] users= userDAO.getUsers();
        for(User tmp: users){
            if(tmp.getUsername().equals(username)){
                tmp.getCart().add(ProductID);
                userDAO.updateUser(tmp);
            }
        }

    }

    @GetMapping("/removeFromCart")
    @ResponseBody
    public void removeFromCart(@RequestParam("Username") String username, @RequestParam("ProductID") int ProductID) throws IOException {
        User [] users=userDAO.getUsers();
        for(User tmp: users){
            if(tmp.getUsername().equals(username)){
                tmp.getCart().remove(Integer.valueOf(ProductID));
                userDAO.updateUser(tmp);
            }
        }

    }
}
