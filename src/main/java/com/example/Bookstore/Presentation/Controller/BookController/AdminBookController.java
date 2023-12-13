package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService.AdminBookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Presentation.Controller.CategoriesController.ClientCategoriesServiceController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminBookController {
    private final AdminBookService adminBookService;
    private final ClientCategoriesServiceController clientCategoriesServiceController;

    public void addBook(Book book) {
        adminBookService.addBook(book);
    }

    public void deleteBook(Long id) {
        adminBookService.removeBook(id);

    }

    public void updateBook(Long id, Book newBook) {
        adminBookService.updateBook(id, newBook);
    }

    public void pageSize(Model model, int page, int size) {

        Page<Book> booksPage = adminBookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("books", booksPage.getContent());
    }

    public void showUpdateBookForm(Model model, Long id) {
        Optional<Book> bookOptional = adminBookService.findById(id);
        bookOptional.ifPresent(book -> model.addAttribute("book", book));
        model.addAttribute("category", clientCategoriesServiceController.getAllCategory());
    }

    public void showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("category", clientCategoriesServiceController.getAllCategory());
    }

    public Optional<Book> findById(Long bookId) {
        return adminBookService.findById(bookId);
    }

    public List<Book> searchBooks(String keyword) {

        return adminBookService.searchBooks(keyword);
    }
}
