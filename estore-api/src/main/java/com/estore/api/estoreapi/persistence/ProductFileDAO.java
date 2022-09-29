package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductFileDAO implements ProductDAO{

    private static final Logger LOG = Logger.getLogger(ProductFileDAO.class.getName());

    Map<Integer,Product> Products;

    private ObjectMapper objectMapper;  

    private static int nextId;  
    private String filename;    

    @Autowired
    public ProductFileDAO(@Value("${products.file}")String filename,ObjectMapper objectmapper) throws IOException{
            this.filename=filename;
            this.objectMapper = objectmapper;
            load();
    }

    private boolean load() throws IOException {
        Products = new TreeMap<>();
        nextId = 0;

        Product[] productArray = objectMapper.readValue(new File(filename),Product[].class);
        for (Product product : productArray) {
            Products.put(product.getID(),product);
            if (product.getID() > nextId)
                nextId = product.getID();
        }
        ++nextId;
        return true;
    }

    private boolean save() throws IOException {
        Product[] ProductArray = getProductsArray();


        objectMapper.writeValue(new File(filename),ProductArray);
        return true;
    }
    

    private Product[] getProductsArray(){
        return getProductsArray(null);
    }

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


    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    
    @Override
    public Product[] getProducts() throws IOException {
            synchronized(Products){
                return getProductsArray();
            }
    }

    @Override
    public Product[] findProduct(String name) throws IOException {
        synchronized(Products){
            return getProductsArray(name);
        }
    }

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


    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized(Products){
            Product newProduct = new Product(nextId, product.getName(), product.getType(), product.getInstructor(), product.get_Room_Number(), product.IsAvailable());
            Products.put(newProduct.getID(),newProduct);
            save();
            return newProduct;
        }
    }

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
}
