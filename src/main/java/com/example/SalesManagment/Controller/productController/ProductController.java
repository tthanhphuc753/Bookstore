package com.example.SalesManagment.Controller.productController;

import com.example.SalesManagment.Model.Product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("list")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("count")
    public Long countProduct() {
        return productService.countProduct();
    }

    @PostMapping("add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

}
