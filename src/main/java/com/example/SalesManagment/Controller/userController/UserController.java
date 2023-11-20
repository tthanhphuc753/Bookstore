package com.example.SalesManagment.Controller.userController;

import com.example.SalesManagment.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("listuser")
    public List<User> getUser() {
        return userServices.getUser();
    }

    @GetMapping("countuser")
    public long countUser() {
        return userServices.countUser();
    }

    @GetMapping("findbyid/{id}")
    public User findByID(@PathVariable long id) {
        return userServices.findUserByID(id);
    }

    @PostMapping("adduser")
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
