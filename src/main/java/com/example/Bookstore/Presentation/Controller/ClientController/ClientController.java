package com.example.Bookstore.Presentation.Controller.ClientController;


import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import com.example.Bookstore.Presentation.Controller.ShoppingCartController.ShoppingCartController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final UserController userController;
    private final BookController bookController;
    private final CategoriesServiceController categoriesServiceController;
    private final OrderController orderController;
    private final ShoppingCartController shoppingCartController;

    @GetMapping("user/findbyid")
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

    @PostMapping("add/{bookId}")
    public String addToCart(@PathVariable Long bookId, HttpServletRequest request, HttpSession session) {
        if (shoppingCartController.addToCart(bookId, request, session)) {
            return "redirect:/user/homepage";
        } else
            return "redirect:/shopping-cart/list";
    }

    @GetMapping("list")
    public String getAll(Model model, HttpSession session) {
        shoppingCartController.getAll(model, session);
        return "shopping-cart";
    }

    @PostMapping("delete/{bookId}")
    public String deleteCart(@PathVariable Long bookId, HttpSession session) {
        shoppingCartController.deleteCart(bookId, session);
        return "redirect:/shopping-cart/list";
    }

    @PostMapping("update")
    public String updateCartQuantity(@ModelAttribute("bookId") Long bookId,
                                     @ModelAttribute("quantity") int quantity, HttpSession session) {
        shoppingCartController.updateCartQuantity(bookId, quantity, session);
        return "redirect:/shopping-cart/list";
    }
}
