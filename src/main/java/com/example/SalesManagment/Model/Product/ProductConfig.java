package com.example.SalesManagment.Model.Product;

import com.example.SalesManagment.DAO.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductConfig {

    private final List<Product> productList = new ArrayList<>();

    @Bean(name = "ProductBean")
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product CleanLiquid = new Product(24, "Clean Liquid");
            productList.add(CleanLiquid);

            Product Bakery = new Product(100, "Bakery");
            productList.add(Bakery);

            Product Chocolate = new Product(10, "Chocolate");
            productList.add(Chocolate);

            Product Mops = new Product(15, "Mops");
            productList.add(Mops);

            //productRepository.saveAll(productList);
        };
    }
}
