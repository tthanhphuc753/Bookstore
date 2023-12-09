package com.example.Bookstore.Domain.Model.Order;

import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.OrderdetailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderConfig {

    private final List<Order> orderList = new ArrayList<>();

//    @Bean(name = "OrderBean")
//    CommandLineRunner commandLineRunner(OrderdetailRepository orderdetailRepository) {
//        return args -> {
//            Order newOrder = new Order();
//            orderList.add(newOrder);
//
//            orderdetailRepository.saveAll(orderList);
//        };
//    }
}
