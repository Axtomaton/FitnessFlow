package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.Model.Product;
import com.estore.api.estoreapi.persistence.ProductDAO;


/**
 * 
 * Test the Product Controller class
 * 
 */

@Tag("Controller-tier")
public class ProductControllerTest {
    private ProductController productController;

    private ProductDAO mockproductDAO;

    /**
     * Before each test, create a new ProductController object and inject
     * a mock Product DAO
     * @author Aagman Relan
     */
    @BeforeEach
    public void setupProductController(){
        mockproductDAO = mock(ProductDAO.class);
        productController = new ProductController(mockproductDAO);
    }
    /**
     * @author Aagman Relan
     * @throws IOException
     */

    @Test
    public void testGetSingleProduct() throws IOException{ // testGetSingleProduct may throw IOException

        // setup
        Product product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false);

        // When the same id is passed in, our mock Product DAO will return the Product object
        when(mockproductDAO.getProduct(product.getID())).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.GetSingleProduct(product.getID());

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }
    /**
     * @author Ivan Lin
     * @throws IOException
     */

    @Test
    public void testSearchProducts() throws IOException{ // findProduct may throw IOException

        // Setup
        Product[] products = new Product[2];
        products[0] = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
        "Generic Test Room", false);
        products[1] = new Product(998, "Test Product", " est Type", "Generic Test Instructor", 
        "Generic Test Room", true);
        String name="odu";

        // When findProduct is called with the search string, return the two
        /// products above
        when(mockproductDAO.findProduct(name)).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProduct(name);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products.length, response.getBody().length);
    }
    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testDeleteProduct() throws IOException{ // deleteProduct may throw IOException

        // when deleteProduct is called return true, simulating successful deletion
        when(mockproductDAO.getProduct(0)).thenReturn(new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
                   "Generic Test Room", false));
        when(mockproductDAO.deleteProduct(0)).thenReturn(true);

        // Invoke
        ResponseEntity<String>  response= productController.deleteProduct(0);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The requested product was successfully Deleted", response.getBody());

    }
    /**
     * @author Thomas Garcia
     * @throws IOException
     */
    @Test
    public void testDeleteProductError() throws IOException{ // deleteProduct may throw IOException

        // when deleteProduct is called return false, simulating failed deletion
        doThrow(new IOException()).when(mockproductDAO).getProduct(0);

        // Invoke
        ResponseEntity<String> response = productController.deleteProduct(0);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    /**
     * @author Aagman Relan
     * @throws IOException
     */
    @Test
    public void testGetInventory() throws IOException{ // getInventory may throw IOException
        // Setup
        Product[] products = new Product[5];
        for(int i=0;i<5;i++){
            products[i] = new Product(i, "Generic", "Generic", "Generic", "Generic", false);
        }

        // When getInventory is called return the products created above
        when(mockproductDAO.getProducts()).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response =productController.getallInventory();

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        
    }
    /**
     * @author Ivan Lin
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException{ // updateProduct may throw IOException
        // Setup
        Product product = new Product(99, "Generic", "Generic", "Generic", "Generic", false);

        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockproductDAO.getProduct(99)).thenReturn(product);
        when(mockproductDAO.updateProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    
    }
    /**
     * @author JianZhuang Jiang
     * @throws IOException
     */
    @Test
    public void testCreateProduct() throws IOException {    // createHero may throw IOException
        // Setup
        Product product = new Product(99, "Generic 1", "Generic 2", "Generic 3", "Generic 4", false);
        Product[] arr = new Product[0];

        // when createHero is called, return true simulating successful
        // creation and save
        when(mockproductDAO.getProducts()).thenReturn(arr);
        when(mockproductDAO.createProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.CreateProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }


}
