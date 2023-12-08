package com.example.Bookstore.Domain.UserService;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.RegistrationRequest;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserServices {

    String getAllUser(Model model);

    User registerUser(RegistrationRequest request);

    Optional<User> findByEmail(String email);


    User findUserByID(long id);


    void deleteUser(long userID);


    void saveUserVerificationToken(User theUser, String verificationToken);


}
