package com.example.SalesManagment.Presentation.Controller.userController;

import com.example.SalesManagment.Domain.Model.User.User;
import com.example.SalesManagment.Domain.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/user")

public class UserController {

    private final UserServices userServices;


    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("list")
    public String getUser(Model model) {
        return userServices.getUser(model);
    }

    @GetMapping("count")
    public long countUser() {
        return userServices.countUser();
    }

    @GetMapping("findbyid/{id}")
    public User findByID(@PathVariable long id) {
        return userServices.findUserByID(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userServices.addUser(user);
        return ResponseEntity.ok("Data saved");
    }

    @DeleteMapping(path = "{userID}")
    public void deleteUser(@PathVariable long userID) {
        userServices.deleteUser(userID);
    }

    @PutMapping("/{userID}/product/{productID}")
    public User addProductForUser(@PathVariable Long userID, @PathVariable Long productID) {
        return userServices.addProduct(userID, productID);
    }


}
