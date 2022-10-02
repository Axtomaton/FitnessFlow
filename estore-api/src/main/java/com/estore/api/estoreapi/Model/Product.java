package com.estore.api.estoreapi.Model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Product entity
 * 
 * @author aagmanrelan
 */
public class Product {
    
    private static final Logger LOG = Logger.getLogger(Product.class.getName());


    private int id;

    private String Name;

    private String Type;
 
    private String Instructor;
    private String Room_Number;
    private boolean Available;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String Product="Product(ID:"+id+", Name:"+Name+", Type:"+Type+", Instructor:"+Instructor+", Room:"+Room_Number+", Available:"+Available+")";
        return Product;
    }

    /**
     * Create a product with the given id and name
     * @param id The id of the product
     * @param name The name of the product
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Product(@JsonProperty("id") int id, @JsonProperty("Name") String Name, @JsonProperty("Type") String Type, @JsonProperty("Instructor") String Instructor, 
                        @JsonProperty("Room_Number") String Room_Number, @JsonProperty("Available") boolean Available) {
        this.id = id;
        this.Name = Name;
        this.Type = Type;
        this.Instructor = Instructor;
        this.Room_Number= Room_Number;
        this.Available = Available;
    }

    /**
     * Retrieves the id of the product
     * @return The id of the product
     */
    @JsonProperty("id") 
    public int getID(){
        return id;
    }

    /**
     * Retrieves the type of the product
     * @return The type of the product
     */
    @JsonProperty("Type") 
    public String getType(){
        return Type;
    }

    /**
     * Retrieves the name of the product
     * @return The name of the product
     */
    @JsonProperty("Name") 
    public String getName(){
        return Name;
    }

    /**
     * Retrieves the instructor of the product
     * @return The instructor of the product
     */
    @JsonProperty("Instructor")
    public String getInstructor(){
        return Instructor;
    }

    /**
     * Retrieves the room number of the product
     * @return The room number of the product
     */
    @JsonProperty("Room_Number") 
    public String get_Room_Number(){
        return Room_Number;
    }

    /**
     * Retrieves the availibility (boolean) of the product
     * @return The boolean of the product
     */
    @JsonProperty("Available") 
    public boolean IsAvailable(){
        return Available;
    }

    /**
     * Sets the name of the product - necessary for JSON object to Java object deserialization
     * @param name The name of the product
     */
    public void setName(String Name){
        this.Name = Name;
    }

    /**
     * Sets the instructor of the product - necessary for JSON object to Java object deserialization
     * @param Instructor The name of the product
     */
    public void setInstructor(String Instructor){
        this.Instructor=Instructor;
    }

    /**
     * Sets the type of the product - necessary for JSON object to Java object deserialization
     * @param Type The name of the product
     */
    public void setType(String Type){
        this.Type=Type;
    }

    /**
     * Sets the room number of the product - necessary for JSON object to Java object deserialization
     * @param Room_Number The name of the product
     */
    public void set_Room_Number(String Room_Number){
        this.Room_Number=Room_Number;
    }

    /**
     * Sets the availability of the product - necessary for JSON object to Java object deserialization
     * @param Available The name of the product
     */
    public void setAvailability(boolean Available){
        this.Available=Available;
    }
}
