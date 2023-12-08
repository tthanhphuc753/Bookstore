package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("list")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "booklist";
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
        return "redirect:list";
    }

    @GetMapping("/homepage")
    public String showHomePage(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "homePage";
    }

    @GetMapping("/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.findById(id);
        bookOptional.ifPresent(book -> model.addAttribute("book", book));
        return "book/updatebook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book newBook) {
        bookService.updateBook(id, newBook);
        return "redirect:/book/list";
    }


}
