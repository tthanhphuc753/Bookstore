package com.example.SalesManagment.Presentation.Controller.productController;

import com.example.SalesManagment.Domain.Model.Book.Book;
import com.example.SalesManagment.Domain.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductServiceController {
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
