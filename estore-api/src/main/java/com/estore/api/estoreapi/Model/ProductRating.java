package com.estore.api.estoreapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRating {
    private Product product;
    private Rating rating;

    public ProductRating(@JsonProperty("Product")Product product, @JsonProperty("Ratings")Rating rating){
        this.product=product;
        this.rating=rating;
    }
    @JsonProperty("Product")
    public Product getProduct(){
        return product;
    }
    @JsonProperty("Ratings")
    public Rating getRating(){
        return rating;
    }
}
