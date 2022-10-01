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
 * @author aagmanrelan
 */

@Tag("Controller-tier")
public class ProductControllerTest {
    private ProductController productController;

    private ProductDAO mockproductDAO;

    @BeforeEach
    public void setupProductController(){
        mockproductDAO = mock(ProductDAO.class);
        productController = new ProductController(mockproductDAO);
    }

    @Test
    public void testGetSingleProduct() throws IOException{

        Product product = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", "Generic Test Room", 
        false);

        when(mockproductDAO.getProduct(product.getID())).thenReturn(product);

        ResponseEntity<Product> response = productController.GetSingleProduct(product.getID());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testSearchProducts() throws IOException{
        Product[] products = new Product[2];

        products[0] = new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
        "Generic Test Room", false);
        products[1] = new Product(998, "Test Product", " est Type", "Generic Test Instructor", 
        "Generic Test Room", true);
        String name="odu";
        when(mockproductDAO.findProduct(name)).thenReturn(products);

        ResponseEntity<Product[]> response = productController.searchProduct(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products.length, response.getBody().length);
    }

    @Test
    public void testDeleteProduct() throws IOException{
        when(mockproductDAO.getProduct(0)).thenReturn(new Product(99, "Generic Test Product", "Generic Test Type", "Generic Test Instructor", 
                   "Generic Test Room", false));
        when(mockproductDAO.deleteProduct(0)).thenReturn(true);
        ResponseEntity<String>  response= productController.deleteProduct(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The requested product was successfully Deleted", response.getBody());

    }

    @Test
    public void testDeleteProductError() throws IOException{
        doThrow(new IOException()).when(mockproductDAO).getProduct(0);
        ResponseEntity<String> response = productController.deleteProduct(0);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetInventory() throws IOException{
        Product[] products = new Product[5];
        for(int i=0;i<5;i++){
            products[i] = new Product(i, "Generic", "Generic", "Generic", "Generic", false);
        }
        when(mockproductDAO.getProducts()).thenReturn(products);

        ResponseEntity<Product[]> response =productController.getallInventory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        
    }

}
