package com.example.Bookstore.Domain.BookService;

import com.example.Bookstore.Domain.Model.Book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientBookService {
    Page<Book> getAllBooks(Pageable pageable);

    List<Book> getAllBook();

    List<Book> searchBooks(String keyword);

    Optional<Book> findById(Long id);

}
