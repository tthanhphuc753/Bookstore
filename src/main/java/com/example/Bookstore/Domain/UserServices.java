package com.example.Bookstore.Domain;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Presentation.Controller.Auth.AuthenticationRequest;
import com.example.Bookstore.Presentation.Controller.Auth.AuthenticationResponse;
import com.example.Bookstore.Presentation.Controller.Auth.RegistrationRequest;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserServices {

    String getUser(Model model);

    User registerUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    Optional<User> findByEmail(String email);


    long countUser();

    User findUserByID(long id);

    void addUser(User user);

    void deleteUser(long userID);

    User addProduct(Long userID, Long productID);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);


}
