package com.example.Bookstore.Presentation.Controller.userController;

import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CategoriesService.CategoriesService;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final UserServices userServices;
    private final CategoriesServiceController categoriesServiceController;
    private final BookController bookController;


    @GetMapping("list")
    public String getAllUser(Model model) {
        model.addAttribute("user", userServices.getAllUser());
        return "user";
    }

    @GetMapping("findbyid")
    public User findByID(@ModelAttribute("userID") Long id) {
        return userServices.findUserByID(id);
    }

    @DeleteMapping(path = "{userID}")
    public void deleteUser(@PathVariable long userID) {
        userServices.deleteUser(userID);
    }


    @GetMapping("book/list")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookController.getAllBook());
        return "booklist";
    }


    @GetMapping("/homepage")
    public String showHomePage(Model model) {
        model.addAttribute("books", bookController.getAllBook());
        model.addAttribute("category", categoriesServiceController.getAllCategory());
        return "homePage";
    }


}
