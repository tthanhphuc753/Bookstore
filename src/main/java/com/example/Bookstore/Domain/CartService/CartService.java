package com.example.Bookstore.Domain.CartService;

import com.example.Bookstore.Domain.Model.Cart.CartItem;

import java.util.Collection;

public interface CartService {


    void addToCart(CartItem item);

    void removeFromCart(long id);

    CartItem updateCart(Long bookId, int quantity);

    void clearAll();

    Collection<CartItem> getAlls();
}
