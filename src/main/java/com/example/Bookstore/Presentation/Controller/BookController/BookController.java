package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoriesService categoriesService;

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void deleteBook(Long id) {
        bookService.removeBook(id);

    }

    public void updateBook(Long id, Book newBook) {
        bookService.updateBook(id, newBook);
    }

    public void pageSize(Model model, int page, int size) {

        Page<Book> booksPage = bookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("books", booksPage.getContent());
    }

    public void showUpdateBookForm(Model model, Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        bookOptional.ifPresent(book -> model.addAttribute("book", book));
        model.addAttribute("category", categoriesService.getAllCategory());
    }

    public void showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("category", categoriesService.getAllCategory());
    }

}
