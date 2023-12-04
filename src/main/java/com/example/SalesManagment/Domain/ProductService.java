package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Domain.Model.Book.Book;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Book book);

    List<Book> getAllProduct();

    Optional<Book> findByName(String name);

    long countProduct();

    void addCategories(long productID, long categoriesID);

}
