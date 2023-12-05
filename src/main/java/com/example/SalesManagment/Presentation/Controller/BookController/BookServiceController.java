package com.example.SalesManagment.Presentation.Controller.BookController;

import com.example.SalesManagment.Domain.BookService;
import com.example.SalesManagment.Domain.Model.Book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class BookServiceController {
    private final BookService bookService;


    @GetMapping("list")
    public List<Book> getAllProduct() {
        return bookService.getAllProduct();
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
