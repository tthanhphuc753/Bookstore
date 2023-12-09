package com.example.Bookstore.Presentation.Controller.AdminController;


import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class adminController {

    private final BookService bookService;
    @GetMapping("/homepage")
    public String showAdminPage(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "admin";
    }

    @GetMapping("/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.findById(id);
        bookOptional.ifPresent(book -> model.addAttribute("book", book));
        return "updatebook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book newBook) {
        bookService.updateBook(id, newBook);
        return "redirect:/book/list";
    }

    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }


    @PostMapping("add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/book/homepage";
    }

    @PostMapping("/books/delete/{id}")
    public String removeBook(@PathVariable Long id) {

        bookService.removeBook(id);
        return "redirect:/book/list";
    }

}
