package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.ignoreStubs;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author JianZhuang Jiang
 */
public class UserLoginTest {
    private UserLogin ulogin; 
    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     */
    @BeforeEach
    public void setUloginDetails(){
    
    this.ulogin = new UserLogin("uname","pword","1111111111");
    } 
      
    @Test
    public void testGetUName() throws IOException{
    String uname ="uname";
    assertEquals(uname, this.ulogin.getUsername());
    }

    @Test
    public void testGetpassword() throws IOException{
    String pword ="pword";
    assertEquals(pword, this.ulogin.getPassword());
    }

    @Test
    public void testGetPNum() throws IOException{
    String pnum ="1111111111";
    assertEquals(pnum, this.ulogin.getPhoneNumber());
    }
}
