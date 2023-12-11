package com.example.Bookstore;

import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CategoriesRepository;
import com.example.Bookstore.Persistence.DAO.OrderdetailRepository;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.ApplicationConfig;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

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
    private final List<Book> bookList = new ArrayList<>();


    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {
            User Phuc = new User(null, "Phuc", "Tran"
                    , "tthanhphuc753@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "ADMIN", null, true, null, null);
            userRepository.save(Phuc);

            User Sor = new User(null, "Sor", "Q"
                    , "tthanhphuc752@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "USER", null, true, null, null);
            userRepository.save(Sor);


            List<Order> orderList = new ArrayList<>();
            Order newOrder = new Order(null, new Date(), "black sea", Phuc, 3);
            orderList.add(newOrder);
            orderdetailRepository.saveAll(orderList);

            List<Categories> lc = new ArrayList<>();
            Categories newCat = new Categories(1, "Document", null);
            lc.add(newCat);
            Categories newCat1 = new Categories(2,"Romance",null);
            lc.add(newCat1);
            Categories newCat2 = new Categories(3,"Mystery",null);
            lc.add(newCat2);
            Categories newCat3 = new Categories(4,"Fantasy & Science fiction",null);
            lc.add(newCat3);
            Categories newCat4 = new Categories(5,"Thrillers & Horror",null);
            lc.add(newCat4);
            Categories self_help = new Categories(6,"Self-help",null);
            lc.add(self_help);
            Categories short_stories = new Categories(7,"Short Stories",null);
            lc.add(short_stories);
            Categories cookbooks = new Categories(8,"Cookbooks",null);
            lc.add(cookbooks);
            Categories history = new Categories(9,"History",null);
            lc.add(history);
            Categories science = new Categories(10,"Science",null);
            lc.add(science);
            Categories adventure = new Categories(11,"Adventure",null);
            lc.add(adventure);
            Categories humor = new Categories(12,"Humor",null);
            lc.add(humor);
            Categories biography = new Categories(13,"Biography",null);
            lc.add(biography);
            Categories memoir = new Categories(14,"Memoir",null);
            lc.add(memoir);




            categoriesRepository.saveAll(lc);


            Set<Categories> newSet = new HashSet<>();
            newSet.add(newCat);

            Book ThinkAndGrowRich = new Book(24, "Think And Grow Rich", "Phuc", newSet);
            bookList.add(ThinkAndGrowRich);

            bookRepository.saveAll(bookList);
        };
    }
}
