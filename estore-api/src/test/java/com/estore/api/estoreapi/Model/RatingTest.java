package com.estore.api.estoreapi.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class RatingTest {
     private Rating rating;
    /**
     * Before each test, create a new Product Object (this is the invoke part of the test)
     */
    @BeforeEach
    public void setProductDetails(){
            this.rating = new Rating(8, "rating 1","Rating2");
    }

    @Test
    public void testGetReview(){
        String review = "rating 1";
        assertEquals(review, this.rating.getReview());
    }

    @Test
    public void testGetUName(){
        String name = "Rating2";
        assertEquals(name, this.rating.getUsername());
    }

    @Test
    public void testsetraiting(){
        int rating = 9;
        this.rating.setRating(rating);
        assertEquals(rating, this.rating.getRatingInNumber());
    }
}
