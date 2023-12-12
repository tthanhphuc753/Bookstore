package com.example.Bookstore.Presentation.Controller.ClientController;


import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final UserController userController;
    private final BookController bookController;
    private final CategoriesServiceController categoriesServiceController;
    private final OrderController orderController;

    @GetMapping("findbyid")
    public User findByID(@ModelAttribute("userID") Long id) {
        return userController.findUserByID(id);
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
}
