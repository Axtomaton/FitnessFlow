package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import com.estore.api.estoreapi.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFileDaoTest {
    private static final User Null = null;
    UserFileDAO uFileDao;
    User[] testUsers;
    ObjectMapper mockObjectMapper;
    User user;
    ArrayList<Integer> cart = new ArrayList<>();


    @BeforeEach
    public void setupUserFileDao() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[3];

        testUsers[0] = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);
        testUsers[1] = new User("uname2", "pword2", "fname1", "lname1", "1111111111",this.cart);
        testUsers[2] = new User("uname3", "pword3", "fname2", "lname2", "1111111111",this.cart);

        when(mockObjectMapper.readValue(new File("Test.txt"), User[].class)).thenReturn(testUsers);
        uFileDao = new UserFileDAO("Test.txt", mockObjectMapper);
    }

    @Test
    public void testSignUp() throws IOException{
        user = new User("uname7", "pword7", "fname7", "lname7", "1111111111",this.cart);
        User user2 = uFileDao.signup(user);
        assertEquals(user.getUsername(), user2.getUsername());
        assertEquals(user.getFirstName(), user2.getFirstName());
        assertEquals(user.getLastName(), user2.getLastName());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getPhoneNumber(), user2.getPhoneNumber());
    }

    @Test
    public void testUpdateUser() throws IOException{
        user = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);
        User user2 = new User("uname7", "pword7", "fname7", "lname7", "1111111111",this.cart);

        User user3 = uFileDao.updateUser(user);
        assertEquals(user, user3);

        User user4 = uFileDao.updateUser(user2);
        assertNull(user4);

        User user6 = new User("uname", "pword", "fname1", "lname1", "1111111111",this.cart);
        User user5 = uFileDao.updateUser(user6);
        assertNull(user5);
        
    }

    @Test
    public void testLoginUser() throws IOException{
        user = new User("uname", "pword", "fname", "lname", "1111111111",this.cart);
        User returnedUser = uFileDao.loginUser("uname", "1111111111", "pword");
        assertEquals("uname", returnedUser.getUsername());
        assertEquals("pword", returnedUser.getPassword());
        assertEquals("1111111111", returnedUser.getPhoneNumber());

        User returnedUser2 = uFileDao.loginUser("uname", "1111111111", "pword123");
        assertNull(returnedUser2);

        User returnedUser3 = uFileDao.loginUser("uname123", "1111111111", "pword");
        assertNull(returnedUser3);
    }

    @Test
    public void testgetUser(){
        User[] returnedlist = uFileDao.getUsers();
        for(int i = 0; i < testUsers.length; i++)
        assertEquals(testUsers[i],returnedlist[i]);

    }
}
