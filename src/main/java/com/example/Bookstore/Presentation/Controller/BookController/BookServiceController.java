package com.example.Bookstore.Presentation.Controller.BookController;

import com.example.Bookstore.Domain.BookService;
import com.example.Bookstore.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookServiceController {
    private final BookService bookService;


    @GetMapping("list")
    public String getAllBook(Model model )
    {
       return bookService.getAllBook(model);
    }

    @GetMapping("count")
    public Long countProduct() {
        return bookService.countProduct();
    }

    @PostMapping("add")
    public void addProduct(@RequestBody Book book) {
        bookService.addProduct(book);
    }

}
