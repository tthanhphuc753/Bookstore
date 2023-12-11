package com.example.Bookstore.Domain.CartService;

import com.example.Bookstore.Domain.Model.Cart.CartItem;

import javax.servlet.http.HttpSession;
import java.util.Collection;

public interface CartService {


    void addToCart(CartItem item, HttpSession session);

    void removeFromCart(long bookId, HttpSession session);

    void updateCart(Long bookId, int quantity, HttpSession session);

    void clearAll(HttpSession session);

    Collection<CartItem> getAlls(HttpSession session);
}
