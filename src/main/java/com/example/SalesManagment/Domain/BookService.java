package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Domain.Model.Book.Book;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void addProduct(Book book);

    Optional<Book> findByName(String name);

    long countProduct();

    void addCategories(long bookID, long categoriesID);

    String getAllBook(Model model);

}
