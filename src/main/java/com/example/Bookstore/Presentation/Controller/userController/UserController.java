package com.example.Bookstore.Presentation.Controller.userController;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")

public class UserController {

    private final UserServices userServices;


    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("list")
    public String getAllUser(Model model) {
        return userServices.getAllUser(model);
    }

    @GetMapping("findbyid")
    public User findByID(@ModelAttribute("userID") Long id) {
        return userServices.findUserByID(id);
    }

    @DeleteMapping(path = "{userID}")
    public void deleteUser(@PathVariable long userID) {
        userServices.deleteUser(userID);
    }




}
