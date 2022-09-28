package com.estore.api.estoreapi.Model;

import java.util.logging.Logger;

import javax.print.attribute.standard.MediaSize.NA;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    
    private static final Logger LOG = Logger.getLogger(Product.class.getName());


    @JsonProperty("id") private int id;         //transaction ID
    @JsonProperty("Type") private String Type;  //online or in-person
    @JsonProperty("Name") private String Name;  //person name
    @JsonProperty("Instructor") private String Instructor;  //instructor name
    @JsonProperty("Room_Number") private String Room_Number;    //room number
    @JsonProperty("Available") private boolean Available;       //true unless it overllaps with other times


    @Override
    public String toString() {
        String Product="Product( "+id+" "+Name+" "+Type+" "+Instructor+" "+Room_Number+" "+Available+" )";
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

    public int getID(){
        return id;
    }

    public String getType(){
        return Type;
    }

    public String getName(){
        return Name;
    }

    public String getInstructor(){
        return Instructor;
    }

    public String get_Room_Number(){
        return get_Room_Number();
    }

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
