package com.example.Bookstore.Domain;

import com.example.Bookstore.Domain.Model.Book.Book;
import org.springframework.ui.Model;

import java.util.Optional;

public interface BookService {
    void addProduct(Book book);

    Optional<Book> findByName(String name);

    long countProduct();

    void addCategories(long bookID, long categoriesID);

    String getAllBook(Model model);

}
