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
public class UserTest {
    private User user;
    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     */
    @BeforeEach
    public void setProductDetails(){
    ArrayList<Integer> cart = new ArrayList<>();

    this.user = new User("uname", "pword", "fname", "lname", "1111111111",cart);
    } 
      
    @Test
    public void testGetUName() throws IOException{
    String uname ="uname";
    assertEquals(uname, this.user.getUsername());
    }

    @Test
    public void testGetpassword() throws IOException{
    String pword ="pword";
    assertEquals(pword, this.user.getPassword());
    }

    @Test
    public void testGetfname() throws IOException{
    String fname ="fname";
    assertEquals(fname, this.user.getFirstName());
    }

    @Test
    public void testGetLName() throws IOException{
    String lname ="lname";
    assertEquals(lname, this.user.getLastName());
    }

    @Test
    public void testGetPNum() throws IOException{
    String pnum ="1111111111";
    assertEquals(pnum, this.user.getPhoneNumber());
    }

    @Test
    public void testGetCart() throws IOException{
    ArrayList<Integer> cart = new ArrayList<>();

    assertEquals(cart, this.user.getCart());
    }
}
