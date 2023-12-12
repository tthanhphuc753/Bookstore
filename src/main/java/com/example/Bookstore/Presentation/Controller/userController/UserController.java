package com.example.Bookstore.Presentation.Controller.userController;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final UserServices userServices;
    private final CategoriesServiceController categoriesServiceController;
    private final BookController bookController;
    private final OrderController orderController;


    @GetMapping("findbyid")
    public User findByID(@ModelAttribute("userID") Long id) {
        return userServices.findUserByID(id);
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

    @PostMapping("add/{cartId}/{userId}")
    public void addOrder(@PathVariable Long cartId, @PathVariable Long userId) {
        orderController.addOrder(cartId, userId);
    }

    public List<User> getAllUser() {
        return userServices.getAllUser();
    }

    public void deleteUser(long userID) {
        userServices.deleteUser(userID);
    }


    public Optional<User> findByEmail(String s) {
        return userServices.findByEmail(s);
    }

}
