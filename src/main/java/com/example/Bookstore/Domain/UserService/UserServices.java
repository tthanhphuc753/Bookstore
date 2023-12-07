package com.example.Bookstore.Domain.UserService;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.RegistrationRequest;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserServices {

    String getAllUser(Model model);

    User registerUser(RegistrationRequest request);

    Optional<User> findByEmail(String email);

    long countUser();

    User findUserByID(long id);

    void addUser(User user);

    void deleteUser(long userID);

    User addProduct(Long userID, Long productID);

    void saveUserVerificationToken(User theUser, String verificationToken);

  //  String validateToken(String theToken);


}
