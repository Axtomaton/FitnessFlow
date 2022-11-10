package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.UserDataHandler;

import com.estore.api.estoreapi.Model.Product;
import com.estore.api.estoreapi.Model.Rating;
import com.estore.api.estoreapi.Model.User;
import com.estore.api.estoreapi.persistence.UserDAO;
import com.jayway.jsonpath.internal.Utils;

public class UserControllerTest {
    private static final User Null = null;
    private UserController uController;
    private UserDAO mUserDAO;
    private User user;
    private Product product;
    ArrayList<Integer> cart = new ArrayList<>();
    User[] testUsers;


    @BeforeEach
    public void setupProductController() throws IOException{
        mUserDAO = mock(UserDAO.class);
        uController = new UserController(mUserDAO);
        this.user = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);
        mUserDAO.signup(user);
        ArrayList<Rating> ratingArr = new ArrayList<>();
        this.product = new Product(99, "Generic 1", "Generic 2", "Generic 3", "Generic 4", false,123.76, ratingArr);
        testUsers = new User[1];
        testUsers[0] = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);



    }

    @Test
    public void testSignup () throws IOException{
        //this.user = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);
        User[] ulist = new User[1];
        ulist[0] = user;

        User user2 = new User("uname1", "pword1", "fname1", "lname1", "1111111111",this.cart);


        when(mUserDAO.getUsers()).thenReturn(ulist);
        //when(muser.createProduct(product)).thenReturn(product);
        ResponseEntity<User> response = uController.signup(user);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        ResponseEntity<User> response2 = uController.signup(user2);
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());

        ResponseEntity<User> response3 = uController.signup(Null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response3.getStatusCode());

    }

    @Test
    public void testUserLogin() throws IOException{
        User user1 = new User("uname", "pworddiff", "fname", "lname", "1111111111",this.cart);
        User user2 = new User("uname1", "pword1", "fname1", "lname1", "1111111111",this.cart);
        
        User[] ulist = new User[1];
        ulist[0] = user;
        when(mUserDAO.getUsers()).thenReturn(ulist);

        ResponseEntity<User> response = uController.login(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<User> response2 = uController.login(user1);
        assertEquals(HttpStatus.OK, response2.getStatusCode());

        ResponseEntity<User> response3 = uController.login(user2);
        assertNull(response3);

        ResponseEntity<User> response4 = uController.login(Null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response4.getStatusCode());
    }

    /**
     * DOES NOT WORK BECAUSE USERDAO IS EMPTY WHEN CALLING ADD TO CART
     * @throws IOException
     */
    @Test
    public void testaddtooCart() throws IOException{
        ArrayList<Integer> alist = new ArrayList<>();
        alist.add(99);
        //mUserDAO.getUsers();
        //mUserDAO.signup(user);
        uController.login(user);
        uController.addToCart("uname", 99);
        assertEquals(alist, this.user.getCart());
    }



}