package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Domain.Model.Book.Book;
import com.example.SalesManagment.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Override
    public void addProduct(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllProduct() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public long countProduct() {
        return bookRepository.count();
    }

    @Override
    public void addCategories(long bookID, long categoriesID) {

    }
}
