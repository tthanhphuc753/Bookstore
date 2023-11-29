package com.example.SalesManagment.Controller.userController;

import com.example.SalesManagment.Controller.Aut.AuthenticateRequest;
import com.example.SalesManagment.Controller.Aut.AuthenticationResponse;
import com.example.SalesManagment.Controller.Aut.RegistrationRequest;
import com.example.SalesManagment.Model.User.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    List<User> getUser();

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
