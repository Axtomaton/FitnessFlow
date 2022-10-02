package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.Model.Product;
import com.estore.api.estoreapi.persistence.ProductDAO;
import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * Handles the REST API requests for the Product resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Aagman Relan, Thomas Garcia, JianZhuang Jiang, Ivan Lin
 */

@RestController
@RequestMapping("admin")
public class ProductController {
    
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    private ProductDAO productDao;
    
    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param productDao The {@link ProductDAO Product Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public ProductController(ProductDAO productDao){
        this.productDao=productDao;
    }

    /**
     * Responds to the GET request for a {@linkplain Product product} for the given id
     * 
     * @param id The id used to locate the {@link Product product}
     * 
     * @return ResponseEntity with {@link Product product} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("product/{id}")
    public ResponseEntity<Product> GetSingleProduct(@PathVariable int id){
        LOG.info("GET /admin/product/ "+id);
        try{
                Product product = productDao.getProduct(id);
                if (product!=null){
                    return new ResponseEntity<Product>(product,HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
        catch(IOException e){
                LOG.log(Level.SEVERE, e.getLocalizedMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Product product} with the provided {@linkplain Product product} object, if it exists
     * 
     * @param newP The {@link Product product} to update
     * 
     * @return ResponseEntity with updated {@link Product product} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newP){
        LOG.info("PUT   /pruduct "+ newP);
        int id = newP.getID();
        try{
            if(productDao.getProduct(id) != null){
                productDao.updateProduct(newP);
                return new ResponseEntity<Product>(newP,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    /**
     * Responds to the GET request for all {@linkplain Product products} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Product products}
     * 
     * @return ResponseEntity with array of {@link Product product} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all products that contain the text "Product"
     * GET http://localhost:8080/admin/product/?name=Product
     */
    @GetMapping("product/")
    public ResponseEntity<Product[]> searchProduct(@RequestParam String name) {
        LOG.info("GET /admin/product/?Name="+name);

        try {
                Product[] products = productDao.findProduct(name);
                return new ResponseEntity<Product[]>(products, HttpStatus.OK);
        } 
        catch (IOException e) {
                LOG.log(Level.SEVERE,e.getLocalizedMessage());
                return new ResponseEntity<Product[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@link Product product} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        LOG.info("DELETE /admin/product/"+id);
        try{
            if (productDao.getProduct(id)!=null){
                productDao.deleteProduct(id);
                return new ResponseEntity<>("The requested product was successfully Deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("The Requested Product was not Found",HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Product products}
     * 
     * @return ResponseEntity with array of {@link Product product} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/products")
    public ResponseEntity<Product[]>getallInventory(){
        LOG.info("GET /admin/products");
        try{
                Product[] products= productDao.getProducts();
                return new ResponseEntity<Product[]>(products,HttpStatus.OK);
        }
        catch(IOException e){
                LOG.log(Level.SEVERE,e.getLocalizedMessage());
                return new ResponseEntity<Product[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Product product} with the provided product object
     * 
     * @param product - The {@link Product product} to create
     * 
     * @return ResponseEntity with created {@link Product product} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Product product} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("/product")
    public ResponseEntity<Product> CreateProduct(@RequestBody  Product product) {
        LOG.info("POST /admin/Product");
        try{
                Product[] existing = productDao.getProducts();
                for (Product productexisting:existing){
                    if (productexisting.getName().equals(product.getName()) || productexisting.getType().equals(product.getType())){
                        return new ResponseEntity<>(product,HttpStatus.CONFLICT);
                    }
                }
                productDao.createProduct(product);
                return new ResponseEntity<>(product,HttpStatus.CREATED);
        }
        catch(Exception e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
