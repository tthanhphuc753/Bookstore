package com.example.Bookstore.Presentation.Controller.userController;

import com.example.Bookstore.Domain.AuthService.RegistrationRequest;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public User registerUser(RegistrationRequest request) {
        return userServices.registerUser(request);
    }

}
