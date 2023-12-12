package com.example.Bookstore.Presentation.Controller.userController;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserController {

    private final UserServices userServices;



    public User findUserByID(Long id) {
        return userServices.findUserByID(id);
    }

    public List<User> getAllUser() {
        return userServices.getAllUser();
    }

    public void deleteUser(long userID) {
        userServices.deleteUser(userID);
    }


    public Optional<User> findByEmail(String s) {
        return userServices.findByEmail(s);
    }

}
