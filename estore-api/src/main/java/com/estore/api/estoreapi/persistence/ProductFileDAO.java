package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ldap.LdapProperties.Template;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.Model.Product;
import com.estore.api.estoreapi.Model.Rating;
import com.estore.api.estoreapi.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionality for JSON file-based peristance for Products
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author aagmanrelan
 */
@Component
public class ProductFileDAO implements ProductDAO{

    private static final Logger LOG = Logger.getLogger(ProductFileDAO.class.getName());

    Map<Integer,Product> Products;  // Provides a local cache of the product objects
                                    // so that we don't need to read from the file
                                    // each time

    private ObjectMapper objectMapper;  // Provides conversion between Product
                                        // objects and JSON text format written
                                        // to the file

    private static int nextId;  // The next Id to assign to a new product
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Product File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    @Autowired
    public ProductFileDAO(@Value("${products.file}")String filename,ObjectMapper objectmapper) throws IOException{
            this.filename=filename;
            this.objectMapper = objectmapper;
            load(); // load the products from the file
    }

    /**
     * Loads {@linkplain Product products} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        Products = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of products
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Product[] productArray = objectMapper.readValue(new File(filename),Product[].class);

        // Add each product to the tree map and keep track of the greatest id
        for (Product product : productArray) {
            Products.put(product.getID(),product);
            if (product.getID() > nextId)
                nextId = product.getID();
        }

        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * Saves the {@linkplain Product products} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Product products} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Product[] ProductArray = getProductsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),ProductArray);
        return true;
    }
    
    /**
     * Generates an array of {@linkplain Product products} from the tree map
     * 
     * @return  The array of {@link Product products}, may be empty
     */
    private Product[] getProductsArray(){
        return getProductsArray(null);
    }

    /**
     * Generates an array of {@linkplain Product products} from the tree map for any
     * {@linkplain Product products} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Product products}
     * in the tree map
     * 
     * @return  The array of {@link Product products}, may be empty
     */
    private Product[] getProductsArray(String Name){
        ArrayList<Product> productarraylist = new ArrayList<>();
        for (Product product: Products.values()){
            if(Name==null || product.getName().contains(Name)){
                productarraylist.add(product);
            }
        }
        Product[] productArray = new Product[productarraylist.size()];
        productarraylist.toArray(productArray);
        return productArray;
    }

    /**
     * Generates the next id for a new {@linkplain Product product}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Product[] getProducts() throws IOException {
            synchronized(Products){
                return getProductsArray();
            }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Product[] findProduct(String name) throws IOException {
        synchronized(Products){
            return getProductsArray(name);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Product getProduct(int id) throws IOException {
        synchronized(Products){
            if(Products.containsKey(id)){
                return Products.get(id);
            }
            else {
                return null;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized(Products){
            Product newProduct = new Product(nextId(), product.getName(), product.getType(), product.getInstructor(), product.get_Room_Number(), product.IsAvailable(),product.getPrice(),product.getRatings());
            Products.put(newProduct.getID(),newProduct);
            save();
            return newProduct;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Product updateProduct(Product product) throws IOException {
        synchronized(Products){
            if(Products.containsKey(product.getID())==false){
                return null;
            }
            else{
                Products.put(product.getID(),product);
                save();
                return product;
            }
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteProduct(int id) throws IOException {
        synchronized(Products){

            if(Products.containsKey(id)==false){
                return false;
            }
            else{
                Products.remove(id);
                return save();
            }

        }
    }

    @Override
    public Product addRating(Rating rating, Product product) throws IOException {
        synchronized(Products){
            if(Products.containsKey(product.getID())==true){
                Product tmp=Products.get(product.getID());
                tmp.getRatings().add(rating);
                Products.put(product.getID(),tmp);
                save();
                return tmp;
            }else{
                return null;
            }
        }
    }
}
