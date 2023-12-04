package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Presentation.Controller.Aut.AuthenticateRequest;
import com.example.SalesManagment.Presentation.Controller.Aut.AuthenticationResponse;
import com.example.SalesManagment.Presentation.Controller.Aut.RegistrationRequest;
import com.example.SalesManagment.Domain.Model.User.User;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserServices {

    String getUser(Model model);

    User registerUser(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticateRequest request);

    Optional<User> findByEmail(String email);


    long countUser();

    User findUserByID(long id);

    void addUser(User user);

    void deleteUser(long userID);

    User addProduct(Long userID, Long productID);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);


}
