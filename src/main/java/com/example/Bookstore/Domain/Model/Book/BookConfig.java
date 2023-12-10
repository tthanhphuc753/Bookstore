package com.example.Bookstore.Domain.Model.Book;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration

public class BookConfig {




    @Bean(name = "ProductBean")
    CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }
}
