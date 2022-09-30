package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Hero class
 * 
 * @author aagmanrelan
 */

@Tag("Model-tier")
public class ProductTest{
    
    private Product product;

    @BeforeEach
    public void setProductDetails(){
            this.product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
            false);
    }

    @Test
    public void testGetID(){
        int expected_id=99;
        assertEquals(expected_id, this.product.getID());
    }

    @Test
    public void testGetName(){
        String expected_name="Generic Test Product";
        assertEquals(expected_name, this.product.getName());
    }

    @Test
    public void testGetType(){
        String expected_type="Generic Test Type";
        assertEquals(expected_type, this.product.getType());
    }
    
    @Test
    public void testGetInstructor(){
        String expected_instructor="Generic Test Instructor";
        assertEquals(expected_instructor, this.product.getInstructor());
    }

    @Test
    public void testGetRoomNumber(){
        String expected_Room_Number = "Generic Test Room";
        assertEquals(expected_Room_Number, this.product.get_Room_Number());
    }

    @Test
    public void testAvailability(){
        boolean expected_availability = false;
        assertEquals(expected_availability, this.product.IsAvailable());
    }

    @Test
    public void testtoString(){
        String producString="Product(ID:99, Name:Generic Test Product, Type:Generic Test Type, Instructor:Generic Test Instructor, Room:Generic Test Room, Available:false)";
        assertEquals(producString, this.product.toString());
    }

}
