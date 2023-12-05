package com.example.Bookstore.Domain.Model.Book;

import com.example.Bookstore.Persistence.DAO.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookConfig {

    private final List<Book> bookList = new ArrayList<>();

    @Bean(name = "ProductBean")
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book ThinkAndGrowRich = new Book(24, "Think And Grow Rich");
            bookList.add(ThinkAndGrowRich);


            bookRepository.saveAll(bookList);
        };
    }
}
