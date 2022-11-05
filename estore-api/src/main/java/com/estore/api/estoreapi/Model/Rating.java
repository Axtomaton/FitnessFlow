package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {
    private int RatingInNumber;
    private String Review;
    private String Username;

    public Rating(@JsonProperty("RatingInNumber")int RatingInNumber,@JsonProperty("Review")String Review,@JsonProperty("Username") String Username){
        this.RatingInNumber=RatingInNumber;
        this.Review=Review;
        this.Username=Username;
    }

    @JsonProperty("RatingInNumber")
    public int getRatingInNumber(){
        return RatingInNumber;
    }

    @JsonProperty("Review")
    public String getReview(){
        return Review;
    }
    @JsonProperty("Username")
    public String getUsername(){
        return Username;
    }

    public void setRating(int rating){
        this.RatingInNumber=rating;
    }

}
