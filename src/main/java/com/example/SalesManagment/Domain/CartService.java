package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Domain.Model.Cart.Cart;

import java.util.Collection;

public interface CartService {


    void addToCart(Cart item);

    void removeFromCart(long id);

    Cart updateCart(Long bookId, int quantity);

    void clearAll();

    Collection<Cart> getAlls();
}
