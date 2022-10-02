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
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test the Product File DAO class
 * 
 * @author Aagman Relan
 */

@Tag("Persistence-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;

    Product[] testProducts;

    ObjectMapper mockObjectMapper;
   
    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupProductFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[3];

        testProducts[0]=new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false);
        testProducts[1]=new Product(998, "Generic Test Product 2", "Generic Test Type 2", "Generic Test Instructor 2", "Generic Test Room 2", 
        true);
        testProducts[2]=new Product(997, "Generic Test Product 3", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the product array above
        when(mockObjectMapper.readValue(new File("TEST.txt"), Product[].class)).thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("TEST.txt", mockObjectMapper);
}

    @Test
    public void testGetProducts() throws IOException {
        // Invoke
        Product[] product = productFileDAO.getProducts();

        // Analyze
        assertEquals(product.length,testProducts.length);
    }

    @Test
    public void testFindProducts() throws IOException{
        // Invoke
        Product[] products = productFileDAO.findProduct("Generic");

        // Analyze
        assertEquals(products.length, 3);
    }

    @Test
    public void testGetProduct() throws IOException{
        Product product = productFileDAO.getProduct(99);
        assertEquals(testProducts[0], product);
    }

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

    @Test
    public void testCreateProduct() throws IOException{
        // Setup
        Product product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
        "Generic Test Room", false);

        // Invoke
        Product result = assertDoesNotThrow(()->productFileDAO.createProduct(product));

        // Analyze
        assertNotNull(result);
        Product actual =productFileDAO.getProduct(99);
        assertEquals(actual.toString(), product.toString());
    }
    @Test
    public void testUpdateProduct() throws IOException{
        // Setup
        Product product = new Product(99,"New Name","New Type","New Instructor","0000F",true);

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product), "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getID());
        assertEquals(actual,product);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Product[].class));

        Product product = new Product(99,"New Name","New Type","New Instructor","0000F",true);

        assertThrows(IOException.class,
                        () -> productFileDAO.createProduct(product),
                        "IOException not thrown");
    }
    @Test
    public void testGetProductNotFound() throws IOException {
        // Invoke 
        Product product = productFileDAO.getProduct(98);

        // Analyze
        assertEquals(product,null);
    }

    @Test
    public void testDeleteProductNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(98),"Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(productFileDAO.Products.size(),testProducts.length);
    }

    @Test
    public void testUpdateProductNotFound() {
        // Setup
        Product product = new Product(96,"New Name","New Type","New Instructor","0000F",true);

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),"Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

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
}
