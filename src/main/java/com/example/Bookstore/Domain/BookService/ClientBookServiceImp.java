package com.example.Bookstore.Domain.BookService;

import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ClientBookServiceImp implements ClientBookService {
    private final BookRepository bookRepository;

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByNameContainingIgnoreCase(keyword);
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
