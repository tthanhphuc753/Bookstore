package com.example.Bookstore.Domain;

import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;


    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }


    @Override
    public void addCategories(long bookID, long categoriesID) {

    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }
}
