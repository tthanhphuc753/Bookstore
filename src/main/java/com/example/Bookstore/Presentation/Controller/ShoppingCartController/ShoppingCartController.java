package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ShoppingCartController {

    private final CartService cartService;
    private final JwtService jwtService;
    private final BookController bookController;
    private final UserController userController;

    public boolean addToCart(Long bookId, HttpServletRequest request, HttpSession session) {
        Optional<Book> optionalBook = bookController.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            CartItem item = new CartItem();
            item.setBook(book);

            String token = getJwtFromCookie(request);

            if (token != null) {
                Optional<User> optionalUser = userController.findByEmail(jwtService.extractUsername(token));
                User user = optionalUser.get();
                item.setUser(user);
            }
            item.setQuantity(1);
            cartService.addToCart(item, session);
            return true;
        } else
            return false;
    }

    public void getAll(Model model, HttpSession session) {
        model.addAttribute("cartitem", cartService.getAlls(session));
    }


    public void deleteCart(Long bookId, HttpSession session) {
        cartService.removeFromCart(bookId, session);
    }

    public void updateCartQuantity(Long bookId, int quantity, HttpSession session) {
        cartService.updateCart(bookId, quantity, session);
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
