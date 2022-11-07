package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.Model.Product;
import com.estore.api.estoreapi.Model.Rating;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test the Product File DAO class
 * 
 */

@Tag("Persistence-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;

    Product[] testProducts;

    ObjectMapper mockObjectMapper;
   
    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @author Aagman Relan
     * @throws IOException
     */
    @BeforeEach
    public void setupProductFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[3];

        //Rating rating = new Rating(8, "thing", "thing");
        ArrayList<Rating> arr = new ArrayList<>();
        //arr.add(rating);

        testProducts[0]=new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false,34.76, arr);
        testProducts[1]=new Product(998, "Generic Test Product 2", "Generic Test Type 2", "Generic Test Instructor 2", "Generic Test Room 2", 
        true,23.45, arr);
        testProducts[2]=new Product(997, "Generic Test Product 3", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false,98.09, arr);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the product array above
        when(mockObjectMapper.readValue(new File("TEST.txt"), Product[].class)).thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("TEST.txt", mockObjectMapper);
}
    /**
     * @author Aagman Relan
     * @throws IOException
     */
    @Test
    public void testGetProducts() throws IOException {
        // Invoke
        Product[] product = productFileDAO.getProducts();

        // Analyze
        assertEquals(product.length,testProducts.length);
    }
    /**
     * @author Ivan Lin
     * @throws IOException
     */
    @Test
    public void testFindProducts() throws IOException{
        // Invoke
        Product[] products = productFileDAO.findProduct("Generic");

        // Analyze
        assertEquals(products.length, 3);
    }
    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testGetProduct() throws IOException{
        Product product = productFileDAO.getProduct(99);
        assertEquals(testProducts[0], product);
    }
    /**
     * @author Thomas Garcia
     */
    @Test
    public void testDeleteProduct(){
        // Invoke
        boolean result = assertDoesNotThrow(()->productFileDAO.deleteProduct(99));

        // Analyzez
        assertEquals(result,true);

        // We check the internal tree map size against the length
        // of the test products array - 1 (because of the delete)
        // Because products attribute of ProductFileDAO is package private
        // we can access it directly
        assertEquals(productFileDAO.Products.size(), testProducts.length-1);
    }
    /**
     * @author Aagman Relan
     * @throws IOException
     */
    @Test
    public void testCreateProduct() throws IOException{
        // Setup
        Product product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
        "Generic Test Room", false,34.76, null);

        // Invoke
        Product result = assertDoesNotThrow(()->productFileDAO.createProduct(product));

        // Analyze
        assertNotNull(result);
        Product actual =productFileDAO.getProduct(99);
        assertEquals(actual.toString(), product.toString());
    }
    /**
     * @author Ivan Lin
     * @throws IOException
     */
    @Test
    public void testUpdateProduct() throws IOException{
        // Setup
        Product product = new Product(99,"New Name","New Type","New Instructor","0000F",true,12.56, null);

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product), "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getID());
        assertEquals(actual,product);
    }
    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Product[].class));

        Product product = new Product(99,"New Name","New Type","New Instructor","0000F",true,45.67, null);

        assertThrows(IOException.class,
                        () -> productFileDAO.createProduct(product),
                        "IOException not thrown");
    }
    /**
     * @author Thomas Garcia
     * @throws IOException
     */
    @Test
    public void testGetProductNotFound() throws IOException {
        // Invoke 
        Product product = productFileDAO.getProduct(98);

        // Analyze
        assertEquals(product,null);
    }
    /**
     * @author Aagman Relan
     */
    @Test
    public void testDeleteProductNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(98),"Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(productFileDAO.Products.size(),testProducts.length);
    }
    /**
     * @author Ivan Lin
     */
    @Test
    public void testUpdateProductNotFound() {
        // Setup
        Product product = new Product(96,"New Name","New Type","New Instructor","0000F",true,56.78, null);

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),"Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }
    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the ProductFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Product[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new ProductFileDAO("doesnt_matter.txt",mockObjectMapper), "IOException not thrown");
    }

    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testaddRating() throws IOException {
        Product product = new Product(99,"New Name","New Type","New Instructor","0000F",true,56.78, null);
        Product product2 = new Product(8585,"New Name","New Type","New Instructor","0000F",true,56.78, null);

        Rating rating = new Rating(8, "stuff", "things");
        ArrayList<Rating> arr = new ArrayList<>();
        arr.add(rating);
        //productFileDAO.addRating(rating, product);
        product.setRatings(arr);
        Product result = productFileDAO.addRating(rating,product);
        assertNotNull(result);
        assertEquals(product.getRatings(), result.getRatings());

        Product result2 = productFileDAO.addRating(rating,product2);

        assertNull(result2);
    }
}
