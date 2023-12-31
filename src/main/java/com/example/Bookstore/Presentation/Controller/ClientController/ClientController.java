package com.example.Bookstore.Presentation.Controller.ClientController;


import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Presentation.Controller.BookController.ClientBookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.ClientCategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import com.example.Bookstore.Presentation.Controller.ShoppingCartController.ShoppingCartController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final UserController userController;
    private final ClientBookController clientBookController;
    private final ClientCategoriesServiceController clientCategoriesServiceController;
    private final OrderController orderController;
    private final ShoppingCartController shoppingCartController;


    @GetMapping("/homepage")
    public String showHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "12") int size) {
        clientBookController.pageSize(model, page, size);
        model.addAttribute("category", clientCategoriesServiceController.getAllCategory());
        return "homePage";
    }

    @Transactional
    @PostMapping("/order/add")
    public String addOrder(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        orderController.addOrder(request, session, response);
        return "redirect:/client/order/list";
    }

    @Transactional
    @PostMapping("add/{bookId}")
    public String addToCart(@PathVariable Long bookId, HttpServletRequest request, HttpSession session, Model model) {
        if (shoppingCartController.addToCart(bookId, request, session)) {
            int cartItemCount = shoppingCartController.getCountItem(session);
            model.addAttribute("cartItemCount", cartItemCount);
            return "redirect:/client/homepage";
        } else
            return "redirect:/client/list";
    }

    @GetMapping("cartitem/list")
    public String getAll(Model model, HttpSession session) {
        shoppingCartController.getAll(model, session);
        return "shopping-cart";
    }

    @PostMapping("delete/{bookId}")
    public String deleteCart(@PathVariable Long bookId, HttpSession session) {
        shoppingCartController.deleteCart(bookId, session);
        return "redirect:/client/cartitem/list";
    }

    @PostMapping("cartitem/update")
    public String updateCartQuantity(@ModelAttribute("bookId") Long bookId,
                                     @ModelAttribute("quantity") int quantity, HttpSession session) {
        shoppingCartController.updateCartQuantity(bookId, quantity, session);
        return "redirect:/client/cartitem/list";
    }

    @GetMapping("book/search")
    public String searchBooks(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Book> searchResults = clientBookController.searchBooks(keyword);
        model.addAttribute("books", searchResults);
        model.addAttribute("category", clientCategoriesServiceController.getAllCategory());
        return "homePage";
    }

    @GetMapping("order/list")
    public String getAllOrders(Model model, HttpSession session) {
        model.addAttribute("orders", orderController.getAllOrder());
        return "clientorder";
    }


    @GetMapping("/cartitem/count")
    @ResponseBody
    public int getCartItemCount(HttpSession session) {
        return shoppingCartController.getCountItem(session);
    }
}
