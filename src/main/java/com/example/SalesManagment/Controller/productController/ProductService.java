package com.example.SalesManagment.Controller.productController;

import com.example.SalesManagment.Model.Product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getAllProduct();

    Optional<Product> findByName(String name);

    long countProduct();

    void addCategories(long productID, long categoriesID);

}
