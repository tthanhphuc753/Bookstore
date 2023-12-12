package com.example.Bookstore.Domain.BookService;

import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
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

    public Book updateBook(Long id, Book newBook) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            BeanUtils.copyProperties(newBook, existingBook, "id");
            return bookRepository.save(existingBook);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByNameContainingIgnoreCase(keyword);
    }
}
