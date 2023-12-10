package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final CartService cartService;
    private final BookService bookService;
    private final UserServices userServices;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @PostMapping("add/{id}/{userid}")
    public String addToCart(@PathVariable Long id, @PathVariable Long userId, HttpServletRequest request, HttpSession session) {
        Optional<Book> optionalBook = bookService.findById(id);
        User user = userServices.findUserByID(userId);
        if (optionalBook.isPresent() ) {
            Book book = optionalBook.get();
            CartItem item = new CartItem();
            item.setBook(book);
            item.setUser(user);
            item.setQuantity(1);
            cartService.addToCart(item, session);
            return "redirect:/shopping-cart/list";
        } else
            return "redirect:/shopping-cart/list";
    }

    @GetMapping("list")
    public String getAll(Model model, HttpSession session) {
        model.addAttribute("cartitem", cartService.getAlls(session));
        return "redirect:/book/homepage";
    }

    @DeleteMapping("delete/{id}")
    public String deleteCart(@PathVariable Long cartId, HttpSession session) {
        cartService.removeFromCart(cartId, session);
        return "redirect:/book/homepage";
    }

    @PostMapping("update")
    public String updateCartQuantity(@ModelAttribute("cartId") Long cartId,
                                     @ModelAttribute("quantity") int quantity, HttpSession session) {
        cartService.updateCart(cartId, quantity, session);
        return "redirect:/shopping-cart/list";
    }
}
