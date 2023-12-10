package com.example.Bookstore;

import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Persistence.DAO.CategoriesRepository;
import com.example.Bookstore.Persistence.DAO.OrderdetailRepository;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Bookstore {

    public static void main(String[] args) {
        SpringApplication.run(Bookstore.class, args);
    }

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private OrderdetailRepository orderdetailRepository;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User Phuc = new User(null, "Phuc", "Tran"
                    , "tthanhphuc753@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "ADMIN", null, true, null,null);
            userRepository.save(Phuc);

            User Sor = new User(null, "Sor", "Q"
                    , "tthanhphuc752@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "USER", null, true, null,null);
            userRepository.save(Sor);


            List<Order> orderList = new ArrayList<>();
            Order newOrder = new Order(null, new Date(), "black sea", Phuc, 3);
            orderList.add(newOrder);
            orderdetailRepository.saveAll(orderList);


            Categories newCat = new Categories(1, "Document", null);
            categoriesRepository.save(newCat);
        };
    }
}
