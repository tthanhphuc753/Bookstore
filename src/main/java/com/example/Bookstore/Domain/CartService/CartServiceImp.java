package com.example.Bookstore.Domain.CartService;

import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Persistence.DAO.BookRepository;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;

    private final Map<Long, CartItem> cartMap = new HashMap<>();


    @Override
    public void addToCart(CartItem item) {
        CartItem cartItem = cartMap.get(item.getBookID());
        if (cartItem == null) {
            cartMap.put(item.getBookID(), item);
            cartRepository.save(item);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem);
        }

    }

    @Override
    public void removeFromCart(long id) {
        cartMap.remove(id);
        cartRepository.deleteById(id);
    }

    @Override
    public CartItem updateCart(Long bookId, int quantity) {
        CartItem cartItem = cartMap.get(bookId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    @Override
    public void clearAll() {
        cartMap.clear();
        cartRepository.deleteAll();
    }

    @Override
    public Collection<CartItem> getAlls() {
        return cartMap.values();

    }
}
