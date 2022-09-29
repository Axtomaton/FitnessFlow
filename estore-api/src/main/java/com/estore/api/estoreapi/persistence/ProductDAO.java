package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.Model.Product;
/**
 * @author aagmanrelan
 */
public interface ProductDAO {
    Product[] getProducts() throws IOException;


    Product[] findProduct(String name) throws IOException;

    Product getProduct(int id) throws IOException;


    Product createProduct(Product product) throws IOException;


    Product updateProduct(Product product) throws IOException;


    boolean deleteProduct(int id) throws IOException;
}
