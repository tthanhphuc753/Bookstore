package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookServiceController {
    private final BookService bookService;


    @GetMapping("list")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "booklist";
    }

    @GetMapping("/add-form")
    public String showAddForm(Model model)
    {
        model.addAttribute("book",new Book());
        return "addbook";
    }


    @PostMapping("add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/book/list";
    }

    @PostMapping("remove")
    public String removeBook(@ModelAttribute("bookId") Long id) {
        bookService.removeBook(id);
        return "redirect:/book/list";
    }


}
