package com.example.Bookstore.Domain.UserService;

import com.example.Bookstore.Domain.AuthService.RegistrationRequest;
import com.example.Bookstore.Domain.Model.User.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    List<User> getAllUser();

    User registerUser(RegistrationRequest request);

    Optional<User> findByEmail(String email);


    User findUserByID(long id);


    void deleteUser(long userID);


    void saveUserVerificationToken(User theUser, String verificationToken);


}
