package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.BookService.BookService;
import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
import com.example.Bookstore.Domain.UserService.UserServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final CartService cartService;
    private final BookService bookService;
    private final JwtService jwtService;
    private final UserServices userServices;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @PostMapping("add/{bookId}")
    public String addToCart(@PathVariable Long bookId, HttpServletRequest request, HttpSession session) {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            CartItem item = new CartItem();
            item.setBook(book);

            String token = getJwtFromCookie(request);

            if (token != null) {
                Optional<User> optionalUser = userServices.findByEmail(jwtService.extractUsername(token));
                User user = optionalUser.get();
                item.setUser(user);
            }
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

    @DeleteMapping("delete/{cartId}")
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

    private String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
