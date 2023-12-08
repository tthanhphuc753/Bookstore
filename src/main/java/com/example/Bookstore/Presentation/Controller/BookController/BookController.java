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



    @GetMapping("/homepage")
    public String showHomePage(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "homePage";
    }


}
