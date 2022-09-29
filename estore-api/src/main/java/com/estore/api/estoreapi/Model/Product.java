package com.estore.api.estoreapi.Model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    
    private static final Logger LOG = Logger.getLogger(Product.class.getName());


    private int id;

    private String Name;

    private String Type;
 
    private String Instructor;
    private String Room_Number;
    private boolean Available;


    @Override
    public String toString() {
        String Product="Product(ID:"+id+", Name:"+Name+", Type:"+Type+", Instructor:"+Instructor+", Room:"+Room_Number+", Available:"+Available+")";
        return Product;
    }

    public Product(@JsonProperty("id") int id, @JsonProperty("Name") String Name, @JsonProperty("Type") String Type, @JsonProperty("Instructor") String Instructor, 
                        @JsonProperty("Room_Number") String Room_Number, @JsonProperty("Available") boolean Available) {
        this.id = id;
        this.Name = Name;
        this.Type = Type;
        this.Instructor = Instructor;
        this.Room_Number= Room_Number;
        this.Available = Available;
    }

    @JsonProperty("id") 
    public int getID(){
        return id;
    }
    @JsonProperty("Type") 
    public String getType(){
        return Type;
    }
    @JsonProperty("Name") 
    public String getName(){
        return Name;
    }
    @JsonProperty("Instructor")
    public String getInstructor(){
        return Instructor;
    }
    @JsonProperty("Room_Number") 
    public String get_Room_Number(){
        return Room_Number;
    }
    @JsonProperty("Available") 
    public boolean IsAvailable(){
        return Available;
    }


    public void setName(String Name){
        this.Name = Name;
    }

    public void setInstructor(String Instructor){
        this.Instructor=Instructor;
    }

    public void setType(String Type){
        this.Type=Type;
    }

    public void set_Room_Number(String Room_Number){
        this.Room_Number=Room_Number;
    }
    public void setAvailability(boolean Available){
        this.Available=Available;
    }
}
