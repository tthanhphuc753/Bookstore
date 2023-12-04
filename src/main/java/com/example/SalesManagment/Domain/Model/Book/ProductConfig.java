package com.example.SalesManagment.Domain.Model.Book;

import com.example.SalesManagment.Persistence.DAO.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductConfig {

    private final List<Book> bookList = new ArrayList<>();

    @Bean(name = "ProductBean")
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Book CleanLiquid = new Book(24, "Clean Liquid");
            bookList.add(CleanLiquid);

            Book Bakery = new Book(100, "Bakery");
            bookList.add(Bakery);

            Book Chocolate = new Book(10, "Chocolate");
            bookList.add(Chocolate);

            Book Mops = new Book(15, "Mops");
            bookList.add(Mops);

            //productRepository.saveAll(bookList);
        };
    }
}
