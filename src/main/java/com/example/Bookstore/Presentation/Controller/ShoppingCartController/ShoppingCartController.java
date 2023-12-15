package com.example.Bookstore.Presentation.Controller.ShoppingCartController;

import com.example.Bookstore.Domain.CartService.CartService;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Presentation.Controller.BookController.ClientBookController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ShoppingCartController {

    private final CartService cartService;
    private final ClientBookController clientBookController;

    public boolean addToCart(Long bookId, HttpServletRequest request, HttpSession session) {
        Optional<Book> optionalBook = clientBookController.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            CartItem item = new CartItem();
            item.setBook(book);
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

    public int getCountItem(HttpSession session) {
        return cartService.getCountItem(session);
    }
}
