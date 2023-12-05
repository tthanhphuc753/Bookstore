package com.example.Bookstore.Domain;

import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public String getAllBook(Model model) {
        model.addAttribute("book",bookRepository.findAll());
        return "booklist";
    }
}
