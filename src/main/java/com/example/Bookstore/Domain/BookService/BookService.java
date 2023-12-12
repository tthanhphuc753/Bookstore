package com.example.Bookstore.Domain.BookService;

import com.example.Bookstore.Domain.Model.Book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findByName(String name);

    Optional<Book> findById(Long id);

    Book updateBook(Long id, Book newBook);

    void addCategories(long bookID, long categoriesID);

    List<Book> getAllBook();

    Book addBook(Book book);

    void removeBook(Long id);


    Page<Book> getAllBooks(Pageable pageable);
}
