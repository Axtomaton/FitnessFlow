package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class ProductRatingTest {
     private ProductRating pRating;
     private Rating rating;
     private Product product;

    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     */
    @BeforeEach
    public void setProductDetails(){
            this.rating = new Rating(8, "rating 1","Rating2");
            this.product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room",false,123.56, null); 
            this.pRating = new ProductRating(product, rating);
    }

    @Test
    public void testGetProduct(){
        assertEquals(product, this.pRating.getProduct());
    }

    @Test
    public void testGetRating(){
        assertEquals(rating, this.pRating.getRating());
    }

}
