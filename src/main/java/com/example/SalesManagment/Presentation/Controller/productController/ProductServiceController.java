package com.example.SalesManagment.Presentation.Controller.productController;

import com.example.SalesManagment.Domain.Model.Book.Book;
import com.example.SalesManagment.Domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductServiceController {
    private final ProductService productService;


    @GetMapping("list")
    public List<Book> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("count")
    public Long countProduct() {
        return productService.countProduct();
    }

    @PostMapping("add")
    public void addProduct(@RequestBody Book book) {
        productService.addProduct(book);
    }

}
