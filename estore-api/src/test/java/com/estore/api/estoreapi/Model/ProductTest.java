package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 * 
 * @author aagmanrelan
 */

@Tag("Model-tier")
public class ProductTest{
    
    private Product product;

    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     */
    @BeforeEach
    public void setProductDetails(){
            this.product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
            false);
    }

    @Test
    public void testGetID(){
        // Setup
        int expected_id=99;

        // Analyze
        assertEquals(expected_id, this.product.getID());
    }

    @Test
    public void testGetName(){
        // Setup
        String expected_name="Generic Test Product";

        // Analyze
        assertEquals(expected_name, this.product.getName());
    }

    @Test
    public void testGetType(){
        // Setup
        String expected_type="Generic Test Type";

        // Analyze
        assertEquals(expected_type, this.product.getType());
    }
    
    @Test
    public void testGetInstructor(){
        // Setup
        String expected_instructor="Generic Test Instructor";

        // Analyze
        assertEquals(expected_instructor, this.product.getInstructor());
    }

    @Test
    public void testGetRoomNumber(){
        // Setup
        String expected_Room_Number = "Generic Test Room";

        // Analyze
        assertEquals(expected_Room_Number, this.product.get_Room_Number());
    }

    @Test
    public void testAvailability(){
        // Setup
        boolean expected_availability = false;

        // Analyze
        assertEquals(expected_availability, this.product.IsAvailable());
    }

    @Test
    public void testtoString(){
        // Setup
        String producString="Product(ID:99, Name:Generic Test Product, Type:Generic Test Type, Instructor:Generic Test Instructor, Room:Generic Test Room, Available:false)";

        // Analyze
        assertEquals(producString, this.product.toString());
    }

}
