package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 * 
 */

@Tag("Model-tier")
public class ProductTest{
    
    private Product product;

    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     * @author Aagman Relan
     */
    @BeforeEach
    public void setProductDetails(){
            this.product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
            false,123.56, null);
    }
    /**
     * @author Ivan Lin
     */
    @Test
    public void testGetID(){
        // Setup
        int expected_id=99;

        // Analyze
        assertEquals(expected_id, this.product.getID());
    }
    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testGetName(){
        // Setup
        String expected_name="Generic Test Product";

        // Analyze
        assertEquals(expected_name, this.product.getName());
    }
    /**
     * @author Thomas Garcia
     */
    @Test
    public void testGetType(){
        // Setup
        String expected_type="Generic Test Type";

        // Analyze
        assertEquals(expected_type, this.product.getType());
    }
    
    /**
     * @author Aagman Relan
     */
    @Test
    public void testGetInstructor(){
        // Setup
        String expected_instructor="Generic Test Instructor";

        // Analyze
        assertEquals(expected_instructor, this.product.getInstructor());
    }
    /**
     * @author Ivan Lin
     */
    @Test
    public void testGetRoomNumber(){
        // Setup
        String expected_Room_Number = "Generic Test Room";

        // Analyze
        assertEquals(expected_Room_Number, this.product.get_Room_Number());
    }
    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testAvailability(){
        // Setup
        boolean expected_availability = false;

        // Analyze
        assertEquals(expected_availability, this.product.IsAvailable());
    }

    /**
     * @author Thomas Garcia
     */
    @Test
    public void testtoString(){
        // Setup
        String producString="Product(ID:99, Name:Generic Test Product, Type:Generic Test Type, Instructor:Generic Test Instructor, Room:Generic Test Room, Available:false, Price:123.56)";

        // Analyze
        assertEquals(producString, this.product.toString());
    }
    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetName(){
        // Setup
        String name="Generic Test Product";
        product.setName(name);
        // Analyze
        assertEquals(name, this.product.getName());
    }

     /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetinstructor(){
        // Setup
        String instructor ="Instructor A";
        product.setInstructor(instructor);
        // Analyze
        assertEquals(instructor, this.product.getInstructor());
    }

     /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetroom(){
        
        String room ="Room name number";
        product.set_Room_Number(room);
        
        assertEquals(room, this.product.get_Room_Number());
    }

    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetavail(){
        
        boolean avail =false;
        product.setAvailability(avail);
        
        assertEquals(avail, this.product.IsAvailable());

    }

    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetprice(){
        
        double price = 123.98;
        product.setPrice(price);
        
        assertEquals(price, this.product.getPrice());

    }

    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsettype(){
        
        String type = "typing";
        product.setType(type);
        
        assertEquals(type, this.product.getType());

    }

    /**
     * @author JianZhuang Jiang
     */
    @Test
    public void testsetraiting(){
        ArrayList<Rating> rating = new ArrayList<>();
        Rating rate1 = new Rating(8,"review 1", "name1");
        
        rating.add(rate1);

        product.setRatings(rating);

        assertEquals(rating,this.product.getRatings());
        
        
    }
}
